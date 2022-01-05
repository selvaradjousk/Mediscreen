package com.patientHistory.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notes")              // Maps Entity class objects to JSON formatted Documents
public class Note {

    @Id		// making this variable as ID, will be auto-generated by MongoDB
    private String id;

    @Field(value = "patient_id")
    private Integer patientId;

    private LocalDate date;

    private String note;



  	// *******************************************************************



    public Note(
    		final Integer patientId,
    		final LocalDate date,
    		final String note) {
        this.patientId = patientId;
        this.date = date;
        this.note = note;
    }


  	// *******************************************************************



}