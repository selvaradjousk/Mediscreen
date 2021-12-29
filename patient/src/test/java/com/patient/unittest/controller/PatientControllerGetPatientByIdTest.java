package com.patient.unittest.controller;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.patient.controller.PatientController;
import com.patient.dto.PatientDTO;
import com.patient.service.IPatientService;

@DisplayName("UNIT TESTS - Controller - Get Patient By Id")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
class PatientControllerGetPatientByIdTest {

	
    @MockBean
    private IPatientService patientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    private PatientDTO patientDTO;
    

    private final static String PATIENT_GET_URL = "/patient/get/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test GET PatientById - "
    		+ " Given a Patient with id,"
    		+ " when request for getPatientById,"
    		+ " then return status 200 Ok")
    public void testGetPatientById() throws Exception {

    	when(patientService.getPatientById(1)).thenReturn(patientDTO);

    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders
    			.get(PATIENT_GET_URL + 1)
                .contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Rees");
        verify(patientService).getPatientById(1);
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
