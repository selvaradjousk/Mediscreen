package com.patientHistory.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

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
    private final static String NOTE_GET_URL = "/note/get/";
    private final static String NOTE_LIST_URL = "/note/list/";
    

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



    @Test
    @DisplayName("test GET GetNoteById - "
    		+ " Given a Note id,"
    		+ " when request for GetNoteById,"
    		+ " then return as OK status")
    public void testGetNoteById() throws Exception {

        List<NoteDTO> notes = noteService.getAllNote(1);
        String id = notes.get(0).getId();

        ResponseEntity<NoteDTO> response = restTemplate
        		.getForEntity("http://localhost:" + port +
                NOTE_GET_URL + id, NoteDTO.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertThat(response.getBody())
                .hasFieldOrPropertyWithValue("note", "note test")
                .isNotNull();
    }




  	// *******************************************************************


    @Test
    @DisplayName("test GET GetNoteById invalid id - "
    		+ " Given a Note id,"
    		+ " when request for GetNoteById,"
    		+ " then return as Not Found Status")
    public void testGetNoteByIdInvalidId() throws Exception {

        ResponseEntity<NoteDTO> response = restTemplate
        		.getForEntity("http://localhost:" + port +
                NOTE_GET_URL + 6, NoteDTO.class);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());

    }

        

      	// *******************************************************************


    @Test
    @DisplayName("test GET GetAllNotes - "
    		+ " Given a Patient id,"
    		+ " when request for GetAllNotes,"
    		+ " then return as 200 OK Status")
    public void testGetAllNotes() throws Exception {

        ResponseEntity<Object> response = restTemplate
        		.getForEntity("http://localhost:" + port +
                NOTE_LIST_URL + 1, Object.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertThat(response.getBody())
                .asList()
                .asString()
                .contains("note test");
        
        assertThat(response.getBody())
        .asList()
        .size().isGreaterThan(1)
        .asString()
        .isNotNull();
    }

        

      	// *******************************************************************



}
