package com.patientAssessment.service;

import com.patientAssessment.dto.AssessmentDTO;
import com.patientAssessment.dto.PatientDTO;

/**
 * The Interface IAssessmentService.
 */
public interface IAssessmentService {

	/**
	 * Gets the patient assessment.
	 *
	 * @param patientId the patient id
	 * @return the patient assessment
	 */
	AssessmentDTO getPatientAssessment(Integer patientId);

	/**
	 * Gets the patient.
	 *
	 * @param lastName the last name
	 * @return the patient
	 */
	PatientDTO getPatient(String lastName);

}