package com.patientHistory.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class NoteDTO {

    private String id;

    private Integer patId;

    private LocalDate date;

    private String note;


    public NoteDTO(
    		final Integer patId,
    		final LocalDate date,
    		final String note) {
        this.patId = patId;
        this.date = date;
        this.note = note;
    }
}