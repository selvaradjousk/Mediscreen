package com.patientAssessment.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.patientAssessment.proxy.MicroserviceNoteProxy;
import com.patientAssessment.proxy.MicroservicePatientProxy;

@Service
public class AssessmentService {


	private final MicroserviceNoteProxy microserviceNoteProxy;

	private final MicroservicePatientProxy microservicePatientProxy;



  	// *******************************************************************


	public AssessmentService(MicroserviceNoteProxy microserviceNoteProxy,
			MicroservicePatientProxy microservicePatientProxy) {
		super();
		this.microserviceNoteProxy = microserviceNoteProxy;
		this.microservicePatientProxy = microservicePatientProxy;
	}



  	// *******************************************************************

	// BUSINESS LAYER: AssessmentService
	// 					- What Sex? 
	//					- What age? (calculate age from birth date)
	// 					- How many Triggers (count number of triggers in all notes of patient)
	// 					- Probability of Risk Level (SEX, AGE, TRIGGER_COUNT)


  	// *******************************************************************

	//	- What age? (calculate age from birth date)
    public int getAge(final LocalDate birthDate) {

        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        if (birthDate.isAfter(currentDate)) {
            throw new IllegalArgumentException("birth date is invalid"
            		+ " - should be below current date");
        } else if (age == 0) {
            age++;
        }

        return age;
    }




  	// *******************************************************************






  	// *******************************************************************






}
