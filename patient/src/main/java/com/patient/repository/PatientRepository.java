package com.patient.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patient.model.Patient;

/**
 * The Interface PatientRepository.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	/**
	 * Find by keyword.
	 *
	 * @param keyword the keyword
	 * @return the list
	 */
	@Query("SELECT p FROM Patient p WHERE CONCAT(p.lastName, ' ', p.firstName) LIKE %?1%")
    List<Patient> findByKeyword(String keyword);


    /**
     * Find by last name and first name and birth date.
     *
     * @param lastName the last name
     * @param firstName the first name
     * @param birthDate the birth date
     * @return the patient
     */
    Patient findByLastNameAndFirstNameAndBirthDate(
    		final String lastName,
    		final String firstName,
            final LocalDate birthDate);

    /**
     * Find by last name.
     *
     * @param LastName the last name
     * @return the patient
     */
    Patient findByLastName(String LastName);

    /**
     * Count by last name.
     *
     * @param lastName the last name
     * @return the int
     */
    int countByLastName(String lastName);
}
