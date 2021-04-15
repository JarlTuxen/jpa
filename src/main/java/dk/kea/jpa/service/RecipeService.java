package dk.kea.jpa.service;

import dk.kea.jpa.model.Recipe;
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


}
