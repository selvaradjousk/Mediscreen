package com.patientHistory.unittest.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patientHistory.controller.NoteController;
import com.patientHistory.dto.NoteDTO;
import com.patientHistory.service.INoteService;

@DisplayName("UNIT TESTS - CONTROLLER - PATIENT HISTORY")
@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteController.class)
class NoteControllerTest {


    @MockBean
    private INoteService noteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext context;

    private NoteDTO noteDTO;

    private final static String NOTE_ADD_URL = "/note/add";

    private LocalDate date = LocalDate.now();
    
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }




  	// *******************************************************************



    @Test
    @DisplayName("test POST addNote - "
    		+ " Given a Note to add,"
    		+ " when request for addNote,"
    		+ " then return status 200 Ok")
    public void testAddNote() throws Exception {

    	noteDTO = new NoteDTO("patId", 1, date, "note");
    	
    	when(noteService.addNote(noteDTO)).thenReturn(noteDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(NOTE_ADD_URL)
                .content(mapper.writeValueAsString(noteDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }


  	// *******************************************************************






}
