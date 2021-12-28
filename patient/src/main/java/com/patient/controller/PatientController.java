package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.dto.PatientDTO;
import com.patient.service.IPatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {


    private final IPatientService patientService;


    @Autowired
    public PatientController(
    		final IPatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/list")
    public List<PatientDTO> getPatientList() {

        List<PatientDTO> patientList = patientService
        		.getAllPatients();

        return patientList;
    }



}
