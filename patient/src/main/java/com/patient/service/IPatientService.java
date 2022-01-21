package com.patient.service;

import java.util.List;

import com.patient.dto.PatientDTO;

/**
 * The Interface IPatientService.
 */
public interface IPatientService {

	/**
	 * Gets the all patients.
	 *
	 * @param keyword the keyword
	 * @return the all patients
	 */
	List<PatientDTO> getAllPatients(String keyword);

	/**
	 * Update patient.
	 *
	 * @param patientId the patient id
	 * @param patientDTO the patient DTO
	 * @return the patient DTO
	 */
	PatientDTO updatePatient(int patientId, PatientDTO patientDTO);

    /**
     * Gets the patient by id.
     *
     * @param patientId the patient id
     * @return the patient by id
     */
    PatientDTO getPatientById(int patientId);

    /**
     * Adds the patient.
     *
     * @param patientDTO the patient DTO
     * @return the patient DTO
     */
    PatientDTO addPatient(final PatientDTO patientDTO);

    /**
     * Delete patient.
     *
     * @param patientId the patient id
     */
    void deletePatient(final int patientId);

    /**
     * Gets the patient.
     *
     * @param lastName the last name
     * @return the patient
     */
    PatientDTO getPatient(String lastName);

}