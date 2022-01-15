package com.patientAssessment.constant;

import lombok.Getter;

@Getter
public enum RiskLevels {

    NONE("None"),
    BORDERLINE("Borderline"),
    IN_DANGER("In danger"),
    EARLY_ONSET("Early onset");

    private String riskLevel;

    private RiskLevels(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}
