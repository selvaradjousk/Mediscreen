package com.patient.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patient.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	@Query("SELECT p FROM Patient p WHERE CONCAT(p.lastName, ' ', p.firstName) LIKE %?1%")
    List<Patient> findByKeyword(String keyword);


    Patient findByLastNameAndFirstNameAndBirthDate(
    		final String lastName,
    		final String firstName,
            final LocalDate birthDate);

    Patient findByLastName(String LastName);

    int countByLastName(String lastName);
}
