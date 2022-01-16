package com.patient.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patient.dto.PatientDTO;
import com.patient.exception.DataAlreadyRegisteredException;
import com.patient.exception.MultiplePatientsLastNameException;
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
	public List<PatientDTO> getAllPatients(final String keyword) {

        if (keyword != null) {
    	List<PatientDTO> patientList = patientRepository
    			.findByKeyword(keyword).stream()
                .map(patient -> patientMapper.toPatientDTO(patient))
                .collect(Collectors.toList());
        
        return patientList;
    }

    List<PatientDTO> allPatient = patientRepository
    		.findAll().stream()
            .map(patient -> patientMapper.toPatientDTO(patient))
            .collect(Collectors.toList());

    return allPatient;
    
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

    @Override
    public PatientDTO getPatient(String lastName) {
      int lastNameOccurences = patientRepository.countByLastName(lastName);
      if (lastNameOccurences == 0) {

        throw new ResourceNotFoundException(
            "Patient not found");
      }
      if (lastNameOccurences > 1) {

        throw new MultiplePatientsLastNameException(
            "There are several patients with this Last name, search by patient id");
      }
      Patient patient = patientRepository.findByLastName(lastName);
      return patientMapper.toPatientDTO(patient);
    }

  	// *******************************************************************

    public PatientDTO addPatient(final PatientDTO patientDTO) {

        Patient patientFound = patientRepository
        		.findByLastNameAndFirstNameAndBirthDate(
        				patientDTO.getLastName(),
        				patientDTO.getFirstName(),
        				patientDTO.getBirthDate());

        if (patientFound != null) {
            throw new DataAlreadyRegisteredException(
            		"The patient data already exists");
        }
        Patient patientSaved = patientRepository
        		.save(patientMapper.toPatient(patientDTO));

        return patientMapper.toPatientDTO(patientSaved);
    }




  	// *******************************************************************

    public void deletePatient(final int patientId) {

        patientRepository.findById(patientId)
        .orElseThrow(() ->
                new ResourceNotFoundException("Patient does not exists"));

        patientRepository.deleteById(patientId);
    }



  	// *******************************************************************

}
