package com.patientAssessment.constant;

import lombok.Getter;

/**
 * The Enum RiskLevels.
 */
@Getter
public enum RiskLevels {

    /** The none. */
    NONE("None"),
    
    /** The borderline. */
    BORDERLINE("Borderline"),
    
    /** The in danger. */
    IN_DANGER("In danger"),
    
    /** The early onset. */
    EARLY_ONSET("Early onset");

    /** The risk level. */
    private String riskLevel;

    /**
     * Instantiates a new risk levels.
     *
     * @param riskLevel the risk level
     */
    private RiskLevels(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}
