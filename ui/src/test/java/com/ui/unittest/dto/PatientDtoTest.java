package com.ui.unittest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ui.dto.PatientDTO;

@DisplayName("UNIT TESTS - DTO - PATIENT")
class PatientDtoTest {

	private PatientDTO patientDto;
	private LocalDate birthday = LocalDate.now();

	@BeforeEach
	void init_test() {
		patientDto = new PatientDTO("lastname", "firstname", birthday, "sex", "address", "phone");
	}


	// *******************************************************************	
		
	
	@DisplayName("Get DTO values - "
			+ "GIVEN DTO value "
			+ "WHEN Requested DTO value"
			+ "THEN returns expected DTO values")
	@Test
	void testGetPatientValues() {
		assertEquals("lastname", patientDto.getLastName());
		assertEquals("firstname", patientDto.getFirstName());
		assertEquals("sex", patientDto.getSex());
		assertEquals(birthday, patientDto.getBirthDate());
		assertEquals("address", patientDto.getAddress());
		assertEquals("phone", patientDto.getPhoneNumber());

	}


	// *******************************************************************	
		
	
	@DisplayName("Get DTO values after SET new values- "
			+ "GIVEN new DTO value set "
			+ "WHEN Requested DTO value"
			+ "THEN returns expected DTO values")
	@Test
	void testSetPatientValues() {

		assertEquals("lastname", patientDto.getLastName());
		assertEquals("firstname", patientDto.getFirstName());
		assertEquals("sex", patientDto.getSex());
		assertEquals(birthday, patientDto.getBirthDate());
		assertEquals("address", patientDto.getAddress());
		assertEquals("phone", patientDto.getPhoneNumber());

		LocalDate newBirthday = LocalDate.now();

		patientDto.setLastName("newLastname");
		patientDto.setFirstName("newFirstname");
		patientDto.setSex("newSex");
		patientDto.setBirthDate(newBirthday);
		patientDto.setAddress("newAddress");
		patientDto.setPhoneNumber("newPhone");

		assertEquals("newLastname", patientDto.getLastName());
		assertEquals("newFirstname", patientDto.getFirstName());
		assertEquals("newSex", patientDto.getSex());
		assertEquals(newBirthday, patientDto.getBirthDate());
		assertEquals("newAddress", patientDto.getAddress());
		assertEquals("newPhone", patientDto.getPhoneNumber());
	}

}
