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

        /*
        // find opskrift med id=1

        Recipe x = recipeService.findById((long) 1);
        System.out.println(x.getDescription());

        // hent alle opskrifter
        System.out.println("opskrifter - directions");
        for (Recipe r : recipeService.findAll()) {
            System.out.println(r.getDirections());
        }

        // brug af egen defineret findAllByCookTime - skal laves i service
        for (Recipe s : recipeService.findAllByCookTime(20)) {
            System.out.println(s.getUrl());
        }

        // hente opskrifter og ingredienser
        System.out.println("opskrift med ingedienser");
        Recipe grd = recipeService.findById(1L);
        System.out.println(grd.getDescription());
        // udskriv ingredienser
        for (Ingredient i : grd.getIngredients()){
            System.out.println(i.getDescription());
        }
        */

        return "index";
    }

}
