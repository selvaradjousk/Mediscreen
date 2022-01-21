package com.patient.unittest.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patient.dto.PatientDTO;
import com.patient.exception.MultiplePatientsLastNameException;
import com.patient.exception.ResourceNotFoundException;
import com.patient.model.Patient;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;
import com.patient.util.PatientMapper;

@DisplayName("UNIT TESTS - Service - Patient GetByLastName")
@ExtendWith(MockitoExtension.class)
class PatientServiceGetByLastNameTest {


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
	
	
	@DisplayName("test GetPatientByLastName - "
			+ "GIVEN a patient id value "
			+ "WHEN GetPatientByLastName"
			+ "THEN returns expected patient")
    @Test
    public void testGetPatientByLastName() {

        when(patientRepository.countByLastName(anyString())).thenReturn(1);
        when(patientRepository.findByLastName(anyString())).thenReturn(patient1);
        lenient().when(patientMapper.toPatientDTO(any(Patient.class))).thenReturn(patient1DTO);

        PatientDTO patientFound = patientService.getPatient("Rees");

        
        assertNotNull(patientFound);

//        InOrder inOrder = inOrder(patientRepository, patientMapper);
//        inOrder.verify(patientRepository).countByLastName(anyString());
//        inOrder.verify(patientMapper).toPatientDTO(any(Patient.class));
    }

	
  	// *******************************************************************

	@DisplayName("test GetPatientByLastName - more same lastname"
			+ "GIVEN a patient lastname more than one "
			+ "WHEN GetPatientByLastName"
			+ "THEN in return throws EXCEPTION")
	  @Test
	  public void testGetPatientMoreSameLastNameExistsThrowsException(){
	
		when(patientRepository.countByLastName(anyString())).thenReturn(2);

		assertThrows(MultiplePatientsLastNameException.class, ()
		     		  -> patientService.getPatient(patient1DTO.getLastName()));
  }  


  	// *******************************************************************


	@DisplayName("test GetPatientByLastName - not exists lastname"
			+ "GIVEN a patient lastname not exists "
			+ "WHEN GetPatientByLastName"
			+ "THEN in return throws EXCEPTION")
	  @Test
	  public void testGetPatientLastNameNotExistsThrowsException(){
	
		when(patientRepository.countByLastName(anyString())).thenReturn(0);

		assertThrows(ResourceNotFoundException.class, ()
		     		  -> patientService.getPatient(patient1DTO.getLastName()));
  }  


  	// *******************************************************************


	







}
