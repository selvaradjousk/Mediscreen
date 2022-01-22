package com.patientAssessment.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class PatientDTO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {


    /** The id. */
    private Integer id;

    /** The last name. */
    private String lastName;

    /** The first name. */
    private String firstName;

    /** The birth date. */
    private LocalDate birthDate;

    /** The sex. */
    private String sex;

    /** The address. */
    private String address;

    /** The phone number. */
    private String phoneNumber;

}