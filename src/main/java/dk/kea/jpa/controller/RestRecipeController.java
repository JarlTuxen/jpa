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

    // HTTP Post, ie. create
        //gem recipe, så der er et id tilknyttet til den nye opskrift til mapning i modsat retning
        //_recipe_id kan så bruges som fremmenøgle i notes, ingredients og categories

        //brug af notes-objekt i r request body

        //sæt fremmednøgle til recipe_id i notes

        //sæt fremmednøgle til note_id i recipe

        //iterer igennem _ingredients og tilføj dem

            //sæt fremmednøgle til recipe_id i ingredient

        //ikke nødvendigt at tilføje til _recipe, da fremmednøglen er i _ingredients

        //category - kør igennem categories på ny recipe

        //  find tilsvarende category i repository

        //  opdater category med opskrift

        //tilføj nye fremmednøgler til jointabel til _recipe

        //opdater _recipe efter tilføjelse af fremmenøgler til notes og categories


    // HTTP PUT, ie. update

        //get recipeById

            //Recipe id findes ikke


        //opdater category, ingredient og notes sker automatisk - nu er relationen oprettet
        //save recipe


    // HTTPDelete

        //check at opskriften findes



        //slet først referencerne til recipe i categories


        //derefter kan categories slettes fra recipe


        //og opdateres (nu uden category mappings)


        //til sidst kan recipe slettes uden at bryde referentiel integritet




}
