package com.patient.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.lenient;
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
import com.patient.exception.DataAlreadyRegisteredException;
import com.patient.model.Patient;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;
import com.patient.util.PatientMapper;

@DisplayName("UNIT TESTS - Service - Patient Add")
@ExtendWith(MockitoExtension.class)
class PatientServiceAddTest {


    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    private static Patient patient1, patientToAdd;


    private static PatientDTO patient1DTO, patientToAddDTO;



	@BeforeEach
    public void setUp() {
        patient1DTO = new PatientDTO("Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
        patient1 = new Patient("Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    }



	// *******************************************************************	
	
	
	@DisplayName("test AddPatient - "
			+ "GIVEN a patient id value "
			+ "WHEN AddPatient"
			+ "THEN returns expected updated patient values")
    @Test
    public void testAddPatient() {

        patientToAdd = new Patient("Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
        
        patientToAddDTO = new PatientDTO("Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
		
        lenient().when(patientRepository.findByLastNameAndFirstNameAndBirthDate(anyString(), anyString(), any(LocalDate.class))).thenReturn(null);
        when(patientMapper.toPatient(any(PatientDTO.class))).thenReturn(new Patient(
        		"Rees", "Pippa", LocalDate.of(1952, 11, 11), "F", "111 EAST Valley Farms Drive", "111-423-0993"));
        lenient().when(patientRepository.save(any(Patient.class))).thenReturn(patientToAdd);
        when(patientMapper.toPatientDTO(any(Patient.class))).thenReturn(patientToAddDTO);
        

        PatientDTO result = patientService.addPatient(patientToAddDTO);

        assertThat(result).usingRecursiveComparison()
						.ignoringCollectionOrder()
						.isEqualTo(patient1DTO);
        
        InOrder inOrder = inOrder(patientRepository, patientMapper);
//        inOrder.verify(patientRepository.findByLastNameFirstNameBirthDate(anyString(), anyString(), any(LocalDate.class)));
        inOrder.verify(patientMapper).toPatient(any(PatientDTO.class));
        inOrder.verify(patientRepository).save(any(Patient.class));
        inOrder.verify(patientMapper).toPatientDTO(any(Patient.class));
    }

	
  	// *******************************************************************

	@DisplayName("test AddPatient - Patient not found- "
			+ "GIVEN a patient not exists "
			+ "WHEN AddPatient"
			+ "THEN in returnthrows EXCEPTION")
	  @Test
	  public void testAddPatientNotExistsThrowsException(){
	
		when(patientRepository.findByLastNameAndFirstNameAndBirthDate(anyString(), anyString(), any(LocalDate.class))).thenReturn(patient1);

		assertThrows(DataAlreadyRegisteredException.class, ()
		     		  -> patientService.addPatient(patient1DTO));
  }  


  	// *******************************************************************



	







}
