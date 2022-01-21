package com.patient.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Patient.
 */
@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {


    /** The id of the patient. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /** The last name of the patient. */
    @Column(name = "last_name")
    private String lastName;


    /** The first name of the patient. */
    @Column(name = "first_name")
    private String firstName;


    /** The birth date of the patient. */
    @Column(name = "date_of_birth")
    private LocalDate birthDate;


    /** The sex of the patient. */
    private String sex;


    /** The address of the patient. */
    private String address;


    /** The phone number of the patient. */
    @Column(name = "phone_number")
    private String phoneNumber;


    /**
     * Instantiates a new patient.
     *
     * @param lastName the last name
     * @param firstName the first name
     * @param birthDate the birth date
     * @param sex the sex
     * @param address the address
     * @param phoneNumber the phone number
     */
    public Patient(
    		final String lastName,
    		final String firstName,
    		final LocalDate birthDate,
    		final String sex,
            final String address,
            final String phoneNumber) {

    	this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
