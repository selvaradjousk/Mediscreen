package com.ui.unittest.controller;

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

    private PatientDTO patientDTO, patientToUpdateDTO;
    

    private final static String PATIENT_UPDATE_URL = "/patient/update/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO = new PatientDTO("Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
    	
    	
    	patientToUpdateDTO = new PatientDTO("Rees", "Pippa",
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
    @DisplayName("test POST updatePatient - valid"
    		+ " Given a Patient to update,"
    		+ " when request for updatePatient,"
    		+ " then return status 200 Ok")
    public void testUpdatePatient() throws Exception {
    	
    	when(patientProxy.updatePatient(1, patientDTO)).thenReturn(patientDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


  	// *******************************************************************


    @Test
    @DisplayName("test POST updatePatient - redirectURL"
    		+ " Given a Patient to update,"
    		+ " when request for updatePatient,"
    		+ " then return status REDIRECT to patient list URL")
    public void testUpdatePatientRedirectUrl() throws Exception {

   	
    	when(patientProxy.updatePatient(1, patientDTO)).thenReturn(patientToUpdateDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", patientDTO.getLastName())
                .param("firstName", patientDTO.getFirstName())
                .param("birthDate", patientDTO.getBirthDate().toString())
                .param("sex", patientDTO.getSex())
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
                .andExpect(redirectedUrl("/patient/list"));

        verify(patientProxy).updatePatient(anyInt(), any(PatientDTO.class));
    }


  	// *******************************************************************

    @Test
    @DisplayName("test POST updatePatient - Empty Lastname"
    		+ " Given a Patient to Empty Lastname,"
    		+ " when request for updatePatient, "
    		+ "then return redirect patient/update")
    void testUpdatePatientForEmptyLastName() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", "")
                .param("firstName", patientDTO.getFirstName())
                .param("birthDate", patientDTO.getBirthDate().toString())
                .param("sex", patientDTO.getSex())
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
        		.andExpect(content().string(containsString("Lastname is mandatory")))
        		.andExpect(model().hasErrors())
                .andExpect(view().name("patient/update"))
                .andReturn();

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
    }



  	// *******************************************************************


    @Test
    @DisplayName("test POST updatePatient - Empty Firstname"
    		+ " Given a Patient to Empty Firstname,"
    		+ " when request for updatePatient, "
    		+ "then  redirect patient/update")
    void testUpdatePatientForEmptyFirstname() throws Exception {


		mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", patientDTO.getLastName())
                .param("firstName", "")
                .param("birthDate", patientDTO.getBirthDate().toString())
                .param("sex", patientDTO.getSex())
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
        		.andExpect(model().hasErrors())
                .andExpect(view().name("patient/update"))
                .andReturn();
		
		
        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
    }



  	// *******************************************************************
    
    
      
    
    @Test
    @DisplayName("test POST updatePatient - Invalid Birthdate"
    		+ " Given a Patient to invalid birthdate,"
    		+ " when request for updatePatient, "
    		+ "then  redirect patient/update")
    void testUpdatePatientForInvalidBirthDate() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", patientDTO.getLastName())
                .param("firstName", patientDTO.getFirstName())
                .param("birthDate", "sdfsqdf")
                .param("sex", patientDTO.getSex())
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
        		.andExpect(model().hasErrors())
                .andExpect(view().name("patient/update"))
                .andReturn();
		
		
        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
    }



  	// *******************************************************************

    
 

    @Test
    @DisplayName("test POST updatePatient - Empty Sex"
    		+ " Given a Patient to Empty Sex,"
    		+ " when request for updatePatient, "
    		+ "then  redirect patient/update")
    void testUpdatePatientForEmptySex() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", patientDTO.getLastName())
                .param("firstName", patientDTO.getFirstName())
                .param("birthDate", patientDTO.getBirthDate().toString())
                .param("sex", "")
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
        		.andExpect(model().hasErrors())
                .andExpect(view().name("patient/update"))
                .andReturn();

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
    }



  	// *******************************************************************




    @Test
    @DisplayName("test POST updatePatient - Sex Invalid"
    		+ " Given a Patient to Sex Invalid,"
    		+ " when request for updatePatient, "
    		+ "then redirect patient/update")
    void testUpdatePatientForSexInvalid() throws Exception {


    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_UPDATE_URL + 1)
    	                .sessionAttr("patientDTO", patientDTO)
    	                .param("lastName", patientDTO.getLastName())
    	                .param("firstName", patientDTO.getFirstName())
    	                .param("birthDate", patientDTO.getBirthDate().toString())
    	                .param("sex", "Z")
    	                .param("address", patientDTO.getAddress())
    	                .param("phoneNumber", patientDTO.getPhoneNumber()))
    	        		.andExpect(model().hasErrors())
    	                .andExpect(view().name("patient/update"))
    	                .andReturn();

        verify(patientProxy, times(0)).updatePatient(anyInt(), any(PatientDTO.class));
    }



  	// *******************************************************************

     

}
