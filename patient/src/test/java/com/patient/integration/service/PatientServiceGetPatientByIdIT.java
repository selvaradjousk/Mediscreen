package com.patient.integration.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.patient.dto.PatientDTO;
import com.patient.exception.ResourceNotFoundException;
import com.patient.service.PatientService;

@DisplayName("INTEGRATION TESTS - Patient GetById")
@SpringBootTest
@ActiveProfiles("test")
class PatientServiceGetPatientByIdIT {


	@Autowired
    private PatientService patientService;



    private static PatientDTO patient1DTO;



	@BeforeEach
    public void setUp() {


    }



	// *******************************************************************	
	
//	
//	@DisplayName("test GetPatientById - "
//			+ "GIVEN a patient id value "
//			+ "WHEN getPatientById"
//			+ "THEN returns expected patient")
//    @Test
//    public void testGetPatientById() {
//
//
//        patient1DTO = new PatientDTO(1, "Rees", "Pippa",
//                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
//		
//
//        PatientDTO patientFound = patientService.getPatientById(1);
//
//        
//        assertThat(patientFound).usingRecursiveComparison()
//						.ignoringCollectionOrder()
//						.isEqualTo(patient1DTO);
//
//        patientService.deletePatient(1);
//
//    }

	
  	// *******************************************************************

	@DisplayName("test GetPatientById - patient not found"
			+ "GIVEN a patient id not exists "
			+ "WHEN getPatientById"
			+ "THEN in return throws EXCEPTION")
	  @Test
	  public void testGetPatientByIdNotExistsThrowsException(){
	

		assertThrows(ResourceNotFoundException.class, ()
		     		  -> patientService.getPatientById(100));
  }  


  	// *******************************************************************



	







}
