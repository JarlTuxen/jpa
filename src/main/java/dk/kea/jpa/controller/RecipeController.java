package dk.kea.jpa.controller;

import dk.kea.jpa.repository.CategoryRepository;
import dk.kea.jpa.repository.IngredientRepository;
import dk.kea.jpa.repository.NotesRepository;
import dk.kea.jpa.repository.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
    RecipeRepository recipeRepository;
    NotesRepository notesRepository;
    IngredientRepository ingredientRepository;
    CategoryRepository categoryRepository;

    public RecipeController(RecipeRepository recipeRepository, NotesRepository notesRepository, IngredientRepository ingredientRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.notesRepository = notesRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
