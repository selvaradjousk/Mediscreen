package com.patient.integration.service;

import static org.assertj.core.api.Assertions.assertThat;
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

@DisplayName("INTEGRATION TESTS - Patient Update")
@SpringBootTest
@ActiveProfiles("test")
class PatientServiceUpdateIT {


	@Autowired
    private PatientService patientService;

    private static PatientDTO patient1DTO, patientUpdatedDTO;



	@BeforeEach
    public void setUp() {
        patient1DTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

	}



	// *******************************************************************	
	
//	
//	@DisplayName("test UpdatePatient - "
//			+ "GIVEN a patient id value "
//			+ "WHEN UpdatePatient"
//			+ "THEN returns expected updated patient values")
//    @Test
//    public void testUpdatePatient() {
//
//		
//        patientUpdatedDTO = new PatientDTO(1, "Rees", "Pippa",
//                LocalDate.of(1952, 11, 11), "F", "111 EAST Valley Farms Drive", "111-423-0993");
//		
//        
//        PatientDTO result = patientService.updatePatient(1, patientUpdatedDTO);
//
//
//        assertThat(result).usingRecursiveComparison()
//						.ignoringCollectionOrder()
//						.isEqualTo(patientUpdatedDTO);
//        
//
//        patientService.deletePatient(1);
//
//    }

	
  	// *******************************************************************

	@DisplayName("test UpdatePatient - Patient not found- "
			+ "GIVEN a patient not exists "
			+ "WHEN UpdatePatient"
			+ "THEN in returnthrows EXCEPTION")
	  @Test
	  public void testUpdatePatientNotExistsThrowsException(){


		assertThrows(ResourceNotFoundException.class, ()
		     		  -> patientService.updatePatient(100, patient1DTO));
  }  


  	// *******************************************************************



	







}
