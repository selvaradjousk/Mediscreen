package com.patient.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patient.dto.PatientDTO;
import com.patient.repository.PatientRepository;
import com.patient.util.PatientMapper;

@Service
public class PatientService implements IPatientService {

	private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;


    public PatientService(
    		final PatientRepository patientRepository,
    		final PatientMapper patientMapper) {
		super();
		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
	}


    @Override
	public List<PatientDTO> getAllPatients(){

    	List<PatientDTO> patientList = patientRepository.findAll().stream()
                .map(patient -> patientMapper.toPatientDTO(patient))
                .collect(Collectors.toList());
        
        return patientList;
    }


}
