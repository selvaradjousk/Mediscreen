package com.patientAssessment.dto;

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

}