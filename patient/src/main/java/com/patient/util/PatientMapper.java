package com.patient.util;

import org.springframework.stereotype.Component;

import com.patient.dto.PatientDTO;
import com.patient.model.Patient;

/**
 * The Class PatientMapper.
 */
@Component
public class PatientMapper {


    /**
     * To patient.
     *
     * @param patientDTO the patient DTO
     * @return the patient
     */
    public Patient toPatient(final PatientDTO patientDTO) {

        return new Patient(
        		patientDTO.getLastName(),
        		patientDTO.getFirstName(),
        		patientDTO.getBirthDate(),
                patientDTO.getSex(),
                patientDTO.getAddress(),
                patientDTO.getPhoneNumber());
    }




  	// *******************************************************************



    /**
     * To patient DTO.
     *
     * @param patient the patient
     * @return the patient DTO
     */
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


  	// *******************************************************************



}
