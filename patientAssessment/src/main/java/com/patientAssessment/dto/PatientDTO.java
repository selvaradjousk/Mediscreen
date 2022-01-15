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
public class PatientDTO {


    private Integer id;

    private String lastName;

    private String firstName;

    private LocalDate birthDate;

    private String sex;

    private String address;

    private String phoneNumber;

}