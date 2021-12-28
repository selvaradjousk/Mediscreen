package com.patient.unittest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.patient.dto.PatientDTO;

@DisplayName("UNIT TESTS - DTO VALIDATION - PATIENT")
class PatientDtoValidationTest {
	
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	private static PatientDTO patientDTO1, patientDTO2,
							patientDTOpatientAllOk,
							patientDTOpatientLastNameNull,
							patientDTOpatientLastNameEmpty,
							patientDTOFirstNameNull,
							patientDTOpatientFirstNameEmpty,
							patientDTOInvalidDateFuture,
							patientDTOInvalidDateNull,
							patientDTOSexNull,
							patientDTOSexEmpty,
							patientDTOSexInvalidCharacter;
	
	private LocalDate birthday = LocalDate.now().minusDays(1);
	private LocalDate futurebirthday = LocalDate.now().plusDays(1);
	private LocalDate invalidbirthday = LocalDate.now().minusYears(1000);
	

	@BeforeEach
	void init_test() {
		patientDTO1 = new PatientDTO("lastname", "firstname", birthday, "M", "address", "phone");
		patientDTO2 = new PatientDTO("lastname", "firstname", birthday, "M", "address", "phone");
		patientDTOpatientAllOk = new PatientDTO("lastname", "firstname", birthday, "M", "address", "phone");
		patientDTOpatientLastNameNull = new PatientDTO(null, "firstname", birthday, "M", "address", "phone");
		patientDTOpatientLastNameEmpty = new PatientDTO("", "firstname", birthday, "M", "address", "phone");
		patientDTOFirstNameNull = new PatientDTO("lastname", null, birthday, "M", "address", "phone");
		patientDTOpatientFirstNameEmpty = new PatientDTO("lastname", "", birthday, "M", "address", "phone");
		patientDTOInvalidDateFuture = new PatientDTO("lastname", "firstname", futurebirthday, "M", "address", "phone");
		patientDTOInvalidDateNull = new PatientDTO("lastname", "firstname", null, "M", "address", "phone");
		patientDTOSexNull = new PatientDTO("lastname", "firstname", birthday, null, "address", "phone");
		patientDTOSexEmpty = new PatientDTO("lastname", "firstname", birthday, "", "address", "phone");
		patientDTOSexInvalidCharacter = new PatientDTO("lastname", "firstname", birthday, "sex", "address", "phone");
	}

	
	// *******************************************************************	
		
	
	@DisplayName("DTO - "
			+ "GIVEN DTO  "
			+ "WHEN Requested DTO value"
			+ "THEN returns expected DTO")
	@Test
	public void userDTOTest() {
	assertEquals(patientDTO1.getFirstName(), patientDTO2.getFirstName());
	assertEquals(patientDTO1.getLastName(), patientDTO2.getLastName());
	}
	
	// *******************************************************************	


	
		@DisplayName("DTO - All inputs Valid - "
				+ "GIVEN DTO with All inputs Valid"
				+ "WHEN Requested DTO value"
				+ "THEN returns expected No DTO constraint violations")
	    @Test
	    public void testForAllInputsValid() {
	 
	    	// GIVEN
	        
	        // WHEN
	        Set<ConstraintViolation<PatientDTO>> constraintViolations =
	                validator.validate(patientDTOpatientAllOk);
	        // THEN
	        assertEquals(0, constraintViolations.size());

	        System.out.println(patientDTOpatientAllOk.getBirthDate());
	        System.out.println(constraintViolations.toString());


	    }
		
		

	// *******************************************************************	
		


		
		@DisplayName("DTO - patientDTOpatientLastNameNull - "
				+ "GIVEN DTO with patientDTOpatientLastNameNull"
				+ "WHEN Requested DTO value"
				+ "THEN returns expected DTO constraint violations")
	    @Test
	    public void patientDTOpatientLastNameNull() {
	 
	    	// GIVEN
	        
	        // WHEN
	        Set<ConstraintViolation<PatientDTO>> constraintViolations =
	                validator.validate(patientDTOpatientLastNameNull);
	        // THEN
	        assertEquals(1, constraintViolations.size());

	    }
		
		

	// *******************************************************************	
		


		
		@DisplayName("DTO - patientDTOpatientLastNameEmpty - "
				+ "GIVEN DTO with inputs patientDTOpatientLastNameEmpty"
				+ "WHEN Requested DTO value"
				+ "THEN returns expected DTO constraint violations")
	    @Test
	    public void patientDTOpatientLastNameEmpty() {
	 
	    	// GIVEN
	        
	        // WHEN
	        Set<ConstraintViolation<PatientDTO>> constraintViolations =
	                validator.validate(patientDTOpatientLastNameEmpty);
	        // THEN
	        assertEquals(1, constraintViolations.size());

	    }
		
		

	// *******************************************************************	
			


		
		@DisplayName("DTO - patientDTOFirstNameNull - "
				+ "GIVEN DTO with inputs patientDTOFirstNameNull"
				+ "WHEN Requested DTO value"
				+ "THEN returns expected DTO constraint violations")
	    @Test
	    public void patientDTOFirstNameNull() {
	 
	    	// GIVEN
	        
	        // WHEN
	        Set<ConstraintViolation<PatientDTO>> constraintViolations =
	                validator.validate(patientDTOFirstNameNull);
	        // THEN
	        System.out.println(constraintViolations.toString());
	        assertEquals(1, constraintViolations.size());

	    }
		
		

	// *******************************************************************	
			
		


		
	@DisplayName("DTO - patientDTOpatientFirstNameEmpty - "
			+ "GIVEN DTO with inputs patientDTOpatientFirstNameEmpty"
			+ "WHEN Requested DTO value"
			+ "THEN returns expected DTO constraint violations")
    @Test
    public void patientDTOpatientFirstNameEmpty() {
 
    	// GIVEN
        
        // WHEN
        Set<ConstraintViolation<PatientDTO>> constraintViolations =
                validator.validate(patientDTOpatientFirstNameEmpty);
        // THEN
        System.out.println(constraintViolations.toString());
        assertEquals(1, constraintViolations.size());

    }
	
	

	// *******************************************************************
	
	

		
			
		@DisplayName("DTO - patientDTOInvalidDateFuture - "
				+ "GIVEN DTO with inputs patientDTOInvalidDateFuture"
				+ "WHEN Requested DTO value"
				+ "THEN returns expected DTO constraint violations")
		@Test
		public void patientDTOInvalidDateFuture() {
		
			// GIVEN
		    
		    // WHEN
		    Set<ConstraintViolation<PatientDTO>> constraintViolations =
		            validator.validate(patientDTOInvalidDateFuture);
		    // THEN
		    System.out.println(constraintViolations.toString());
		    assertEquals(1, constraintViolations.size());
		
		}
		
		
		
		// *******************************************************************
		
		
		

		
		
		@DisplayName("DTO - patientDTOInvalidDateNull - "
				+ "GIVEN DTO with inputs patientDTOInvalidDateNull"
				+ "WHEN Requested DTO value"
				+ "THEN returns expected DTO constraint violations")
		@Test
		public void patientDTOInvalidDateNull() {
		
			// GIVEN
		    
		    // WHEN
		    Set<ConstraintViolation<PatientDTO>> constraintViolations =
		            validator.validate(patientDTOInvalidDateNull);
		    // THEN
		    System.out.println(constraintViolations.toString());
		    assertEquals(1, constraintViolations.size());
		
		}
		
		
		
		// *******************************************************************
		
		

		
		
		@DisplayName("DTO - patientDTOSexNull - "
				+ "GIVEN DTO with inputs patientDTOInvalidDateNotReal"
				+ "WHEN Requested DTO value"
				+ "THEN returns expected DTO constraint violations")
		@Test
		public void patientDTOSexNull() {
		
			// GIVEN
		    
		    // WHEN
		    Set<ConstraintViolation<PatientDTO>> constraintViolations =
		            validator.validate(patientDTOSexNull);
		    // THEN
		    System.out.println(constraintViolations.toString());
		    assertEquals(1, constraintViolations.size());
		
		}
		
		
		
		// *******************************************************************
		
		

		
		
		@DisplayName("DTO - patientDTOSexEmpty - "
				+ "GIVEN DTO with inputs patientDTOInvalidDateNotReal"
				+ "WHEN Requested DTO value"
				+ "THEN returns expected DTO constraint violations")
		@Test
		public void patientDTOSexEmpty() {
		
			// GIVEN
		    
		    // WHEN
		    Set<ConstraintViolation<PatientDTO>> constraintViolations =
		            validator.validate(patientDTOSexEmpty);
		    // THEN
		    System.out.println(constraintViolations.toString());
		    assertEquals(2, constraintViolations.size());
		
		}
		
		
		
		// *******************************************************************
		
		

		
		
		@DisplayName("DTO - patientDTOSexInvalidCharacter - "
				+ "GIVEN DTO with inputs patientDTOSexInvalidCharacter"
				+ "WHEN Requested DTO value"
				+ "THEN returns expected DTO constraint violations")
		@Test
		public void patientDTOSexInvalidCharacter() {
		
			// GIVEN
		    
		    // WHEN
		    Set<ConstraintViolation<PatientDTO>> constraintViolations =
		            validator.validate(patientDTOSexInvalidCharacter);
		    // THEN
		    System.out.println(constraintViolations.toString());
		    assertEquals(2, constraintViolations.size());
		
		}
		
		
		
		// *******************************************************************

}
