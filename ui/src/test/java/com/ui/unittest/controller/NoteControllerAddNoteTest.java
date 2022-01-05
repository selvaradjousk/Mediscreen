package com.ui.unittest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.controller.NoteController;
import com.ui.dto.NoteDTO;
import com.ui.proxy.MicroserviceNoteProxy;

@DisplayName("UNIT TESTS - Controller - Add Note")
@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteController.class)
class NoteControllerAddNoteTest {

	
    @MockBean
    private MicroserviceNoteProxy noteProxy;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    private NoteDTO noteDTO, noteToAddDTO;
    

    private final static String NOTE_ADD_GET_URL = "/note/add/";
    private final static String NOTE_ADD_POST_URL = "/note/validate";

    private LocalDate date = LocalDate.of(2021,12,31);

  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	noteDTO = new NoteDTO("patId", 1, date, "note test");
    	
    	noteToAddDTO = new NoteDTO("patId", 1, date, "note test");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test GET addNoteForm - "
    		+ " Given a Note to add,"
    		+ " when request for GET addNoteForm,"
    		+ " then return status 200 Ok")
    public void testGETaddNoteForm() throws Exception {

    	mockMvc.perform(MockMvcRequestBuilders.get(NOTE_ADD_GET_URL + "1"))
        .andExpect(model().attributeExists("noteDTO"))
        .andExpect(view().name("note/add"))
        .andExpect(status().isOk());

    }


  	// *******************************************************************


    @Test
    @DisplayName("test POST addNote - valid"
    		+ " Given a Note to add,"
    		+ " when request for addNote,"
    		+ " then return status redirect note list page")
    public void testAddNote() throws Exception {
    	
        when(noteProxy.addNote(any(NoteDTO.class))).thenReturn(noteToAddDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(NOTE_ADD_POST_URL)
                .sessionAttr("noteDTO", noteToAddDTO)
                .param("patientId", noteToAddDTO.getPatientId().toString())
                .param("date", noteToAddDTO.getDate().toString())
                .param("note", noteToAddDTO.getNote()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/note/list/1"));

        verify(noteProxy).addNote(any(NoteDTO.class));
    }


  	// *******************************************************************

     

}
