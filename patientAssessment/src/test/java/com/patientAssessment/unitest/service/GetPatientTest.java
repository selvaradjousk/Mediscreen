package com.patientAssessment.unitest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patientAssessment.dto.NoteDTO;
import com.patientAssessment.dto.PatientDTO;
import com.patientAssessment.proxy.MicroserviceNoteProxy;
import com.patientAssessment.proxy.MicroservicePatientProxy;
import com.patientAssessment.service.AssessmentService;

@DisplayName("UNIT TESTS - Service - PatientAssessment getPatient")
@ExtendWith(MockitoExtension.class)
class GetPatientTest {


    @InjectMocks
    private AssessmentService assessmentService;

    @Mock
    private MicroserviceNoteProxy microserviceNoteProxy;

    @Mock
    private MicroservicePatientProxy microservicePatientProxy;



	// *******************************************************************	
	

	@DisplayName("getPatient - "
			+ "GIVEN a patient "
			+ "WHEN getPatient"
			+ "THEN returns expected patient")
    @Test
    public void testGetPatient() {

		PatientDTO patientDTO = new PatientDTO(1, "LastName1", "FirstName1",
                LocalDate.of(1991,8,1), "M", "Address1", "1111111111");

		when(microservicePatientProxy.getPatientById(1)).thenReturn(patientDTO);

        PatientDTO result = assessmentService.getPatient(1);

        assertEquals("FirstName1", result.getFirstName());
        assertEquals("LastName1", result.getLastName());

    }



	// *******************************************************************	


	@DisplayName("getPatient by lastName- "
			+ "GIVEN a patient lastName"
			+ "WHEN getPatient"
			+ "THEN returns expected patient")
    @Test
    public void testGetPatientByLastName() {

		PatientDTO patientDTO = new PatientDTO(1, "LastName1", "FirstName1",
                LocalDate.of(1991,8,1), "M", "Address1", "1111111111");

		when(microservicePatientProxy.getPatient("LastName1")).thenReturn(patientDTO);

        PatientDTO result = assessmentService.getPatient("LastName1");

        assertEquals("FirstName1", result.getFirstName());
        assertEquals("LastName1", result.getLastName());

    }



	// *******************************************************************	







}
