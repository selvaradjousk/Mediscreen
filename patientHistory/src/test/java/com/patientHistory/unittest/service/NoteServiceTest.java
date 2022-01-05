package com.patientHistory.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.model.Note;
import com.patientHistory.repository.NoteRepository;
import com.patientHistory.service.NoteService;
import com.patientHistory.util.NoteMapper;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @InjectMocks
    private NoteService noteService;

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private NoteMapper noteMapper;

    private LocalDate date = LocalDate.of(2021,12,31);

    private static Note note1;

    private static NoteDTO note1DTO;


  	// *******************************************************************


    @BeforeEach
    public void setUp() {
        note1DTO = new NoteDTO("1", 1, date, "note 1");

        note1 = new Note("1",1, date, "note 1");


    }


  	// *******************************************************************


    @Test
    @DisplayName("test POST addNote - "
    		+ " Given a Note to add,"
    		+ " when request for addNote,"
    		+ " then return as expected")
    public void testAddNote() {
        NoteDTO noteToAddDTO = new NoteDTO("patId", 1, date, "note");
        Note noteToAdd = new Note("patId", 1, date, "note");
        when(noteMapper.toNote(any(NoteDTO.class))).thenReturn(noteToAdd);
        when(noteRepository.save(any(Note.class))).thenReturn(note1);
        when(noteMapper.toNoteDTO(any(Note.class))).thenReturn(note1DTO);

        NoteDTO noteSaved = noteService.addNote(noteToAddDTO);


        assertThat(noteSaved).usingRecursiveComparison()
        					.ignoringCollectionOrder()
        					.isEqualTo(note1DTO);

        InOrder inOrder = inOrder(noteRepository, noteMapper);
        inOrder.verify(noteMapper).toNote(any(NoteDTO.class));
        inOrder.verify(noteRepository).save(any(Note.class));
        inOrder.verify(noteMapper).toNoteDTO(any(Note.class));
    }



  	// *******************************************************************




}
