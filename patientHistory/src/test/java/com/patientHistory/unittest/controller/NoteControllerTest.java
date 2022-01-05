package com.patientHistory.unittest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

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
import org.springframework.test.web.servlet.MvcResult;
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
    private final static String NOTE_GET_URL = "/note/get/";
    private final static String NOTE_LIST_URL = "/note/list/";
    private final static String NOTE_UPDATE_URL = "/note/update/";

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

    @Test
    @DisplayName("test GET GetNoteById - "
    		+ " Given a Note id,"
    		+ " when request for GetNoteById,"
    		+ " then return as OK status")
    public void testGetNoteById() throws Exception {

    	NoteDTO noteDTO = new NoteDTO("patId", 1, date, "note");
        when(noteService.getNoteById("1")).thenReturn(noteDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
        		.get(NOTE_GET_URL + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("ote");
        verify(noteService).getNoteById("1");
    }




  	// *******************************************************************



    @Test
    @DisplayName("test GET GetAllNote - "
    		+ " Given a Note id,"
    		+ " when request for GetAllNote,"
    		+ " then return as OK status")
    public void testGetAllNote() throws Exception {

    	NoteDTO noteDTO1 = new NoteDTO("patId1", 1, date, "note1");
    	NoteDTO noteDTO2 = new NoteDTO("patId2", 1, date, "note2");
    	
        when(noteService.getAllNote(1)).thenReturn(Arrays
        		.asList(noteDTO1, noteDTO2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
        		.get(NOTE_LIST_URL + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(noteService).getAllNote(1);
        assertThat(content).contains("note1", "note2");
    }


  	// *******************************************************************





    @Test
    @DisplayName("test POST UpdateNote - "
    		+ " Given a Note id,"
    		+ " when request for UpdateNote,"
    		+ " then return as OK status and update done")
    public void testUpdateNote() throws Exception {

    	NoteDTO noteDTO = new NoteDTO("patId", 1, date, "note updated");
        when(noteService.updateNote(anyString(), any(NoteDTO.class))).thenReturn(noteDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(NOTE_UPDATE_URL + 1)
                .content(mapper.writeValueAsString(noteDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("note updated");
        verify(noteService).updateNote(anyString(), any(NoteDTO.class));
    }



  	// *******************************************************************




}
