package com.ui.unittest.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Arrays;

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

@DisplayName("UNIT TESTS - Controller - Note GetList")
@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteController.class)
class NoteControllerListNotesTest {

    @MockBean
    private MicroserviceNoteProxy noteProxy;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private NoteDTO noteDTO1, noteDTO2;
    
    private LocalDate date = LocalDate.of(2021,12,31);


  	// *******************************************************************


    @BeforeEach
    public void setup() {
    	noteDTO1 = new NoteDTO("patId", 1, date, "note test1");
    	noteDTO2 = new NoteDTO("patId", 1, date, "note test2");

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************


    @Test
    @DisplayName("test GET note list - without search keyword"
    		+ "Given a note list,"
    		+ " when getAllNote without search keyword,"
    		+ " then return Ok status")
    public void testNoteList() throws Exception {

    	when(noteProxy.getAllNote(1)).thenReturn(Arrays.asList(noteDTO1, noteDTO2));
        mockMvc.perform(MockMvcRequestBuilders.get("/note/list/1"))
                .andExpect(model().attributeExists("patientId"))
                .andExpect(model().attributeExists("notes"))
                .andExpect(view().name("note/list"))
                .andExpect(status().isOk());

        verify(noteProxy).getAllNote(1);
    }





  	// *******************************************************************





}
