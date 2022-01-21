package com.patient.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

@DisplayName("INTEGRATION TESTS - Patient GetByLastName")
@SpringBootTest
@ActiveProfiles("test")
class PatientServiceGetByLastNameIT {


	@Autowired
    private PatientService patientService;



    private static PatientDTO patient1DTO;



	@BeforeEach
    public void setUp() {
        patient1DTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");


//        patientService.addPatient(patient1DTO);
//        patientService.addPatient(patient2DTO);
	}



	// *******************************************************************	
	
	
	@DisplayName("test GetPatientByLastName - "
			+ "GIVEN a patient id value "
			+ "WHEN GetPatientByLastName"
			+ "THEN returns expected patient")
    @Test
    public void testGetPatientByLastName() {
		patientService.addPatient(patient1DTO);
//		patientService.addPatient(patient2DTO);

        PatientDTO patientFound = patientService.getPatient("Rees");

        
        assertNotNull(patientFound);
        
        assertEquals("Pippa", patientFound.getFirstName());


    }

	
  	// *******************************************************************

	

	@DisplayName("test GetPatientByLastName - not exists lastname"
			+ "GIVEN a patient lastname not exists "
			+ "WHEN GetPatientByLastName"
			+ "THEN in return throws EXCEPTION")
	  @Test
	  public void testGetPatientLastNameNotExistsThrowsException(){


		assertThrows(ResourceNotFoundException.class, ()
		     		  -> patientService.getPatient("whoareyou"));
  }  


  	// *******************************************************************


	







}
