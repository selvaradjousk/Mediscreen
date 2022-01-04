package com.patient.unittest.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patient.exception.ResourceNotFoundException;
import com.patient.model.Patient;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;
import com.patient.util.PatientMapper;

@DisplayName("UNIT TESTS - Service - Patient Delete")
@ExtendWith(MockitoExtension.class)
class PatientServiceDeleteTest {


    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    private static Patient patient1;



	@BeforeEach
    public void setUp() {
        patient1 = new Patient(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    }



	// *******************************************************************	
	
	
	@DisplayName("test testDeletePatienttById - "
			+ "GIVEN a patient id value "
			+ "WHEN deletePatient"
			+ "THEN returns expected patient")
    @Test
    public void testDeletePatientById() {

        when(patientRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(patient1));

        patientService.deletePatient(anyInt());

        InOrder inOrder = inOrder(patientRepository);
        inOrder.verify(patientRepository).findById(anyInt());
        inOrder.verify(patientRepository).deleteById(anyInt());
    }

	
  	// *******************************************************************

	@DisplayName("test DeletePatient - patient not found"
			+ "GIVEN a patient id not exists "
			+ "WHEN DeletePatient"
			+ "THEN in return throws EXCEPTION")
	  @Test
	  public void testDeletePatientNotExistsThrowsException(){
	
        when(patientRepository.findById(anyInt())).thenReturn(java.util.Optional.empty());

		assertThrows(ResourceNotFoundException.class, ()
		     		  -> patientService.deletePatient(anyInt()));
  }  


  	// *******************************************************************



	







}
