package com.patient.integration.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.dto.PatientDTO;

@DisplayName("INTEGRATION TESTS - Controller - Update Patient")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PatientControllerUpdatePatientIT {

	

    @Autowired
    private TestRestTemplate restTemplate;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
    
    @LocalServerPort
    private int port;

    private PatientDTO patientDTO;

    private final static String PATIENT_ADD_URL = "/patient/add/";
    private final static String PATIENT_UPDATE_URL = "/patient/update/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    	
    	restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test PUT updatePatient - "
    		+ " Given a Patient to update,"
    		+ " when request for updatePatient,"
    		+ " then return status 200 Ok")
    public void testUpdatePatient() throws Exception {

    	PatientDTO patientupdateDTO = new PatientDTO(
    			1, "Rees1", "Pippa1", LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");


    	MvcResult response =   mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientupdateDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    
		PatientDTO patientResult = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<PatientDTO>() {});
		assertNotNull(patientResult);
		assertEquals("Pippa1", patientResult.getFirstName());
		assertEquals("Rees1", patientResult.getLastName());
    }


  	// *******************************************************************

    @Test
    @DisplayName("test PUT updatePatient - Empty Lastname"
    		+ " Given a Patient to Empty Lastname,"
    		+ " when request for updatePatient, "
    		+ "then return BAD_REQUEST")
    void testUpdatePatientForEmptyLastName() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "", "Pippa", LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Lastname is mandatory")))
                .andReturn();

    }



  	// *******************************************************************


    @Test
    @DisplayName("test PUT updatePatient - LastNameNull"
    		+ " Given a Patient to LastNameNull,"
    		+ " when request for updatePatient, "
    		+ "then return BAD_REQUEST")
    void testUpdatePatientForLastNameNull() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, null, "Pippa", LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Lastname is mandatory")))
                .andReturn();

    }



  	// *******************************************************************


    @Test
    @DisplayName("test PUT updatePatient - Empty Firstname"
    		+ " Given a Patient to Empty Firstname,"
    		+ " when request for updatePatient, "
    		+ "then return BAD_REQUEST")
    void testUpdatePatientForEmptyFirstname() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "", LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Firstname is mandatory")))
                .andReturn();

    }



  	// *******************************************************************


    @Test
    @DisplayName("test PUT updatePatient - FirstnameNull"
    		+ " Given a Patient to FirstnameNull,"
    		+ " when request for updatePatient, "
    		+ "then return BAD_REQUEST")
    void testUpdatePatientForFirstnameNull() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", null, LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Firstname is mandatory")))
                .andReturn();

    }



  	// *******************************************************************

    
    
      
    
    @Test
    @DisplayName("test PUT updatePatient - Invalid Birthdate"
    		+ " Given a Patient to invalid birthdate,"
    		+ " when request for updatePatient, "
    		+ "then return BAD_REQUEST")
    void testUpdatePatientForInvalidBirthDate() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", LocalDate.parse("9999-09-16"), "F", "745 West Valley Farms Drive", "628-423-0993");

    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Please enter a valid birth date")))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains("Please enter a valid birth date");

    }



  	// *******************************************************************

    
    
      
    
    @Test
    @DisplayName("test PUT updatePatient - Null Birthdate"
    		+ " Given a Patient to Null birthdate,"
    		+ " when request for updatePatient, "
    		+ "then return BAD_REQUEST")
    void testUpdatePatientForNullBirthDate() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", null, "F", "745 West Valley Farms Drive", "628-423-0993");

    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains("Date of birth is mandatory");

    }



  	// *******************************************************************



    @Test
    @DisplayName("test PUT updatePatient - Empty Sex"
    		+ " Given a Patient to Empty Sex,"
    		+ " when request for updatePatient, "
    		+ "then return BAD_REQUEST")
    void testUpdatePatientForEmptySex() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", LocalDate.of(1952, 11, 11), "", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Please enter character M or F")))
                .andReturn();

    }



  	// *******************************************************************


    @Test
    @DisplayName("test PUT updatePatient - SexNull"
    		+ " Given a Patient to SexNull,"
    		+ " when request for updatePatient, "
    		+ "then return BAD_REQUEST")
    void testUpdatePatientForSexNull() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", LocalDate.of(1952, 11, 11), null, "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Sex is mandatory")))
                .andReturn();

    }



  	// *******************************************************************



    @Test
    @DisplayName("test PUT updatePatient - Sex Invalid"
    		+ " Given a Patient to Sex Invalid,"
    		+ " when request for updatePatient, "
    		+ "then return BAD_REQUEST")
    void testUpdatePatientForSexInvalid() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", LocalDate.of(1952, 11, 11), "Z", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Please enter character M or F")))
                .andReturn();

    }



  	// *******************************************************************

     

}
