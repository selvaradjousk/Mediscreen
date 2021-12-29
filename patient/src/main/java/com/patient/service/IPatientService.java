package com.patient.service;

import java.util.List;

import com.patient.dto.PatientDTO;

public interface IPatientService {

	List<PatientDTO> getAllPatients();

	PatientDTO updatePatient(int patientId, PatientDTO patientDTO);

    PatientDTO getPatientById(final int patientId);


}