package dk.kea.jpa.controller;

import dk.kea.jpa.model.Ingredient;
import dk.kea.jpa.model.Recipe;
import dk.kea.jpa.service.RecipeService;
import dk.kea.jpa.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {

    RecipeService recipeService;
    IngredientService ingredientService;

    // constructor injection
    public RecipeController(RecipeService recipeService, IngredientService ingredientService ){
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/")
    public String index(Model model){

        // find opskrifter og put dem i view modellen
        model.addAttribute("recipes", recipeService.findAll());

        return "index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Iterable<Ingredient> ingredientList = ingredientService.findAll();
        model.addAttribute("ingredients", ingredientList);
        return "createrecipe";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Recipe recipe){
        recipeService.save(recipe);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model, Model modelingredient){
        model.addAttribute("recipe", recipeService.findById(id));
        modelingredient.addAttribute("ingredients", ingredientService.findAll());
        return "updateStudent";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Recipe recipe){
        recipeService.save(recipe);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }
}
}
