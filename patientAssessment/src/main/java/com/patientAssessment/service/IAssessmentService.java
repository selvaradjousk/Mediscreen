package com.patientAssessment.service;

import com.patientAssessment.dto.AssessmentDTO;

public interface IAssessmentService {

	AssessmentDTO getPatientAssessment(Integer patientId);

}