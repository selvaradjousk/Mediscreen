package com.ui.unittest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
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

@DisplayName("UNIT TESTS - Controller - Add Patient")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
class PatientControllerAddPatientTest {

	
    @MockBean
    private MicroservicePatientProxy patientProxy;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private ObjectMapper mapper;

    private PatientDTO patientDTO, patientToAddDTO;
    

    private final static String PATIENT_ADD_GET_URL = "/patient/add";
    private final static String PATIENT_ADD_POST_URL = "/patient/validate";



  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	patientDTO = new PatientDTO("Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");
    	
    	
    	patientToAddDTO = new PatientDTO("Rees", "Pippa",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test GET addPatientForm - "
    		+ " Given a Patient to add,"
    		+ " when request for GET addPatientForm,"
    		+ " then return status 200 Ok")
    public void testGETaddPatientForm() throws Exception {

    	when(patientProxy.getPatientById(1)).thenReturn(patientDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATIENT_ADD_GET_URL))
        		.andExpect(model().attributeExists("patientDTO"))
                .andExpect(view().name("patient/add"))
                .andExpect(status().isOk());

    }


  	// *******************************************************************


    @Test
    @DisplayName("test POST addPatient - valid"
    		+ " Given a Patient to add,"
    		+ " when request for addPatient,"
    		+ " then return status 200 Ok")
    public void testAddPatient() throws Exception {
    	
    	when(patientProxy.addPatient(patientDTO)).thenReturn(patientDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_POST_URL)
                .content(mapper.writeValueAsString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


  	// *******************************************************************


    @Test
    @DisplayName("test POST addPatient - redirectURL"
    		+ " Given a Patient to add,"
    		+ " when request for addPatient,"
    		+ " then return status REDIRECT to patient list URL")
    public void testAddPatientRedirectUrl() throws Exception {

   	
    	when(patientProxy.addPatient(patientDTO)).thenReturn(patientToAddDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_POST_URL)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", patientDTO.getLastName())
                .param("firstName", patientDTO.getFirstName())
                .param("birthDate", patientDTO.getBirthDate().toString())
                .param("sex", patientDTO.getSex())
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
                .andExpect(redirectedUrl("/patient/list"));

        verify(patientProxy).addPatient(any(PatientDTO.class));
    }


  	// *******************************************************************

    @Test
    @DisplayName("test POST addPatient - Empty Lastname"
    		+ " Given a Patient to Empty Lastname,"
    		+ " when request for addPatient, "
    		+ "then return redirect patient/add")
    void testAddPatientForEmptyLastName() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_POST_URL)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", "")
                .param("firstName", patientDTO.getFirstName())
                .param("birthDate", patientDTO.getBirthDate().toString())
                .param("sex", patientDTO.getSex())
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
        		.andExpect(content().string(containsString("Lastname is mandatory")))
        		.andExpect(model().hasErrors())
                .andExpect(view().name("patient/add"))
                .andReturn();

        verify(patientProxy, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************


    @Test
    @DisplayName("test POST addPatient - Empty Firstname"
    		+ " Given a Patient to Empty Firstname,"
    		+ " when request for addPatient, "
    		+ "then  redirect patient/add")
    void testAddPatientForEmptyFirstname() throws Exception {


		mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_POST_URL)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", patientDTO.getLastName())
                .param("firstName", "")
                .param("birthDate", patientDTO.getBirthDate().toString())
                .param("sex", patientDTO.getSex())
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
        		.andExpect(model().hasErrors())
                .andExpect(view().name("patient/add"))
                .andReturn();
		
		
        verify(patientProxy, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************
    
    
      
    
    @Test
    @DisplayName("test POST addPatient - Invalid Birthdate"
    		+ " Given a Patient to invalid birthdate,"
    		+ " when request for addPatient, "
    		+ "then  redirect patient/add")
    void testAddPatientForInvalidBirthDate() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_POST_URL)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", patientDTO.getLastName())
                .param("firstName", patientDTO.getFirstName())
                .param("birthDate", "sdfsqdf")
                .param("sex", patientDTO.getSex())
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
        		.andExpect(model().hasErrors())
                .andExpect(view().name("patient/add"))
                .andReturn();
		
		
        verify(patientProxy, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************

    
 

    @Test
    @DisplayName("test POST addPatient - Empty Sex"
    		+ " Given a Patient to Empty Sex,"
    		+ " when request for addPatient, "
    		+ "then  redirect patient/add")
    void testAddPatientForEmptySex() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_POST_URL)
                .sessionAttr("patientDTO", patientDTO)
                .param("lastName", patientDTO.getLastName())
                .param("firstName", patientDTO.getFirstName())
                .param("birthDate", patientDTO.getBirthDate().toString())
                .param("sex", "")
                .param("address", patientDTO.getAddress())
                .param("phoneNumber", patientDTO.getPhoneNumber()))
        		.andExpect(model().hasErrors())
                .andExpect(view().name("patient/add"))
                .andReturn();

        verify(patientProxy, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************




    @Test
    @DisplayName("test POST addPatient - Sex Invalid"
    		+ " Given a Patient to Sex Invalid,"
    		+ " when request for addPatient, "
    		+ "then redirect patient/add")
    void testAddPatientForSexInvalid() throws Exception {


    			mockMvc.perform(MockMvcRequestBuilders.post(PATIENT_ADD_POST_URL)
    	                .sessionAttr("patientDTO", patientDTO)
    	                .param("lastName", patientDTO.getLastName())
    	                .param("firstName", patientDTO.getFirstName())
    	                .param("birthDate", patientDTO.getBirthDate().toString())
    	                .param("sex", "Z")
    	                .param("address", patientDTO.getAddress())
    	                .param("phoneNumber", patientDTO.getPhoneNumber()))
    	        		.andExpect(model().hasErrors())
    	                .andExpect(view().name("patient/add"))
    	                .andReturn();

        verify(patientProxy, times(0)).addPatient(any(PatientDTO.class));
    }



  	// *******************************************************************

     

}
