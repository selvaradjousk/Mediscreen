package com.ui.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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

    private Integer patId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String note;

    public NoteDTO(
    		final LocalDate date,
    		final String note) {
        this.date = date;
        this.note = note;
    }

    public NoteDTO(
    		final Integer patId,
    		final LocalDate date,
    		final String note) {
        this.patId = patId;
        this.date = date;
        this.note = note;
    }
}