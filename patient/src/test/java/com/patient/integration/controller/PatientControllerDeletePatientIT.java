package com.patient.integration.controller;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.patient.dto.PatientDTO;

@DisplayName("INTEGRATION TESTS - Controller - Delete Patient")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PatientControllerDeletePatientIT {


	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;
	
	
    private PatientDTO patientDTO1;


    private final static String PATIENT_DELETE_URL = "/patient/delete/";
    private final static String PATIENT_ADD_URL = "/patient/add/";




  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO1 = new PatientDTO(1, "Rees1", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");


		restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO1, PatientDTO.class);	

    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test DELETE PatientById - "
    		+ " Given a Patient with id,"
    		+ " when request for deletePatient,"
    		+ " then return status 200 Ok")
    public void testDeletePatient() throws Exception {

    	// Delete status is OK - successful
    	mockMvc
				.perform(get("http://localhost:" + port +
        		PATIENT_DELETE_URL + "1", PatientDTO.class))
				.andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andReturn();
    	
    	// delete second time id does not exists
    	mockMvc
		.perform(get("http://localhost:" + port +
		PATIENT_DELETE_URL + "1", PatientDTO.class))
		.andExpect(status().isNotFound())
		.andReturn();


    }


  	// *******************************************************************

   

}
