package com.patient.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.patient.dto.PatientDTO;
import com.patient.service.IPatientService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {


    private final IPatientService patientService;



  	// *******************************************************************



    @Autowired
    public PatientController(
    		final IPatientService patientService) {
        this.patientService = patientService;
    }



  	// *******************************************************************



    @GetMapping("/list")
    public List<PatientDTO> getPatientList(
    		@RequestParam(value = "keyword", required = false) final String keyword) {

        List<PatientDTO> patientList = patientService
        		.getAllPatients(keyword);

        return patientList;
    }



  	// *******************************************************************


    @PostMapping("/update/{id}")
    public PatientDTO updatePatient(
    		@PathVariable("id") final Integer patientId,
    		@Valid @RequestBody final PatientDTO patientDTO) {

        PatientDTO patientUpdated = patientService
        		.updatePatient(patientId, patientDTO);

        return patientUpdated;
    }


  	// *******************************************************************

    @GetMapping("/get/{id}")
    public PatientDTO getPatientById(
    		@PathVariable("id") final Integer patientId) {

        PatientDTO patient = patientService
        		.getPatientById(patientId);

        return patient;
    }



  	// *******************************************************************

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO addPatient(
    		@Valid @RequestBody final PatientDTO patientDTO) {

        PatientDTO patientAdded = patientService
        		.addPatient(patientDTO);

        return patientAdded;
    }




  	// *******************************************************************



}
