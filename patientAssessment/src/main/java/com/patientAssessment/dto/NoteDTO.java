package com.patientAssessment.dto;

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

}