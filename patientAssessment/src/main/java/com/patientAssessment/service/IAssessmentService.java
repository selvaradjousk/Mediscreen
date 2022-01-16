package com.patientAssessment.service;

import com.patientAssessment.dto.AssessmentDTO;
import com.patientAssessment.dto.PatientDTO;

public interface IAssessmentService {

	AssessmentDTO getPatientAssessment(Integer patientId);

	PatientDTO getPatient(String lastName);

}