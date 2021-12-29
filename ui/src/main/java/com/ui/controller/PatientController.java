package com.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ui.proxy.MicroservicePatientProxy;

@Controller
@RequestMapping({"/patient"})
public class PatientController {


    private MicroservicePatientProxy patientProxy;



  	// *******************************************************************



    @Autowired
    public PatientController(final MicroservicePatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }




  	// *******************************************************************



    @GetMapping({"/list"})
    public String getPatientList(
    		final Model model) {

        model.addAttribute(
        		"patients",
        		this.patientProxy.getPatientList());

        return "patient/list";
    }





  	// *******************************************************************







}
