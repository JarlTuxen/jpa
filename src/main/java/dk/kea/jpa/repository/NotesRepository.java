package dk.kea.jpa.repository;

import dk.kea.jpa.model.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes, Long> {
}
