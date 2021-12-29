package com.patient.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;
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

@DisplayName("UNIT TESTS - Service - Patient GetById")
@ExtendWith(MockitoExtension.class)
class PatientServiceGetByIdTest {


    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    private static Patient patient1;


    private static PatientDTO patient1DTO;



	@BeforeEach
    public void setUp() {
        patient1DTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
        patient1 = new Patient(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    }



	// *******************************************************************	
	
	
	@DisplayName("test GetPatientById - "
			+ "GIVEN a patient id value "
			+ "WHEN getPatientById"
			+ "THEN returns expected patient")
    @Test
    public void testGetPatientById() {

        when(patientRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(patient1));
        when(patientMapper.toPatientDTO(any(Patient.class))).thenReturn(patient1DTO);

        PatientDTO patientFound = patientService.getPatientById(1);

        
        assertThat(patientFound).usingRecursiveComparison()
						.ignoringCollectionOrder()
						.isEqualTo(patient1DTO);

        InOrder inOrder = inOrder(patientRepository, patientMapper);
        inOrder.verify(patientRepository).findById(anyInt());
        inOrder.verify(patientMapper).toPatientDTO(any(Patient.class));
    }

	
  	// *******************************************************************

	@DisplayName("test GetPatientById - patient not found"
			+ "GIVEN a patient id not exists "
			+ "WHEN getPatientById"
			+ "THEN in return throws EXCEPTION")
	  @Test
	  public void testGetPatientByIdNotExistsThrowsException(){
	
		when(patientRepository.findById(anyInt())).thenReturn(java.util.Optional.empty());

		assertThrows(ResourceNotFoundException.class, ()
		     		  -> patientService.getPatientById(1));
  }  


  	// *******************************************************************



	







}
