package com.ui.unittest.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

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
import com.ui.proxy.MicroserviceNoteProxy;

@DisplayName("UNIT TESTS - Controller - Delete Note")
@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteController.class)
class NoteControllerDeleteNoteTest {

	
    @MockBean
    private MicroserviceNoteProxy microserviceNoteProxy;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


  	// *******************************************************************



    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    



  	// *******************************************************************



    @Test
    @DisplayName("test DELETE NoteById - "
    		+ " Given a Note with id,"
    		+ " when request for deleteNote,"
    		+ " then return status 200 Ok")
    public void testGetNoteById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/note/delete/1/1"))
        .andExpect(redirectedUrl("/note/list/1"));

        verify(microserviceNoteProxy).deleteNote("1");
    }


  	// *******************************************************************

   

}
