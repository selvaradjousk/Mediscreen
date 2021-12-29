package com.patient.unittest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.patient.dto.PatientDTO;
import com.patient.exception.ResourceNotFoundException;
import com.patient.model.Patient;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;
import com.patient.util.PatientMapper;

@DisplayName("UNIT TESTS - Service - Patient")
@ExtendWith(MockitoExtension.class)
class PatientServiceUpdateTest {


    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    private static Patient patient1, patientUpdated;


    private static PatientDTO patient1DTO, patientUpdatedDTO;



	@BeforeEach
    public void setUp() {
        patient1DTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
        patient1 = new Patient(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    }



	// *******************************************************************	
	
	
	@DisplayName("test UpdatePatient - "
			+ "GIVEN a patient id value "
			+ "WHEN UpdatePatient"
			+ "THEN returns expected updated patient values")
    @Test
    public void testUpdatePatient() {

        patientUpdated = new Patient(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "111 EAST Valley Farms Drive", "111-423-0993");
        
        patientUpdatedDTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "111 EAST Valley Farms Drive", "111-423-0993");
		
        when(patientRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(patient1));
        when(patientMapper.toPatient(any(PatientDTO.class))).thenReturn(new Patient(
        		"Rees", "Pippa", LocalDate.of(1952, 11, 11), "F", "111 EAST Valley Farms Drive", "111-423-0993"));
        when(patientRepository.save(any(Patient.class))).thenReturn(patientUpdated);
        when(patientMapper.toPatientDTO(any(Patient.class))).thenReturn(patientUpdatedDTO);
        

        PatientDTO result = patientService.updatePatient(1, new PatientDTO(
        		"Rees", "Pippa", LocalDate.of(1952, 11, 11), "F", "111 EAST Valley Farms Drive", "111-423-0993"));

        assertEquals(patientUpdatedDTO, result);
        InOrder inOrder = inOrder(patientRepository, patientMapper);
        inOrder.verify(patientRepository).findById(anyInt());
        inOrder.verify(patientMapper).toPatient(any(PatientDTO.class));
        inOrder.verify(patientRepository).save(any(Patient.class));
        inOrder.verify(patientMapper).toPatientDTO(any(Patient.class));
    }

	
  	// *******************************************************************

	@DisplayName("test UpdatePatient - Patient not found- "
			+ "GIVEN a patient not exists "
			+ "WHEN UpdatePatient"
			+ "THEN in returnthrows EXCEPTION")
	  @Test
	  public void testUpdatePatientNotExistsThrowsException(){
	
		when(patientRepository.findById(anyInt())).thenReturn(java.util.Optional.empty());

		assertThrows(ResourceNotFoundException.class, ()
		     		  -> patientService.updatePatient(1, patient1DTO));
  }  


  	// *******************************************************************



	







}
