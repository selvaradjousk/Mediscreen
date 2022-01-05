package com.patientHistory.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.patientHistory.model.Note;


@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findByPatientId(final Integer patientId);

}
