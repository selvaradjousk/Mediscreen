package com.patient.integration.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.dto.PatientDTO;

@DisplayName("INTEGRATION TESTS - Controller - Get Patient By Id")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PatientControllerGetPatientByIdIT {

    @Autowired
    private TestRestTemplate restTemplate;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
    
    @LocalServerPort
    private int port;

    private PatientDTO patientDTO1, patientDTO2;
	List<PatientDTO> listPatient;

    private final static String PATIENT_ADD_URL = "/patient/add/";
    private final static String PATIENT_GET_URL = "/patient/get/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO1 = new PatientDTO(1, "Rees1", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    	patientDTO2 = new PatientDTO(2, "Rees2", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    	listPatient = new ArrayList<>();
		listPatient.add(patientDTO1);
		listPatient.add(patientDTO2);

		
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test GET PatientById - "
    		+ " Given a Patient with id,"
    		+ " when request for getPatientById,"
    		+ " then return status 200 Ok")
    public void testGetPatientById() throws Exception {


		restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO1, PatientDTO.class);	
		
		restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO2, PatientDTO.class);	
    	
    	MvcResult response =  mockMvc
				.perform(get("http://localhost:" + port +
                PATIENT_GET_URL + "1"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andReturn();
    	
		PatientDTO patientResult = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<PatientDTO>() {});
		assertNotNull(patientResult);
		assertEquals("Pippa", patientResult.getFirstName());
		assertEquals("Rees1", patientResult.getLastName());

    }


  	// *******************************************************************

    
    @Test
	@DisplayName("test GetPatientById - patient id null"
			+ "GIVEN a patient id null "
			+ "WHEN getPatientById"
			+ "THEN in return BAD_REQUEST")
	  public void testGetPatientByIdNullhrowsException() throws Exception{

    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders
    			.get(PATIENT_GET_URL + null)
                .contentType(MediaType.APPLICATION_JSON))
    			.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Invalid id");
    }

  	// *******************************************************************


    
    @Test
	@DisplayName("test GetPatientById - patient id invalid character input"
			+ "GIVEN a patient invalid  "
			+ "WHEN getPatientById"
			+ "THEN in return BAD_REQUEST")
	  public void testGetPatientByIdInvalid() throws Exception{

    	mockMvc.perform(MockMvcRequestBuilders
    			.get(PATIENT_GET_URL)
                .contentType(MediaType.APPLICATION_JSON))
    			.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

    }

  	// *******************************************************************

   

}
