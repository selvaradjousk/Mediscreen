package com.ui.unittest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.ui.controller.NoteController;
import com.ui.dto.NoteDTO;
import com.ui.proxy.MicroserviceNoteProxy;

@DisplayName("UNIT TESTS - Controller - Update Note")
@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteController.class)
class NoteControllerUpdateNoteTest {

	
    @MockBean
    private MicroserviceNoteProxy noteProxy;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    private NoteDTO noteDTO, noteToUpdateDTO;
    

    private LocalDate date = LocalDate.of(2021,12,31);

  	// *******************************************************************



    @BeforeEach
    public void setup() {
    	noteDTO = new NoteDTO("patId", 1, date, "note test1");
    	
    	
    	noteToUpdateDTO = new NoteDTO("patId", 1, date, "note test2");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test GET updateNoteForm - "
    		+ " Given a Note to update,"
    		+ " when request for GET updateNoteForm,"
    		+ " then return status 200 Ok")
    public void testGETupdateNoteForm() throws Exception {

        when(noteProxy.getNoteById("1")).thenReturn(noteDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/note/update/1"))
                .andExpect(model().attributeExists("noteDTO"))
                .andExpect(view().name("note/update"))
                .andExpect(status().isOk());

        verify(noteProxy).getNoteById("1");
    }


  	// *******************************************************************


    @Test
    @DisplayName("test POST updateNote - valid"
    		+ " Given a Note to update,"
    		+ " when request for updateNote,"
    		+ " then return status 200 Ok")
    public void testUpdateNote() throws Exception {
    	
        when(noteProxy.updateNote(anyString(), any(NoteDTO.class))).thenReturn(noteDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/note/update/1")
                .sessionAttr("noteDTO", noteToUpdateDTO)
                .param("patientId", noteToUpdateDTO.getPatientId().toString())
                .param("date", noteToUpdateDTO.getDate().toString())
                .param("note", noteToUpdateDTO.getNote()))
                .andExpect(redirectedUrl("/note/list/1"));

        verify(noteProxy).updateNote(anyString(), any(NoteDTO.class));
    }


  	// *******************************************************************



}
