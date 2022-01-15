package com.patientAssessment.unitest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

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

@DisplayName("UNIT TESTS - Service - PatientAssessment getPatientTriggers")
@ExtendWith(MockitoExtension.class)
class GetPatientTriggersTest {


    @InjectMocks
    private AssessmentService assessmentService;

    @Mock
    private MicroserviceNoteProxy microserviceNoteProxy;

    @Mock
    private MicroservicePatientProxy microservicePatientProxy;



	// *******************************************************************	
	

	@DisplayName("getPatientTriggers - "
			+ "GIVEN all notes of patient "
			+ "WHEN getPatientTriggers"
			+ "THEN returns expected all Trigger Count")
    @Test
    public void testGetPatientTriggers() {


        NoteDTO note1DTO = new NoteDTO("1", 1, LocalDate.of(1990,01,01), "Smoker");
        NoteDTO note2DTO = new NoteDTO("2", 1, LocalDate.of(1991,01,01), "Cholesterol");
        List<NoteDTO> noteslist = Arrays.asList(note1DTO, note2DTO);
        lenient().when(microserviceNoteProxy.getAllNote(1)).thenReturn(noteslist);

        int triggerCount = assessmentService.getPatientTriggers(noteslist);

        assertEquals(2, triggerCount);

    }



	// *******************************************************************	

}
