package com.patient.unittest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.controller.PatientController;
import com.patient.dto.PatientDTO;
import com.patient.service.IPatientService;

@DisplayName("UNIT TESTS - Controller - Add Patient")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
class PatientControllerAddPatientTest {

	
    @MockBean
    private IPatientService patientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private ObjectMapper mapper;

    private PatientDTO patientDTO;
    

    private final static String PATIENT_ADD_URL = "/patient/add/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test POST addPatient - "
    		+ " Given a Patient to add,"
    		+ " when request for addPatient,"
    		+ " then return status 200 Ok")
    public void testAddPatient() throws Exception {

    	when(patientService.addPatient(patientDTO)).thenReturn(patientDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }


  	// *******************************************************************

    @Test
    @DisplayName("test POST addPatient - Empty Lastname"
    		+ " Given a Patient to Empty Lastname,"
    		+ " when request for addPatient, "
    		+ "then return BAD_REQUEST")
    void testAddPatientForEmptyLastName() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "", "Pippa", LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Lastname is mandatory")))
                .andReturn();

        verify(patientService, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************


    @Test
    @DisplayName("test POST addPatient - LastNameNull"
    		+ " Given a Patient to LastNameNull,"
    		+ " when request for addPatient, "
    		+ "then return BAD_REQUEST")
    void testAddPatientForLastNameNull() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, null, "Pippa", LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Lastname is mandatory")))
                .andReturn();

        verify(patientService, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************


    @Test
    @DisplayName("test POST addPatient - Empty Firstname"
    		+ " Given a Patient to Empty Firstname,"
    		+ " when request for addPatient, "
    		+ "then return BAD_REQUEST")
    void testAddPatientForEmptyFirstname() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "", LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Firstname is mandatory")))
                .andReturn();

        verify(patientService, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************


    @Test
    @DisplayName("test POST addPatient - FirstnameNull"
    		+ " Given a Patient to FirstnameNull,"
    		+ " when request for addPatient, "
    		+ "then return BAD_REQUEST")
    void testAddPatientForFirstnameNull() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", null, LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Firstname is mandatory")))
                .andReturn();

        verify(patientService, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************

    
    
      
    
    @Test
    @DisplayName("test POST addPatient - Invalid Birthdate"
    		+ " Given a Patient to invalid birthdate,"
    		+ " when request for addPatient, "
    		+ "then return BAD_REQUEST")
    void testAddPatientForInvalidBirthDate() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", LocalDate.parse("9999-09-16"), "F", "745 West Valley Farms Drive", "628-423-0993");

    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Please enter a valid birth date")))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains("Please enter a valid birth date");
        verify(patientService, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************

    
    
      
    
    @Test
    @DisplayName("test POST addPatient - Null Birthdate"
    		+ " Given a Patient to Null birthdate,"
    		+ " when request for addPatient, "
    		+ "then return BAD_REQUEST")
    void testAddPatientForNullBirthDate() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", null, "F", "745 West Valley Farms Drive", "628-423-0993");

    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains("Date of birth is mandatory");
        verify(patientService, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************



    @Test
    @DisplayName("test POST addPatient - Empty Sex"
    		+ " Given a Patient to Empty Sex,"
    		+ " when request for addPatient, "
    		+ "then return BAD_REQUEST")
    void testAddPatientForEmptySex() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", LocalDate.of(1952, 11, 11), "", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Please enter character M or F")))
                .andReturn();

        verify(patientService, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************


    @Test
    @DisplayName("test POST addPatient - SexNull"
    		+ " Given a Patient to SexNull,"
    		+ " when request for addPatient, "
    		+ "then return BAD_REQUEST")
    void testAddPatientForSexNull() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", LocalDate.of(1952, 11, 11), null, "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Sex is mandatory")))
                .andReturn();

        verify(patientService, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************



    @Test
    @DisplayName("test POST addPatient - Sex Invalid"
    		+ " Given a Patient to Sex Invalid,"
    		+ " when request for addPatient, "
    		+ "then return BAD_REQUEST")
    void testAddPatientForSexInvalid() throws Exception {

    	PatientDTO patientDTO = new PatientDTO(
    			1, "Rees", "Pippa", LocalDate.of(1952, 11, 11), "Z", "745 West Valley Farms Drive", "628-423-0993");

//    	MvcResult result = 
    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsString("Please enter character M or F")))
                .andReturn();

        verify(patientService, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************

     

}
