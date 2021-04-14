package dk.kea.jpa.repository;

import dk.kea.jpa.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    //Optional<Recipe> findByCookTime(int cooktime);
    Set<Recipe> findAllByCookTime(int cooktime);

}
