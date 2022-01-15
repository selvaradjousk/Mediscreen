package com.patientAssessment.unittest.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.patientAssessment.controller.AssessmentController;
import com.patientAssessment.dto.AssessmentDTO;
import com.patientAssessment.dto.PatientDTO;
import com.patientAssessment.service.IAssessmentService;

@DisplayName("UNIT TESTS - Controller - getPatientAssessment")
@ExtendWith(SpringExtension.class)
@WebMvcTest(AssessmentController.class)
class AssessmentControllerTest {
    @MockBean
    private IAssessmentService assessmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    // URL
    private final static String ASSESS_GET_URL = "/assess/";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }



	// *******************************************************************	
	

	@DisplayName("getDiabetesRiskLevel: FergusonAsBorderline - "
			+ "GIVEN a patient Ferguson "
			+ "WHEN getDiabetesRiskLevel"
			+ "THEN returns expected DiabetesRiskLevel of the Ferguson As Borderline")
	@Test
    public void testGetPatientAssessment() throws Exception {

		AssessmentDTO assessmentDTO = new AssessmentDTO(
				new PatientDTO(1, "Ferguson", "Lucas",
                LocalDate.of(1968,06,22), "M", "2 Warren Street ", "387-866-1399"),
				"BORDELINE");

		when(assessmentService
				.getPatientAssessment(1))
		.thenReturn(assessmentDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
        		.get(ASSESS_GET_URL + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse()
        		.getContentAsString();

        assertThat(content).contains("BORDELINE");
        verify(assessmentService).getPatientAssessment(1);
    }


	// *******************************************************************	
	

}
