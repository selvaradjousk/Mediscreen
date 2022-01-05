package com.patientHistory.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    private String id;

    private Integer patientId;

    private LocalDate date;

    private String note;


    public NoteDTO(
    		final Integer patientId,
    		final LocalDate date,
    		final String note) {
        this.patientId = patientId;
        this.date = date;
        this.note = note;
    }
}