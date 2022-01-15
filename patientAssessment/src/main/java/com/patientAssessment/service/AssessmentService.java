package com.patientAssessment.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.patientAssessment.constant.DiabetesTrigger;
import com.patientAssessment.constant.RiskLevels;
import com.patientAssessment.dto.AssessmentDTO;
import com.patientAssessment.dto.NoteDTO;
import com.patientAssessment.dto.PatientDTO;
import com.patientAssessment.proxy.MicroserviceNoteProxy;
import com.patientAssessment.proxy.MicroservicePatientProxy;

@Service
public class AssessmentService implements IAssessmentService {


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


	@Override
	public AssessmentDTO getPatientAssessment(
    		final Integer patientId) {

        PatientDTO patient = getPatient(patientId);

        int patientAge = getAge(patient.getBirthDate());

        List<NoteDTO> patientNotes = getPatientNotes(patientId);
        int patientTriggers = getPatientTriggers(patientNotes);

        String diabetesRiskLevel = getDiabetesRiskLevel(
        		patientTriggers,
        		patientAge,
        		patient.getSex());

        return new AssessmentDTO(
        		patient,
        		diabetesRiskLevel);

    }




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


    // Get the patient by Id
	public PatientDTO getPatient(final Integer patientId) {
		
		PatientDTO patient = microservicePatientProxy
				.getPatientById(patientId);

		return patient;
	}



  	// *******************************************************************

    // Get the patients notes through proxy
    public List<NoteDTO> getPatientNotes(final Integer patientId) {

		List<NoteDTO> patientNotes = microserviceNoteProxy
				.getAllNote(patientId);

		return patientNotes;
	}



  	// *******************************************************************

    // How many Triggers (count number of triggers in all notes of patient)
    public int getPatientTriggers(final List<NoteDTO> notes) {

        EnumSet<DiabetesTrigger> diabetesTriggers = EnumSet
        		.allOf(DiabetesTrigger.class);

        List<DiabetesTrigger> patientTriggers = new ArrayList();
        diabetesTriggers.forEach(diabetesTrigger -> {
            notes.forEach(note -> {
                if (StringUtils.containsIgnoreCase(
                		note.getNote(),
                		diabetesTrigger.getTrigger()) &&
                        !patientTriggers.contains(diabetesTrigger)) {
                    patientTriggers.add(diabetesTrigger);
                }
            });
        });

        return patientTriggers.size();
    }



  	// *******************************************************************

    // Probability of Risk Level (SEX, AGE, TRIGGER_COUNT)
    public String getDiabetesRiskLevel(
    		final int triggers,
    		final int patientAge,
    		final String sex) {

        String diabetesAssessment = RiskLevels.NONE.getRiskLevel();

        if ((((
        		sex.equals("F"))
        		&& patientAge < 30
        		&& triggers >= 7)
        		|| ((sex.equals("M"))
        				&& patientAge < 30
        				&& triggers >= 5))
        		|| (patientAge >= 30
        		&& triggers >= 8)) {
            diabetesAssessment = RiskLevels.EARLY_ONSET.getRiskLevel();

        } else if ((
        		sex.equals("M")
        		&& patientAge < 30
        		&& triggers >= 3)
        		|| ((sex.equals("F"))
        				&& patientAge < 30
        				&& triggers >= 4)
        		|| (patientAge >= 30
        		&& triggers >= 6)) {
            diabetesAssessment = RiskLevels.IN_DANGER.getRiskLevel();

        } else if ((
        		patientAge >= 30
        		&& triggers >= 2)) {
            diabetesAssessment = RiskLevels.BORDERLINE.getRiskLevel();
        }

        return diabetesAssessment;
    }



  	// *******************************************************************


}
