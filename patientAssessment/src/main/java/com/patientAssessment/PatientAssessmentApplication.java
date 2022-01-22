package com.patientAssessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The Class PatientAssessmentApplication.
 */
@EnableFeignClients("com.patientAssessment")
@SpringBootApplication
public class PatientAssessmentApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PatientAssessmentApplication.class, args);
	}


	// OBJECTIF - SPRINT 3 - MICROSERVICE - PATIENT ASSESSMENT
	// -------------------------------------------------------
	// - Le rapport doit inclure les données démographiques du patient
	// - ainsi que le niveau de risque
	
	// BUSINESS LAYER - TASKS
	// -------------------------
	// déterminer sa probabilité à développer un diabète

	
	// BUSINESS LAYER INPUTS
	// ------------------------
	// un patient pourra avoir l'un des 4 niveaux de risque
	// - aucun risque (None) => aucune déclencheurs
	// - risque limité (Borderline) => le patient est âgé de plus de 30 ans + 2 déclencheurs
	// - danger (In Danger) => 	homme et a moins de 30 ans + 3 termes déclencheurs
	//							une femme et a moins de 30 ans + 4 termes déclencheurs
	// 							le patient a plus de 30 ans + 6 termes déclencheurs
	// - apparition précoce (Early onset) => 	homme et a moins de 30 ans + 5 termes déclencheurs
	//							une femme et a moins de 30 ans + 7 termes déclencheurs
	// 							le patient a plus de 30 ans + > 8 termes déclencheurs
	
	
	// Les termes déclencheurs ==> ENUM ou array
	// ------------------------------------------
	// Hémoglobine A1C, Microalbumine, Taille, Poids, Fumeur, Anormal, Cholestérol, Vertige,
	// Rechute, Réaction, Anticorps.


	// MVC schema - conception for implementation
	// ------------
	// DTO (PatientDTO, NoteDTO, AssessmentDTO)
	// ENUMS (RiskLevels, DiabetesTrigger)
	// PROXY (MS-NoteProxy, MS-PatientProxy)
	// BUSINESS LAYER: AssessmentService
	// 					- What Sex? 
	//					- What age? (calculate age from birth date)
	// 					- How many Triggers (count number of triggers in all notes of patient)
	// 					- Probability of Risk Level (SEX, AGE, TRIGGER_COUNT)
	// CONTROLLER: 	getPatientAssessment(patientId):AssessmentDTO  => http://localhost:8083/assess/{id}













}
