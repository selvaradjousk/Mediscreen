package com.patientHistory.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.patientHistory.model.Note;


/**
 * The Interface NoteRepository.
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    /**
     * Find by patient id.
     *
     * @param patientId the patient id
     * @return the list
     */
    List<Note> findByPatientId(final Integer patientId);

}
