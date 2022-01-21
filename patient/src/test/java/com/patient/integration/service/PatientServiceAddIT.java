package com.patient.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@DisplayName("INTEGRATION TESTS - Service - Patient Add")
@SpringBootTest
@ActiveProfiles("test")
class PatientServiceAddIT {


	@Autowired
    private PatientService patientService;


    private static PatientDTO patient1DTO, patientToAddDTO;



	@BeforeEach
    public void setUp() {
        patient1DTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");


    }



	// *******************************************************************	
	
	
	@DisplayName("test AddPatient - "
			+ "GIVEN a patient  "
			+ "WHEN AddPatient"
			+ "THEN returns expected updated patient values")
    @Test
    public void testAddPatient() {

        	PatientDTO result = patientService.addPatient(new PatientDTO("Rees", "Pippa",
                    LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993"));

            assertEquals(patient1DTO.getFirstName(), result.getFirstName());



        

    }

	
  	// *******************************************************************

//	@DisplayName("test AddPatient - Patient not found- "
//			+ "GIVEN a patient not exists "
//			+ "WHEN AddPatient"
//			+ "THEN in returnthrows EXCEPTION")
//	  @Test
//	  public void testAddPatientNotExistsThrowsException(){
//	
//
//        patientService.deletePatient(1);
//        	patientService.addPatient(patientToAddDTO);
//
//		assertThrows(ResourceNotFoundException.class, ()
//		     		  -> patientService.addPatient(patient1DTO));
//
//        patientService.deletePatient(1);
//	
//	}  


  	// *******************************************************************



	







}
