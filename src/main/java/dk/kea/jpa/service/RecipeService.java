package dk.kea.jpa.service;

import dk.kea.jpa.model.Ingredient;
import dk.kea.jpa.model.Notes;
import dk.kea.jpa.model.Recipe;
import dk.kea.jpa.repository.IngredientRepository;
import dk.kea.jpa.repository.NotesRepository;
import dk.kea.jpa.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService {

    //Annoter med autowired til repository
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public Set<Recipe> findAll(){
        //hent alle opskrifter fre repo med finaAll og returner collection
        Set<Recipe> recipes = new HashSet<>();
        for (Recipe recipe: recipeRepository.findAll()){
            recipes.add(recipe);
        }
        return recipes;
    }

    //findById
    public Recipe findById(long id){
        //findById giver en optional
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        //hvis der ikke findes et product på id, kastes der en fejl
        if (!recipeOptional.isPresent())
        {
            throw new RuntimeException("Recipe not found");
        }
        //returner product vha. .get()
        return recipeOptional.get();
    }

    //create - ny note og ingredient
    public void create(Recipe recipe){
        //der skal sættes reference begge veje på notes, så først opretres en ny opskrift
        Recipe _recipe = new Recipe(r.getDescription(), r.getPrepTime(), r.getCookTime(), r.getServings(), r.getSource(), r.getUrl(), r.getDirections());

        //brug af notes-objekt i r og gem med nye reference itl recipe
        Notes _notes=r.getNotes();
        _notes.setRecipe(_recipe);
        notesRepository.save(_notes);

        //gem de nye ingredients
        Set<Ingredient> _ingredients = r.getIngredients();
        for (Ingredient ingredient : _ingredients){
            ingredient.setRecipe(_recipe);
            ingredientRepository.save(ingredient);
        }
        _recipe.setIngredients(_ingredients);

        //gem recipe
        recipeRepository.save(_recipe);

    }

    //update
    public void update(Recipe recipe){
        recipeRepository.save(recipe);
    }

    //deleteById
    public void deleteById(long id){
        recipeRepository.deleteById(id);
    }

}
