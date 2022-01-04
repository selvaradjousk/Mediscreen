package com.patientHistory.repository;

import com.patientHistory.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String>  {

}
