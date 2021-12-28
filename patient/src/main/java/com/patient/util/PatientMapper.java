package com.patient.util;

import org.springframework.stereotype.Component;

import com.patient.dto.PatientDTO;
import com.patient.model.Patient;

@Component
public class PatientMapper {


    public Patient toPatient(final PatientDTO patientDTO) {

        return new Patient(
        		patientDTO.getLastName(),
        		patientDTO.getFirstName(),
        		patientDTO.getBirthDate(),
                patientDTO.getSex(),
                patientDTO.getAddress(),
                patientDTO.getPhoneNumber());
    }



    public PatientDTO toPatientDTO(final Patient patient) {

        return new PatientDTO(
        		patient.getId(),
        		patient.getLastName(),
        		patient.getFirstName(),
        		patient.getBirthDate(),
                patient.getSex(),
                patient.getAddress(),
                patient.getPhoneNumber());
    }

}
