package com.patient.integration.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

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

import com.patient.dto.PatientDTO;

@DisplayName("INTEGRATION TESTS - Controller - GetByLastName")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PatientControllerGetPatientByLastNameIT {


    @Autowired
    private TestRestTemplate restTemplate;

	@Autowired
	private MockMvc mockMvc;

    
    @LocalServerPort
    private int port;

    private PatientDTO patientDTO1;


    private final static String PATIENT_ADD_URL = "/patient/add/";
    private final static String PATIENT_GET_URL = "/patient/getByFamilyName";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO1 = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

		restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO1, PatientDTO.class);	
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test GET GetByLastName - "
    		+ " Given a Patient with lastName,"
    		+ " when request for GetByLastName,"
    		+ " then return status 200 Ok")
    public void testGetPatientByLastName() throws Exception {


    	MvcResult result = mockMvc.perform(get(PATIENT_GET_URL + "?lastName=Rees")
                .contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Rees");

    }


  	// *******************************************************************

}
