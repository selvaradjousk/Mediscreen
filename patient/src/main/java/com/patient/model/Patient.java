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

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "first_name")
    private String firstName;


    @Column(name = "date_of_birth")
    private LocalDate birthDate;


    private String sex;


    private String address;


    @Column(name = "phone_number")
    private String phoneNumber;


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
