package com.patientHistory.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.repository.NoteRepository;
import com.patientHistory.service.NoteService;
import com.patientHistory.util.NoteMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class NoteControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NoteRepository noteRespository;
    
    @Autowired
    private NoteService noteService;
    
    @Autowired
    NoteMapper noteMapper;

    @LocalServerPort
    private int port;
 
    private LocalDate date = LocalDate.of(2021,12,31);
    
    private final static String NOTE_ADD_URL = "/note/add";
    

  	// *******************************************************************


    @Test
    @DisplayName("test POST addNote - "
    		+ " Given a Note to add,"
    		+ " when request for addNote,"
    		+ " then return status 200 Ok")
    public void testAddNote() {

    	NoteDTO noteToAdd = new NoteDTO("patId", 1, date, "note test");

        ResponseEntity<NoteDTO> response = restTemplate.postForEntity("http://localhost:" + port + NOTE_ADD_URL,
                noteToAdd, NoteDTO.class);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertThat(response.getBody())
                .hasFieldOrPropertyWithValue("note", "note test")
                .isNotNull();
    }
    

  	// *******************************************************************


}
