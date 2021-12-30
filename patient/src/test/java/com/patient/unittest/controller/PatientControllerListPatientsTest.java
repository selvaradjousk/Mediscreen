package com.patient.unittest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

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

import com.patient.controller.PatientController;
import com.patient.dto.PatientDTO;
import com.patient.service.IPatientService;

@DisplayName("UNIT TESTS - Controller - LIST Patient")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
class PatientControllerListPatientsTest {

	
    @MockBean
    private IPatientService patientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private PatientDTO patientDTO;
    

    private final static String PATIENT_LIST_URL = "/patient/list/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }



  	// *******************************************************************



    @Test
    @DisplayName("test GET patient list - without search keyword"
    		+ "Given a patient list,"
    		+ " when getAllPatient without search keyword,"
    		+ " then return Ok status")
    public void testPatientListwithoutKeyword() throws Exception {
        PatientDTO patient2DTO = new PatientDTO(1, "new Reeeees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

        when(patientService.getAllPatients(null)).thenReturn(Arrays.asList(patientDTO, patient2DTO));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(PATIENT_LIST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(patientService).getAllPatients(null);
        assertThat(content).contains("new Reeeees", "Rees");
    }



  	// *******************************************************************



    @Test
    @DisplayName("test GET patient list - with search keyword"
    		+ "Given a patient list,"
    		+ " when getAllPatient with search keyword,"
    		+ " then return Ok status")
    public void testPatientListwithKeyword() throws Exception {
        PatientDTO patient2DTO = new PatientDTO(1, "new Reeeees", "Piiiippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

        when(patientService.getAllPatients("Reeeees")).thenReturn(Arrays.asList(patient2DTO));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(PATIENT_LIST_URL + "?keyword=Reeeees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(patientService).getAllPatients("Reeeees");
        assertThat(content).contains("new Reeeees");
        assertThat(content).doesNotContain("Rees");
    }



  	// *******************************************************************



}
