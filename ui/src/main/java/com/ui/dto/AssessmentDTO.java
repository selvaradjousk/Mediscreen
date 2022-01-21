package com.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class AssessmentDTO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentDTO {

    /** The patient DTO. */
    private PatientDTO patientDTO;
    
    /** The patient age. */
    private int patientAge;

    /** The diabetes risk level. */
    private String diabetesRiskLevel;
}
