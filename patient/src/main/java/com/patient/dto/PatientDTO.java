package com.patient.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.patient.constant.Constraints;

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


    @NotEmpty(message = "Lastname is mandatory")
    @Length(
    		max = Constraints.LAST_NAME_MAX_SIZE,
    		message = "The maximum length for lastName is 125 characters")
    private String lastName;


    @NotEmpty(message = "Firstname is mandatory")
    @Length(
    		max = Constraints.FIRST_NAME_MAX_SIZE,
    		message = "The maximum length for firstName is 125 characters")
    private String firstName;


    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Please enter a valid birth date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;


    @NotEmpty(message = "Sex is mandatory")
    @Length(
    		max = Constraints.SEX_MAX_SIZE,
    		message = "The maximum length for sex is 1 characters")
    @Pattern(
    		regexp = "^[M|F]{1}$",
    		message = "Please enter character M or F")
    private String sex;


    @Length(
    		max = Constraints.ADDRESS_MAX_SIZE,
    		message = "The maximum length for address is 150 characters")
    private String address;


    @Length(
    		max = Constraints.PHONE_MAX_SIZE,
    		message = "Please enter a valid phone number")
    private String phoneNumber;


    public PatientDTO(
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