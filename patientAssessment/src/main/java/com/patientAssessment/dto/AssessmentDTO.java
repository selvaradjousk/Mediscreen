package com.patientAssessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentDTO {

    private PatientDTO patientDTO;

    private String diabetesRiskLevel;
}
