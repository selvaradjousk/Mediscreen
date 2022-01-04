package com.patientHistory.unittest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.patientHistory.model.Note;

@DisplayName("UNIT TESTS - MODEL - PATIENT HISTORY")
class PatientHistoryTest {

	private Note note;
	private LocalDate date = LocalDate.now();

	@BeforeEach
	void init_test() {
		note = new Note("patId", 1, date, "note");
	}


	// *******************************************************************	
		
	
	@DisplayName("Get DO values - "
			+ "GIVEN DO value "
			+ "WHEN Requested DO value"
			+ "THEN returns expected DO values")
	@Test
	void testGetPatientHistoryValues() {
		assertEquals(1, note.getPatId());
		assertEquals("patId", note.getId());
		assertEquals(date, note.getDate());
		assertEquals("note", note.getNote());

	}


	// *******************************************************************	
		
	
	@DisplayName("Get DO values after SET new values- "
			+ "GIVEN new DO value set "
			+ "WHEN Requested DO value"
			+ "THEN returns expected DO values")
	@Test
	void testSetPatientHistoryValues() {

		assertEquals(1, note.getPatId());
		assertEquals("patId", note.getId());
		assertEquals(date, note.getDate());
		assertEquals("note", note.getNote());

		LocalDate newDate = LocalDate.of(2021,12,31);

		note.setId("newId");
		note.setPatId(3);
		note.setDate(newDate);
		note.setNote("newNote");


		assertEquals(3, note.getPatId());
		assertEquals("newId", note.getId());
		assertEquals(newDate, note.getDate());
		assertEquals("newNote", note.getNote());
	}


	// *******************************************************************	
		
	
}
