package com.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ui.dto.PatientDTO;
import com.ui.proxy.MicroservicePatientProxy;

import feign.Param;

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
    		final Model model,
    		@Param("keyword") final String keyword) {

        model.addAttribute(
        		"patients",
        		this.patientProxy.getPatientList(keyword));

        model.addAttribute("keyword", keyword);

        return "patient/list";
    }





  	// *******************************************************************


    @GetMapping({"/update/{id}"})
    public String showUpdateForm(
    		@PathVariable("id") final Integer patientId,
    		final Model model) {

        PatientDTO patient = this.patientProxy
        		.getPatientById(patientId);

        model.addAttribute("patientDTO", patient);

        return "patient/update";
    }





  	// *******************************************************************


    @PostMapping({"/update/{id}"})
    public String updateUser(
    		@PathVariable("id") final Integer patientId,
    		@Valid final PatientDTO patientDTO,
    		final BindingResult result) {


        if (result.hasErrors()) {

            return "patient/update";
        } else {
            this.patientProxy.updatePatient(patientId, patientDTO);

            return "redirect:/patient/list";
        }
    }




  	// *******************************************************************


}
