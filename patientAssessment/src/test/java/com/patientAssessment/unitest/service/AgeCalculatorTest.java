package com.patientAssessment.unitest.service;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patientAssessment.proxy.MicroserviceNoteProxy;
import com.patientAssessment.proxy.MicroservicePatientProxy;
import com.patientAssessment.service.AssessmentService;

@DisplayName("UNIT TESTS - Service - PatientAssessment AgeCalculator")
@ExtendWith(MockitoExtension.class)
class AgeCalculatorTest {


    @InjectMocks
    private AssessmentService assessmentService;

    @Mock
    private MicroserviceNoteProxy noteProxy;

    @Mock
    private MicroservicePatientProxy patientProxy;



	// *******************************************************************	
	

	@DisplayName("Calculate Age valid - "
			+ "GIVEN birthdate "
			+ "WHEN calculate age"
			+ "THEN returns expected age value")
    @Test
    public void testGetAge() {
        LocalDate birthDate = LocalDate.now().minusYears(25);

        int age = assessmentService.getAge(birthDate);

        assertEquals(25, age);
    }



	// *******************************************************************	

	@DisplayName("Calculate Age valid less than one year - "
			+ "GIVEN birthdate "
			+ "WHEN calculate age"
			+ "THEN returns expected age value")
	@Test
    public void testGetAgeForLessThanAYear() {
        LocalDate birthDate = LocalDate.now().minusMonths(11);

        int age = assessmentService.getAge(birthDate);

        assertEquals(1, age);
    }



	// *******************************************************************	
	
	
	
	@DisplayName("Calculate Age null value as date of birth - "
			+ "GIVEN birthdate null value as birth date "
			+ "WHEN calculate age"
			+ "THEN throws exception")
    @Test
    public void testGetAgeForInvalidNullValueDateOfBirthInput() {
        LocalDate birthDate = null;

        assertThatNullPointerException()
        .isThrownBy(() -> assessmentService.getAge(birthDate));
    }



	// *******************************************************************	
	

	@DisplayName("Calculate Age invalid future date as date of birth - "
			+ "GIVEN birthdate invalid future birth date "
			+ "WHEN calculate age"
			+ "THEN throws exception")
    @Test
    public void testGetAgeForInvalidFutureDateOfBirthInput() {
        int age = 1;
        LocalDate birthDate = LocalDate.now().plusYears(age);

        assertThatIllegalArgumentException()
        .isThrownBy(() -> assessmentService.getAge(birthDate));
    }



	// *******************************************************************	
	
	@DisplayName("Calculate Age negative date as date of birth - "
			+ "GIVEN birthdate invalid future birth date "
			+ "WHEN calculate age"
			+ "THEN throws exception")
    @Test
    public void testGetAgeForInvalidNegativeValueDateOfBirthInput() {
        int age = -1;
        LocalDate birthDate = LocalDate.now().minusYears(age);

        assertThatIllegalArgumentException()
        .isThrownBy(() -> assessmentService.getAge(birthDate));
    }



	// *******************************************************************	

}
