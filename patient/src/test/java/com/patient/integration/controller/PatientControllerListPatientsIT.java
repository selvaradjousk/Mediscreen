package com.patient.integration.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.patient.dto.PatientDTO;

@DisplayName("INTEGRATION TESTS - Controller - LIST Patient")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PatientControllerListPatientsIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;

    private PatientDTO patientDTO1, patientDTO2;
	List<PatientDTO> listPatient;


    private final static String PATIENT_ADD_URL = "/patient/add/";
    

    private final static String PATIENT_LIST_URL = "/patient/list/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO1 = new PatientDTO(1, "Rees1", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    	patientDTO2 = new PatientDTO(1, "Rees2", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    	listPatient = new ArrayList<>();
		listPatient.add(patientDTO1);
		listPatient.add(patientDTO2);

		restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO1, PatientDTO.class);	
		
		restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO2, PatientDTO.class);	
    }



  	// *******************************************************************



    @Test
    @DisplayName("test GET patient list - without search keyword"
    		+ "Given a patient list,"
    		+ " when getAllPatient without search keyword,"
    		+ " then return Ok status")
    public void testPatientListwithoutKeyword() throws Exception {


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(PATIENT_LIST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Rees1", "Rees2");
    }



  	// *******************************************************************



    @Test
    @DisplayName("test GET patient list - with search keyword"
    		+ "Given a patient list,"
    		+ " when getAllPatient with search keyword,"
    		+ " then return Ok status")
    public void testPatientListwithKeyword() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(PATIENT_LIST_URL + "?keyword=Rees1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Rees1");
        assertThat(content).doesNotContain("Rees2");
    }



  	// *******************************************************************



}
