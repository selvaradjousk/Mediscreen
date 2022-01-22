package com.patientHistory.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class NoteDTO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    /** The id. */
    private String id;

    /** The patient id. */
    private Integer patientId;

    /** The date. */
    private LocalDate date;

    /** The note. */
    private String note;


    /**
     * Instantiates a new note DTO.
     *
     * @param patientId the patient id
     * @param date the date
     * @param note the note
     */
    public NoteDTO(
    		final Integer patientId,
    		final LocalDate date,
    		final String note) {
        this.patientId = patientId;
        this.date = date;
        this.note = note;
    }
}