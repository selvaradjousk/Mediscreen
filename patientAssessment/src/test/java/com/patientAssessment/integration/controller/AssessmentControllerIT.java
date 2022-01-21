package com.patientAssessment.integration.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.patientAssessment.dto.AssessmentDTO;

@DisplayName("IT - Controller - getPatientAssessment")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({"/application-test.properties"})
class AssessmentControllerIT {


    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private final static String ASSESS_GET_URL = "/assess/";

	// *******************************************************************	
	

	@DisplayName("getDiabetesRiskLevel: TestNone - "
			+ "GIVEN a patient TestNone "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the TestNone As None")
	@Test
    public void testGetPatientAssessmentTestNone() throws Exception {

        ResponseEntity<AssessmentDTO> response = restTemplate.getForEntity("http://localhost:" + port +
                ASSESS_GET_URL + "1", AssessmentDTO.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());

        String content = response.getBody().getDiabetesRiskLevel();

        assertThat(content).contains("None");
    }


	// *******************************************************************	
	

	@DisplayName("getDiabetesRiskLevel: TestBorderline - "
			+ "GIVEN a patient TestBorderline "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the TestBorderline As Borderline")
	@Test
    public void testGetPatientAssessmentTestBorderline() throws Exception {

        ResponseEntity<AssessmentDTO> response = restTemplate.getForEntity("http://localhost:" + port +
                ASSESS_GET_URL + "2", AssessmentDTO.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());

        String content = response.getBody().getDiabetesRiskLevel();

        assertThat(content).contains("Borderline");
    }


	// *******************************************************************	
	
	

	@DisplayName("getDiabetesRiskLevel: TestInDanger - "
			+ "GIVEN a patient TestInDanger "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the TestInDanger As In danger")
	@Test
    public void testGetPatientAssessmentTestInDanger() throws Exception {

        ResponseEntity<AssessmentDTO> response = restTemplate.getForEntity("http://localhost:" + port +
                ASSESS_GET_URL + "3", AssessmentDTO.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());

        String content = response.getBody().getDiabetesRiskLevel();

        assertThat(content).contains("In danger");
    }


	// *******************************************************************	
	
	

	@DisplayName("getDiabetesRiskLevel: TestEarlyOnset - "
			+ "GIVEN a patient TestEarlyOnset "
			+ "WHEN getPatientAssessment"
			+ "THEN returns expected PatientAssessment of the TestEarlyOnset As Early onset")
	@Test
    public void testGetPatientAssessmenTestEarlyOnset() throws Exception {

        ResponseEntity<AssessmentDTO> response = restTemplate.getForEntity("http://localhost:" + port +
                ASSESS_GET_URL + "4", AssessmentDTO.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());

        String content = response.getBody().getDiabetesRiskLevel();

        assertThat(content).contains("Early onset");
    }


	// *******************************************************************	
	
	

	@DisplayName("getDiabetesRiskLevel: invalid patient id - "
			+ "GIVEN a patient invalid patient id "
			+ "WHEN getPatientAssessment"
			+ "THEN returns BAD_REQUEST")
	@Test
    public void testGetPatientAssessmenInvalidPatientId() throws Exception {

        ResponseEntity<AssessmentDTO> response = restTemplate.getForEntity("http://localhost:" + port +
                ASSESS_GET_URL + "xx", AssessmentDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());

    }


	// *******************************************************************	
	

	@DisplayName("getPatientAssessmentByFamilyName: TestNone - "
			+ "GIVEN a patient TestNone "
			+ "WHEN PatientAssessmentByFamilyName"
			+ "THEN returns expected PatientAssessment of the TestNone As None")
	@Test
    public void testGetPatientAssessmentByFamilyNameTestNone() throws Exception {

        ResponseEntity<AssessmentDTO> response = restTemplate.getForEntity("http://localhost:" + port +
                ASSESS_GET_URL + "getByFamilyName?lastName=TestNone", AssessmentDTO.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());

        String content = response.getBody().getDiabetesRiskLevel();

        assertThat(content).contains("None");
    }


	// *******************************************************************	
	
					
			


	@DisplayName("getPatientAssessmentByFamilyName: NotFoundException- "
			+ "GIVEN a patient NotFound "
			+ "WHEN PatientAssessmentByFamilyName"
			+ "THEN returns Exception")
	@Test
    public void testGetPatientAssessmentNotFound() throws Exception {

        ResponseEntity<AssessmentDTO> response = restTemplate.getForEntity("http://localhost:" + port +
                ASSESS_GET_URL + "getByFamilyName?lastName=TTTTTTestNone", AssessmentDTO.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());


    }


	// *******************************************************************	
	
					
		
}
