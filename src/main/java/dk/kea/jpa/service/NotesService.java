package dk.kea.jpa.service;

import dk.kea.jpa.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotesService {

    @Autowired
    NotesRepository notesRepository;


}
