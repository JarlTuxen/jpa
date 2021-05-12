package dk.kea.jpa.controller;

import dk.kea.jpa.model.Category;
import dk.kea.jpa.model.Ingredient;
import dk.kea.jpa.model.Notes;
import dk.kea.jpa.model.Recipe;
import dk.kea.jpa.repository.CategoryRepository;
import dk.kea.jpa.repository.IngredientRepository;
import dk.kea.jpa.repository.NotesRepository;
import dk.kea.jpa.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class RestRecipeController {

    RecipeRepository recipeRepository;
    NotesRepository notesRepository;
    IngredientRepository ingredientRepository;
    CategoryRepository categoryRepository;


    // constructor injection
    public RestRecipeController(RecipeRepository recipeRepository,
                                NotesRepository notesRepository,
                                IngredientRepository ingredientRepository,
                                CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.notesRepository = notesRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
    }

    // HTTP Get List
    @GetMapping("/recipe")
    public ResponseEntity<Iterable<Recipe>> findAll(){
        //findAll recipes and return
        return ResponseEntity.status(HttpStatus.OK).body(recipeRepository.findAll());
    }

    // HTTP Get by ID
    @GetMapping("/recipe/{id}")
    public ResponseEntity<Optional<Recipe>> findById(@PathVariable Long id)
    {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalRecipe);
        }
        else{
            //Not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalRecipe);
        }
    }

    // HTTP Post, ie. create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/recipe", consumes = "application/json")
    public ResponseEntity<String> create(@RequestBody Recipe r){
        //gem recipe, så der er et id tilknyttet til den nye opskrift til mapning i modsat retning
        //_recipe_id kan så bruges som fremmenøgle i notes, ingredients og categories
        Recipe _recipe = new Recipe(r.getDescription(), r.getPrepTime(), r.getCookTime(), r.getServings(), r.getSource(), r.getUrl(), r.getDirections());
        //glemt i undervisning torsdag - sørger for ny primary key til brug som fremmednøgle i andre klasser
        recipeRepository.save(_recipe);

        //brug af notes-objekt i r request body
        Notes _notes = r.getNotes();

        //sæt fremmednøgle til recipe_id i notes
        _notes.setRecipe(_recipe);
        notesRepository.save(_notes);

        //sæt fremmednøgle til note_id i recipe
        _recipe.setNotes(_notes);

        Set<Ingredient> _ingredients = r.getIngredients();
        //iterer igennem _ingredients og tilføj dem
        for (Ingredient ingredient: _ingredients) {
            //sæt fremmednøgle til recipe_id i ingredient
            ingredient.setRecipe(_recipe);
            ingredientRepository.save(ingredient);
        }

        //ikke nødvendigt at tilføje til _recipe, da fremmednøglen er i _ingredients

        //category - kør igennem categories på ny recipe
        //  find tilsvarende category i repository
        //  opdater category med recipe
        Set<Category> _categories = r.getCategories();
        for (Category category : _categories) {
            Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
            if (optionalCategory.isPresent()) {
                Category cat = optionalCategory.get();
                cat.getRecipes().add(_recipe);
                categoryRepository.save(cat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category " + category.getId() + " not found");
            }
        }
        //tilføj nye fremmednøgler til jointabel til _recipe
        _recipe.setCategories(_categories);

        //opdater _recipe efter tilføjelse af fremmenøgler til notes og categories
        recipeRepository.save(_recipe);

        //endelig kan vi sende svar med location
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/recipe/" + r.getId())
                .body("{'Msg': 'Post created'}");

    }

    // HTTP PUT, ie. update
    @PutMapping("/recipe/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Recipe r) {
        //get recipeById
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (!optionalRecipe.isPresent()) {
            //Recipe id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'recipe " + id + " not found'");
        }
        //opdater category, ingredient og notes sker automatisk - nu er relationen oprettet
        //save recipe
        recipeRepository.save(r);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'msg' : 'updated' }");
    }

    // HTTPDelete - first shot
    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        //check at opskriften findes
        if (!optionalRecipe.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ 'msg' : 'recipe " + id + " not found'}");
        }

        Recipe r = optionalRecipe.get();

        //slet først referencerne til recipe i categories
        for (Category c : r.getCategories()) {
            c.getRecipes().remove(r);
        }

        //derefter kan categories slettes fra recipe
        r.setCategories(null);

        //og opdateres (nu uden category mappings)
        recipeRepository.save(r);

        //til sidst kan recipe slettes uden at bryde referentiel integritet
        recipeRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("{ 'msg' : 'deleted'}");
    }

}
