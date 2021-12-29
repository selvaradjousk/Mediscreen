package com.ui.unittest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.ui.controller.PatientController;
import com.ui.dto.PatientDTO;
import com.ui.proxy.MicroservicePatientProxy;

@DisplayName("UNIT TESTS - Controller - Update Patient")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
class PatientControllerUpdatePatientTest {

	
    @MockBean
    private MicroservicePatientProxy patientProxy;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private ObjectMapper mapper;

    private PatientDTO patientDTO;
    

    private final static String PATIENT_UPDATE_URL = "/patient/update/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test GET updatePatientForm - "
    		+ " Given a Patient to update,"
    		+ " when request for GET updatePatientForm,"
    		+ " then return status 200 Ok")
    public void testGETupdatePatientForm() throws Exception {

    	when(patientProxy.getPatientById(1)).thenReturn(patientDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATIENT_UPDATE_URL + 1))
        		.andExpect(model().attributeExists("patientDTO"))
                .andExpect(view().name("patient/update"))
                .andExpect(status().isOk());

        verify(patientProxy).getPatientById(1);
    }


  	// *******************************************************************


    @Test
    @DisplayName("test PUT updatePatient - valid"
    		+ " Given a Patient to update,"
    		+ " when request for updatePatient,"
    		+ " then return status 200 Ok")
    public void testUpdatePatient() throws Exception {

    	when(patientProxy.updatePatient(1, patientDTO)).thenReturn(patientDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(redirectedUrl("/patient/list"))
                .andReturn();
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

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
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

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
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

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
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

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
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
        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
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
        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
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

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
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

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
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

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
    }



  	// *******************************************************************

     

}
