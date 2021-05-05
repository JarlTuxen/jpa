package dk.kea.jpa.repository;

import dk.kea.jpa.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
