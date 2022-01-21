package com.patient.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patient.dto.PatientDTO;
import com.patient.model.Patient;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;
import com.patient.util.PatientMapper;

@DisplayName("INTEGRATION TESTS - Patient List")
@ExtendWith(MockitoExtension.class)
class PatientServiceGetListTest {


    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    private static Patient patient1, patient2;


    private static PatientDTO patient1DTO, patient2DTO;


    private static List<PatientDTO> patientListDTO;

	@BeforeEach
    public void setUp() {
        patient1DTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
        patient2DTO = new PatientDTO(2, "Ferguson", "Lucas",
                LocalDate.of(1968, 06, 22), "M", "2 Warren Street", "387-866-1399");
        patient1 = new Patient(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
        patient2 = new Patient(2, "Ferguson", "Lucas",
                LocalDate.of(1968, 06, 22), "M", "2 Warren Street", "387-866-1399");
        patientListDTO = Arrays.asList(patient1DTO, patient2DTO);
    }



	// *******************************************************************	


	@DisplayName("test getAllPatients - search keyword empty"
			+ "GIVEN patient list "
			+ "WHEN getAllPatients without search keyword"
			+ "THEN returns expected number of patients ")
    @Test
    public void testGetAllPatientNoSearchKeyword() {

		when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));
        when(patientMapper.toPatientDTO(patient1)).thenReturn(patient1DTO);
        when(patientMapper.toPatientDTO(patient2)).thenReturn(patient2DTO);
//        patientnewListDTO = Arrays.asList(patient1DTO, patient2DTO);

        List<PatientDTO> result = patientService.getAllPatients(null);

        assertEquals(patientListDTO.size(), result.size());
        InOrder inOrder = inOrder(patientRepository, patientMapper);
        inOrder.verify(patientRepository).findAll();
        inOrder.verify(patientMapper).toPatientDTO(patient1);
    }


	// *******************************************************************	




	@DisplayName("test getAllPatients - with search keyword"
			+ "GIVEN patient list "
			+ "WHEN getAllPatients with search keyword"
			+ "THEN returns expected patient searched by keyword ")
    @Test
    public void testGetAllPatientWithSearchKeyword() {

        when(patientRepository.findByKeyword(anyString())).thenReturn(Arrays.asList(patient1));
        when(patientMapper.toPatientDTO(patient1)).thenReturn(patient1DTO);

        List<PatientDTO> result = patientService.getAllPatients(anyString());

        assertEquals(1, result.size());
        InOrder inOrder = inOrder(patientRepository, patientMapper);
        inOrder.verify(patientRepository).findByKeyword(anyString());
        inOrder.verify(patientMapper).toPatientDTO(patient1);
    }


	// *******************************************************************	




}
