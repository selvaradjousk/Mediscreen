package com.patientHistory.unittest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.patientHistory.dto.NoteDTO;

@DisplayName("UNIT TESTS - MODEL - PATIENT HISTORY")
class PatientHistoryDtoTest {

	private NoteDTO noteDto;
	private LocalDate date = LocalDate.now();

	@BeforeEach
	void init_test() {
		noteDto = new NoteDTO("patId", 1, date, "note");
	}


	// *******************************************************************	
		
	
	@DisplayName("Get DTO values - "
			+ "GIVEN DTO value "
			+ "WHEN Requested DTO value"
			+ "THEN returns expected DTO values")
	@Test
	void testGetPatientHistoryDTOValues() {
		assertEquals(1, noteDto.getPatId());
		assertEquals("patId", noteDto.getId());
		assertEquals(date, noteDto.getDate());
		assertEquals("note", noteDto.getNote());

	}


	// *******************************************************************	
		
	
	@DisplayName("Get DTO values after SET new values- "
			+ "GIVEN new DTO value set "
			+ "WHEN Requested DTO value"
			+ "THEN returns expected DTO values")
	@Test
	void testSetPatientHistoryDTOValues() {

		assertEquals(1, noteDto.getPatId());
		assertEquals("patId", noteDto.getId());
		assertEquals(date, noteDto.getDate());
		assertEquals("note", noteDto.getNote());

		LocalDate newDate = LocalDate.of(2021,12,31);

		noteDto.setId("newId");
		noteDto.setPatId(3);
		noteDto.setDate(newDate);
		noteDto.setNote("newNote");


		assertEquals(3, noteDto.getPatId());
		assertEquals("newId", noteDto.getId());
		assertEquals(newDate, noteDto.getDate());
		assertEquals("newNote", noteDto.getNote());
	}


	// *******************************************************************	
		
	
}
