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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.patient.controller.PatientController;
import com.patient.dto.PatientDTO;
import com.patient.service.IPatientService;

@DisplayName("UNIT TESTS - Controller - GetByLastName")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
class PatientControllerGetPatientByLastNameTest {

	
    @MockBean
    private IPatientService patientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    private PatientDTO patientDTO;
    

    private final static String PATIENT_GET_URL = "/patient/getByFamilyName";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test GET GetByLastName - "
    		+ " Given a Patient with lastName,"
    		+ " when request for GetByLastName,"
    		+ " then return status 200 Ok")
    public void testGetPatientByLastName() throws Exception {

    	when(patientService.getPatient("Rees")).thenReturn(patientDTO);

    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders
    			.get(PATIENT_GET_URL + "?lastName=Rees")
                .contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Rees");
        verify(patientService).getPatient("Rees");
    }


  	// *******************************************************************

}
