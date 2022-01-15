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
import com.patientAssessment.proxy.MicroserviceNoteProxy;
import com.patientAssessment.proxy.MicroservicePatientProxy;
import com.patientAssessment.service.AssessmentService;

@DisplayName("UNIT TESTS - Service - PatientAssessment getPatientsNotes")
@ExtendWith(MockitoExtension.class)
class GetPatientNotesTest {


    @InjectMocks
    private AssessmentService assessmentService;

    @Mock
    private MicroserviceNoteProxy microserviceNoteProxy;

    @Mock
    private MicroservicePatientProxy microservicePatientProxy;



	// *******************************************************************	
	

	@DisplayName("getPatientsNotes - "
			+ "GIVEN all notes of patient "
			+ "WHEN getPatientsNotes"
			+ "THEN returns expected all notes")
    @Test
    public void testGetPatientsNotes() {

//		PatientDTO patientDTO = new PatientDTO(1, "LastName1", "FirstName1",
//                LocalDate.of(1991,8,1), "M", "Address1", "1111111111");
        NoteDTO note1DTO = new NoteDTO("1", 1, LocalDate.of(1990,01,01), "note1");
        NoteDTO note2DTO = new NoteDTO("2", 1, LocalDate.of(1991,01,01), "note2");
        when(microserviceNoteProxy.getAllNote(1)).thenReturn(Arrays.asList(note1DTO, note2DTO));

        List<NoteDTO> patientNotesDTO = assessmentService.getPatientNotes(1);

        assertEquals("note1", patientNotesDTO.get(0).getNote());
        assertEquals("note2", patientNotesDTO.get(1).getNote());

    }



	// *******************************************************************	

}
