package com.patient.integration.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.patient.dto.PatientDTO;
import com.patient.exception.ResourceNotFoundException;
import com.patient.service.PatientService;

@DisplayName("INTEGRATION TESTS - Service - Patient Delete")
@SpringBootTest
@ActiveProfiles("test")
class PatientServiceDeleteIT {


	@Autowired
    private PatientService patientService;


    private static PatientDTO patientDTO1;



	@BeforeEach
    public void setUp() {


        patientDTO1 = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");


	}



	// *******************************************************************	
	
	
	@DisplayName("test testDeletePatienttById - "
			+ "GIVEN a patient id value "
			+ "WHEN deletePatient"
			+ "THEN returns expected patient")
    @Test
    public void testDeletePatientById() {


        if(patientService.getPatientById(1) != null) {
        }
        else {
        patientService.addPatient(patientDTO1);
        }
		
        patientService.deletePatient(1);

    }

	
  	// *******************************************************************

	@DisplayName("test DeletePatient - patient not found"
			+ "GIVEN a patient id not exists "
			+ "WHEN DeletePatient"
			+ "THEN in return throws EXCEPTION")
	  @Test
	  public void testDeletePatientNotExistsThrowsException(){


		assertThrows(ResourceNotFoundException.class, ()
		     		  -> patientService.deletePatient(100));
  }  


  	// *******************************************************************



}
