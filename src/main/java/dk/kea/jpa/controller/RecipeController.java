package dk.kea.jpa.controller;

import dk.kea.jpa.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    RecipeService recipeService;
    // constructor injection
    public RecipeController(RecipeService recipeService ){
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String index(Model model){

        // find opskrifter og put dem i view modellen
        model.addAttribute("recipes", recipeService.findAll());

        return "index";
    }

}
