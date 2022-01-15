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

import com.patientAssessment.dto.AssessmentDTO;
import com.patientAssessment.dto.NoteDTO;
import com.patientAssessment.dto.PatientDTO;
import com.patientAssessment.proxy.MicroserviceNoteProxy;
import com.patientAssessment.proxy.MicroservicePatientProxy;
import com.patientAssessment.service.AssessmentService;

@DisplayName("UNIT TESTS - Service - PatientAssessment GetPatientAssessment")
@ExtendWith(MockitoExtension.class)
class GetPatientAssessmentTest {


    @InjectMocks
    private AssessmentService assessmentService;

    @Mock
    private MicroserviceNoteProxy microserviceNoteProxy;

    @Mock
    private MicroservicePatientProxy microservicePatientProxy;



	// *******************************************************************	
	

	@DisplayName("getPatientAssessment: FergusonAsBorderline - "
			+ "GIVEN a patient Ferguson "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the Ferguson As Borderline")
    @Test
    public void GetPatientAssessmentForFergusonAsBorderlineTest() {


		PatientDTO patientDTO = new PatientDTO(1, "Ferguson", "Lucas",
                LocalDate.of(1968,06,22), "M", "2 Warren Street ", "387-866-1399");
        NoteDTO note1DTO = new NoteDTO("1", 1, LocalDate.of(1990,01,01), "The patient reports that he feels very well. Weight equal to or less than the recommended weight");
        NoteDTO note2DTO = new NoteDTO("2", 1, LocalDate.of(1991,01,01), "Patient reports feeling tired during the day He also complains of muscle aches Laboratory tests indicating high microalbumin ");
        NoteDTO note3DTO = new NoteDTO("3", 1, LocalDate.of(1990,01,01), "Patient says he doesn't feel that tired Smoker, quit within past 12 months Lab tests showing antibodies are high ");
        List<NoteDTO> noteslist = Arrays.asList(note1DTO, note2DTO, note3DTO);
        when(microserviceNoteProxy.getAllNote(1)).thenReturn(noteslist);
        when(microservicePatientProxy.getPatientById(1)).thenReturn(patientDTO);

        AssessmentDTO assessmentDTO = assessmentService.getPatientAssessment(1);

        assertEquals("Borderline", assessmentDTO.getDiabetesRiskLevel());


    }



	// *******************************************************************	

	

	@DisplayName("GetPatientAssessment: ReesAsBorderline - "
			+ "GIVEN a patient Rees "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the Rees As Borderline")
    @Test
    public void GetPatientAssessmentForReesAsBorderlineTest() {


		PatientDTO patientDTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952,9,27), "F", "745 West Valley Farms Drive ", "628-423-0993");
        NoteDTO note1DTO = new NoteDTO("1", 1, LocalDate.of(1990,01,01), "The patient reports that he feels a lot of stress at work He also complains that his hearing is abnormal lately. ");
        NoteDTO note2DTO = new NoteDTO("2", 1, LocalDate.of(1991,01,01), "Patient reports having had a reaction to medication in the last 3 months He also notices that his hearing continues to be abnormal. ");
        NoteDTO note3DTO = new NoteDTO("3", 1, LocalDate.of(1990,01,01), "Laboratory tests indicating high microalbumin");
        NoteDTO note4DTO = new NoteDTO("4", 1, LocalDate.of(1990,01,01), "The patient declares that everything seems to be going well Lab reports hemoglobin A1C exceeds recommended level The patient declares that he has smoked for a long time ");
        List<NoteDTO> noteslist = Arrays.asList(note1DTO, note2DTO, note3DTO, note4DTO);
        when(microserviceNoteProxy.getAllNote(1)).thenReturn(noteslist);
        when(microservicePatientProxy.getPatientById(1)).thenReturn(patientDTO);

        AssessmentDTO assessmentDTO = assessmentService.getPatientAssessment(1);

        assertEquals("Borderline", assessmentDTO.getDiabetesRiskLevel());


    }



	// *******************************************************************	


	

	@DisplayName("GetPatientAssessment: PatientTestNone - "
			+ "GIVEN a patient TestNone "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the PatientTestNone as None")
    @Test
    public void GetPatientAssessmentForPatientTestNoneTest() {


		PatientDTO patientDTO = new PatientDTO(1, "TestNone", "Test",
                LocalDate.of(1952,12,31), "F", "1 Brookside St&phone", "100-222-3333");
        NoteDTO note1DTO = new NoteDTO("1", 1, LocalDate.of(1966,01,01), " Patient states that they are feeling terrific Weight at or below recommended level");
       
        List<NoteDTO> noteslist = Arrays.asList(note1DTO);
        when(microserviceNoteProxy.getAllNote(1)).thenReturn(noteslist);
        when(microservicePatientProxy.getPatientById(1)).thenReturn(patientDTO);

        AssessmentDTO assessmentDTO = assessmentService.getPatientAssessment(1);

        assertEquals("None", assessmentDTO.getDiabetesRiskLevel());


    }



	// *******************************************************************	


	@DisplayName("GetPatientAssessment: PatientTestBorderline - "
			+ "GIVEN a patient TestBorderline "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the Patient TestBorderline as Borderline")
    @Test
    public void GetPatientAssessmentForPatientTestBorderlineTest() {


		PatientDTO patientDTO = new PatientDTO(1, "TestBorderline", "Test",
                LocalDate.of(1945,06,24), "M", "2 High St&phone", "200-333-4444");
        NoteDTO note1DTO = new NoteDTO("1", 1, LocalDate.of(1966,01,01), "Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late");
        NoteDTO note2DTO = new NoteDTO("2", 1, LocalDate.of(1966,01,01), "Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic");
       
        List<NoteDTO> noteslist = Arrays.asList(note1DTO, note2DTO);
        when(microserviceNoteProxy.getAllNote(1)).thenReturn(noteslist);
        when(microservicePatientProxy.getPatientById(1)).thenReturn(patientDTO);

        AssessmentDTO assessmentDTO = assessmentService.getPatientAssessment(1);

        assertEquals("Borderline", assessmentDTO.getDiabetesRiskLevel());


    }



	// *******************************************************************	


	@DisplayName("GetPatientAssessment: Patient TestInDanger - "
			+ "GIVEN a patient TestInDanger "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the Patient TestInDanger as In danger")
    @Test
    public void GetPatientAssessmentForPatientTestInDangerTest() {


		PatientDTO patientDTO = new PatientDTO(1, "TestInDanger", "Test",
                LocalDate.of(2004,06,18), "M", "3 Club Road&phone", "300-444-5555");
        NoteDTO note1DTO = new NoteDTO("1", 1, LocalDate.of(1966,01,01), "Patient states that they are short term Smoker");
        NoteDTO note2DTO = new NoteDTO("2", 1, LocalDate.of(1966,01,01), "Patient states that they quit within last year Patient also complains that of Abnormal breathing spells Lab reports Cholesterol LDL high");
       
        List<NoteDTO> noteslist = Arrays.asList(note1DTO, note2DTO);

        when(microserviceNoteProxy.getAllNote(1)).thenReturn(noteslist);
        when(microservicePatientProxy.getPatientById(1)).thenReturn(patientDTO);

        AssessmentDTO assessmentDTO = assessmentService.getPatientAssessment(1);

        assertEquals("In danger", assessmentDTO.getDiabetesRiskLevel());

    }



	// *******************************************************************	


	@DisplayName("GetPatientAssessment: Patient TestEarlyOnset - "
			+ "GIVEN a patient TestEarlyOnset "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the Patient TestEarlyOnset as Early onset")
    @Test
    public void GetPatientAssessmentForPatientTestEarlyOnsetTest() {


		PatientDTO patientDTO = new PatientDTO(1, "TestEarlyOnset", "Test",
                LocalDate.of(2002,06,28), "F", "4 Valley Dr&phone=", "400-555-6666");
        NoteDTO note1DTO = new NoteDTO("1", 1, LocalDate.of(1966,01,01), "Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication");
        NoteDTO note2DTO = new NoteDTO("2", 1, LocalDate.of(1966,01,01), "Patient states that they are experiencing back pain when seated for a long time");
        NoteDTO note3DTO = new NoteDTO("3", 1, LocalDate.of(1966,01,01), "Patient states that they are a short term Smoker Hemoglobin A1C above recommended level");
        NoteDTO note4DTO = new NoteDTO("4", 1, LocalDate.of(1966,01,01), "Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction");
       
        List<NoteDTO> noteslist = Arrays.asList(note1DTO, note2DTO, note3DTO, note4DTO);
        when(microserviceNoteProxy.getAllNote(1)).thenReturn(noteslist);
        when(microservicePatientProxy.getPatientById(1)).thenReturn(patientDTO);

        AssessmentDTO assessmentDTO = assessmentService.getPatientAssessment(1);

        assertEquals("Early onset", assessmentDTO.getDiabetesRiskLevel());



    }



	// *******************************************************************	

}
