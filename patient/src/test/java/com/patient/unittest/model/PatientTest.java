package com.patient.unittest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.patient.model.Patient;

@DisplayName("UNIT TESTS - MODEL - PATIENT")
class PatientTest {

	private Patient patient;
	private LocalDate birthday = LocalDate.now();

	@BeforeEach
	void init_test() {
		patient = new Patient("lastname", "firstname", birthday, "sex", "address", "phone");
	}


	// *******************************************************************	
		
	
	@DisplayName("Get DO values - "
			+ "GIVEN DO value "
			+ "WHEN Requested DO value"
			+ "THEN returns expected DO values")
	@Test
	void testGetPatientValues() {
		assertEquals("lastname", patient.getLastName());
		assertEquals("firstname", patient.getFirstName());
		assertEquals("sex", patient.getSex());
		assertEquals(birthday, patient.getBirthDate());
		assertEquals("address", patient.getAddress());
		assertEquals("phone", patient.getPhoneNumber());

	}


	// *******************************************************************	
		
	
	@DisplayName("Get DO values after SET new values- "
			+ "GIVEN new DO value set "
			+ "WHEN Requested DO value"
			+ "THEN returns expected DO values")
	@Test
	void testSetPatientValues() {

		assertEquals("lastname", patient.getLastName());
		assertEquals("firstname", patient.getFirstName());
		assertEquals("sex", patient.getSex());
		assertEquals(birthday, patient.getBirthDate());
		assertEquals("address", patient.getAddress());
		assertEquals("phone", patient.getPhoneNumber());

		LocalDate newBirthday = LocalDate.now();

		patient.setLastName("newLastname");
		patient.setFirstName("newFirstname");
		patient.setSex("newSex");
		patient.setBirthDate(newBirthday);
		patient.setAddress("newAddress");
		patient.setPhoneNumber("newPhone");

		assertEquals("newLastname", patient.getLastName());
		assertEquals("newFirstname", patient.getFirstName());
		assertEquals("newSex", patient.getSex());
		assertEquals(newBirthday, patient.getBirthDate());
		assertEquals("newAddress", patient.getAddress());
		assertEquals("newPhone", patient.getPhoneNumber());
	}

}
