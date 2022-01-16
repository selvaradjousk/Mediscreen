package com.patient.service;

import java.util.List;

import com.patient.dto.PatientDTO;

public interface IPatientService {

	List<PatientDTO> getAllPatients(String keyword);

	PatientDTO updatePatient(int patientId, PatientDTO patientDTO);

    PatientDTO getPatientById(int patientId);

    PatientDTO addPatient(final PatientDTO patientDTO);

    void deletePatient(final int patientId);

    PatientDTO getPatient(String lastName);

}