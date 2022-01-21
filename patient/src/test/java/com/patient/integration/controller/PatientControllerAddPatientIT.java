package com.patient.integration.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.patient.dto.PatientDTO;

@DisplayName("INTEGRATION TESTS - Controller - Add Patient")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class PatientControllerAddPatientIT {


    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;

    private PatientDTO patientDTO;

    private final static String PATIENT_ADD_URL = "/patient/add/";



  	// *******************************************************************



    @BeforeEach
    public void setup() {

    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test POST addPatient - "
    		+ " Given a Patient to add,"
    		+ " when request for addPatient,"
    		+ " then return status 200 Ok")
    public void testAddPatient() throws Exception {


    	patientDTO = new PatientDTO(1, "ReesTest", "PippaTest",
                LocalDate.of(1952, 11, 11), "F", "745 West Valley Farms Drive", "628-423-0993");

    	
        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertThat(response.getBody())
                .hasFieldOrPropertyWithValue("lastName", "ReesTest")
                .isNotNull();

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

       	
        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertThat(response.getBody().toString().contains("Lastname is mandatory"));    	
    	
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

       	
        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertThat(response.getBody().toString().contains("Lastname is mandatory")); 
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


       	
        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertThat(response.getBody().toString().contains("Firstname is mandatory")); 
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

       	
        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertThat(response.getBody().toString().contains("Firstname is mandatory")); 
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

       	
        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertThat(response.getBody().toString().contains("Please enter a valid birth date")); 
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

       	
        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertThat(response.getBody().toString().contains("Date of birth is mandatory"));

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

       	
        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertThat(response.getBody().toString().contains("Please enter character M or F"));

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

    	       	
    	        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
    	        		patientDTO, PatientDTO.class);

    	        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    	        assertThat(response.getBody().toString().contains("Sex is mandatory"));

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


       	
        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("http://localhost:" + port + PATIENT_ADD_URL,
        		patientDTO, PatientDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertThat(response.getBody().toString().contains("Please enter character M or F"));

    }



  	// *******************************************************************

     

}
