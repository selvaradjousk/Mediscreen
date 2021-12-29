package com.patient.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patient.dto.PatientDTO;
import com.patient.exception.ResourceNotFoundException;
import com.patient.model.Patient;
import com.patient.repository.PatientRepository;
import com.patient.util.PatientMapper;

@Service
public class PatientService implements IPatientService {

	private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;




  	// *******************************************************************



    public PatientService(
    		final PatientRepository patientRepository,
    		final PatientMapper patientMapper) {
		super();
		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
	}




  	// *******************************************************************



    @Override
	public List<PatientDTO> getAllPatients(){

    	List<PatientDTO> patientList = patientRepository.findAll().stream()
                .map(patient -> patientMapper.toPatientDTO(patient))
                .collect(Collectors.toList());
        
        return patientList;
    }



  	// *******************************************************************




    public PatientDTO updatePatient(
    		final int patientId,
    		final PatientDTO patientDTO) {

        patientRepository.findById(patientId).orElseThrow(() ->
                new ResourceNotFoundException("Patient Id not found"));

        Patient patientToUpdate = patientMapper
        		.toPatient(patientDTO);

        patientToUpdate.setId(patientId);

        Patient patientUpdated = patientRepository
        		.save(patientToUpdate);

        return patientMapper.toPatientDTO(patientUpdated);
    }



  	// *******************************************************************



    public PatientDTO getPatientById(final int patientId) {

        Patient patient = patientRepository
        		.findById(patientId).orElseThrow(() ->
                new ResourceNotFoundException(
                		"Patient not found"));

        return patientMapper.toPatientDTO(patient);
    }



  	// *******************************************************************





}
