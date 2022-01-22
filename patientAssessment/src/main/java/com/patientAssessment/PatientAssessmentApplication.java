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
	// - Le rapport doit inclure les donn�es d�mographiques du patient
	// - ainsi que le niveau de risque
	
	// BUSINESS LAYER - TASKS
	// -------------------------
	// d�terminer sa probabilit� � d�velopper un diab�te

	
	// BUSINESS LAYER INPUTS
	// ------------------------
	// un patient pourra avoir l'un des 4 niveaux de risque
	// - aucun risque (None) => aucune d�clencheurs
	// - risque limit� (Borderline) => le patient est �g� de plus de 30 ans + 2 d�clencheurs
	// - danger (In Danger) => 	homme et a moins de 30 ans + 3 termes d�clencheurs
	//							une femme et a moins de 30 ans + 4 termes d�clencheurs
	// 							le patient a plus de 30 ans + 6 termes d�clencheurs
	// - apparition pr�coce (Early onset) => 	homme et a moins de 30 ans + 5 termes d�clencheurs
	//							une femme et a moins de 30 ans + 7 termes d�clencheurs
	// 							le patient a plus de 30 ans + > 8 termes d�clencheurs
	
	
	// Les termes d�clencheurs ==> ENUM ou array
	// ------------------------------------------
	// H�moglobine A1C, Microalbumine, Taille, Poids, Fumeur, Anormal, Cholest�rol, Vertige,
	// Rechute, R�action, Anticorps.


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
