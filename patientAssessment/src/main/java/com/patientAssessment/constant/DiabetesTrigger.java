package com.patientAssessment.constant;

import lombok.Getter;

@Getter
public enum DiabetesTrigger {

    HEMOGLOBIN_A1C("Hemoglobin A1C"),
    MICROALBUMIN("Microalbumin"),
    HEIGHT("Height"),
    WEIGHT("Weight"),
    SMOKER("Smoker"),
    ABNORMAL("Abnormal"),
    CHOLESTEROL("Cholesterol"),
    DIZZINESS("Dizziness"),
    RELAPSE("Relapse"),
    REACTION("Reaction"),
    ANTIBODIES("Antibodies");

    private String trigger;

    DiabetesTrigger(final String trigger) {
        this.trigger = trigger;
    }
}