package com.patientAssessment.constant;

import lombok.Getter;


/**
 * The Enum DiabetesTrigger.
 */
@Getter
public enum DiabetesTrigger {

    /** The hemoglobin a1c. */
    HEMOGLOBIN_A1C("Hemoglobin A1C"),
    
    /** The microalbumin. */
    MICROALBUMIN("Microalbumin"),
    
    /** The height. */
    HEIGHT("Height"),
    
    /** The weight. */
    WEIGHT("Weight"),
    
    /** The smoker. */
    SMOKER("Smoker"),
    
    /** The abnormal. */
    ABNORMAL("Abnormal"),
    
    /** The cholesterol. */
    CHOLESTEROL("Cholesterol"),
    
    /** The dizziness. */
    DIZZINESS("Dizziness"),
    
    /** The relapse. */
    RELAPSE("Relapse"),
    
    /** The reaction. */
    REACTION("Reaction"),
    
    /** The antibodies. */
    ANTIBODIES("Antibodies");

    /** The trigger. */
    private String trigger;

    /**
     * Instantiates a new diabetes trigger.
     *
     * @param trigger the trigger
     */
    DiabetesTrigger(final String trigger) {
        this.trigger = trigger;
    }
}