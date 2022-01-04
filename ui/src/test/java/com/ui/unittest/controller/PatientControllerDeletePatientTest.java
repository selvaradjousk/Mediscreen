package com.ui.unittest.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ui.controller.PatientController;
import com.ui.dto.PatientDTO;
import com.ui.proxy.MicroservicePatientProxy;

@DisplayName("UNIT TESTS - Controller - Delete Patient")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
class PatientControllerDeletePatientTest {

	
    @MockBean
    private MicroservicePatientProxy microservicePatientProxy;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    private PatientDTO patientDTO;
    

    private final static String PATIENT_DELETE_URL = "/patient/delete/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO = new PatientDTO(1, "Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test DELETE PatientById - "
    		+ " Given a Patient with id,"
    		+ " when request for deletePatient,"
    		+ " then return status 200 Ok")
    public void testGetPatientById() throws Exception {

    	when(microservicePatientProxy.getPatientById(1)).thenReturn(patientDTO);

    	mockMvc.perform(MockMvcRequestBuilders
    			.get(PATIENT_DELETE_URL + 1))
    			.andExpect(redirectedUrl("/patient/list"));

        verify(microservicePatientProxy).deletePatient(1);
    }


  	// *******************************************************************

   

}
