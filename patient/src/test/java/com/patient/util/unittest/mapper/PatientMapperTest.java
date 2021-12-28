package com.patient.util.unittest.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.patient.dto.PatientDTO;
import com.patient.model.Patient;
import com.patient.util.PatientMapper;

@DisplayName("UNIT TESTS - MAPPER - PATIENT")
class PatientMapperTest {


    private PatientMapper patientMapper = new PatientMapper();

    @Test
    @DisplayName("test toPatient - "
    		+ "Given an PatientDTO,"
    		+ "when ToPatient DO,"
    		+ " then return result as expected Patient DO")
    public void testToPatientDO() {

    	Patient expectedPatient = new Patient(
    			"Ferguson",
    			"Lucas",
                LocalDate.of(1968,06,22),
                "M",
                "2 Warren Street", "387-866-1399");

    	PatientDTO patientDTO = new PatientDTO(
    			"Ferguson",
    			"Lucas",
                LocalDate.of(1968,06,22),
                "M",
                "2 Warren Street",
                "387-866-1399");

        Patient result = patientMapper.toPatient(patientDTO);

        assertThat(result).usingRecursiveComparison()
        					.ignoringCollectionOrder()
        					.isEqualTo(expectedPatient);
    }
    
    
    @Test
    @DisplayName("test toPatientDTO - "
    		+ "Given an Patient,"
    		+ "when ToPatientDTO,"
    		+ " then return result as expected PatientDTO")
    public void testToPatientDTO() {

    	PatientDTO expectedPatientDTO = new PatientDTO(
    			1,
    			"Ferguson",
    			"Lucas",
                LocalDate.of(1968,06,22),
                "M", "11 Warren Street",
                "387-866-1399");

    	Patient Patient = new Patient(
    			1,
    			"Ferguson",
    			"Lucas",
                LocalDate.of(1968,06,22),
                "M",
                "11 Warren Street",
                "387-866-1399");

        PatientDTO result = patientMapper.toPatientDTO(Patient);

        assertThat(result).usingRecursiveComparison()
						.ignoringCollectionOrder()
						.isEqualTo(expectedPatientDTO);
    }
}
