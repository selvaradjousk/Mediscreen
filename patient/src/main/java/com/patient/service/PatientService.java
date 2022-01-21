package com.patient.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.patient.dto.PatientDTO;
import com.patient.exception.DataAlreadyRegisteredException;
import com.patient.exception.MultiplePatientsLastNameException;
import com.patient.exception.ResourceNotFoundException;
import com.patient.model.Patient;
import com.patient.repository.PatientRepository;
import com.patient.util.PatientMapper;

/**
 * The Class PatientService.
 */
@Service
public class PatientService implements IPatientService {

	/** The logger. */
	private Logger logger = LoggerFactory
			.getLogger(PatientService.class);


	// ##############################################################
	/** The patient repository. */
	private final PatientRepository patientRepository;

    /** The patient mapper. */
    private final PatientMapper patientMapper;




  	// *******************************************************************



    /**
	   * Instantiates a new patient service.
	   *
	   * @param patientRepository the patient repository
	   * @param patientMapper the patient mapper
	   */
	  public PatientService(
    		final PatientRepository patientRepository,
    		final PatientMapper patientMapper) {
		super();
		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
	}




  	// *******************************************************************



    /**
	   * Gets the all patients.
	   *
	   * @param keyword the keyword optional input
	   * @return the all patients
	   */
	  @Override
	public List<PatientDTO> getAllPatients(final String keyword) {

		logger.debug(" *** Service: getAllPatients - requested");

        if (keyword != null) {
    	List<PatientDTO> patientList = patientRepository
    			.findByKeyword(keyword).stream()
                .map(patient -> patientMapper.toPatientDTO(patient))
                .collect(Collectors.toList());

	    logger.info(" *** Service: getAllPatients with keyword - returned");
        
        return patientList;
    }

    List<PatientDTO> allPatient = patientRepository
    		.findAll().stream()
            .map(patient -> patientMapper.toPatientDTO(patient))
            .collect(Collectors.toList());

    logger.info(" *** Service: getAllPatients classic - returned");

    return allPatient;
    
	  }


  	// *******************************************************************




    /**
	   * Update patient.
	   *
	   * @param patientId the patient id
	   * @param patientDTO the patient DTO
	   * @return the patient DTO
	   */
	  public PatientDTO updatePatient(
    		final int patientId,
    		final PatientDTO patientDTO) {

		logger.debug(" *** Service: updatePatient - requested");

        patientRepository.findById(patientId).orElseThrow(() ->
                new ResourceNotFoundException("Patient Id not found"));

        Patient patientToUpdate = patientMapper
        		.toPatient(patientDTO);

        patientToUpdate.setId(patientId);

        Patient patientUpdated = patientRepository
        		.save(patientToUpdate);

        logger.info(" *** Service: updatePatient - done");

        return patientMapper.toPatientDTO(patientUpdated);
    }



  	// *******************************************************************



    /**
	   * Gets the patient by id.
	   *
	   * @param patientId the patient id
	   * @return the patient by id
	   */
	  public PatientDTO getPatientById(final int patientId) {

		logger.debug(" *** Service: getPatientById - requested");

        Patient patient = patientRepository
        		.findById(patientId).orElseThrow(() ->
                new ResourceNotFoundException(
                		"Patient not found"));

        logger.info(" *** Service: getPatientById - done");

        return patientMapper.toPatientDTO(patient);
    }



  	// *******************************************************************

    /**
	   * Gets the patient.
	   *
	   * @param lastName the last name
	   * @return the patient
	   */
	  @Override
    public PatientDTO getPatient(String lastName) {

		  logger.debug(" *** Service: getPatient by lastname - requested");

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

		  logger.info(" *** Service: getPatient by lastname - done");

	      return patientMapper.toPatientDTO(patient);
    }

  	// *******************************************************************

    /**
	   * Adds the patient.
	   *
	   * @param patientDTO the patient DTO
	   * @return the patient DTO
	   */
	  public PatientDTO addPatient(final PatientDTO patientDTO) {

		logger.debug(" *** Service: addPatient - requested");

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

		logger.info(" *** Service: addPatient - done");

        return patientMapper.toPatientDTO(patientSaved);
    }




  	// *******************************************************************

    /**
	   * Delete patient.
	   *
	   * @param patientId the patient id
	   */
	  public void deletePatient(final int patientId) {

		logger.debug(" *** Service: deletePatient - requested");

        patientRepository.findById(patientId)
        .orElseThrow(() ->
                new ResourceNotFoundException("Patient does not exists"));

        patientRepository.deleteById(patientId);

		logger.info(" *** Service: deletePatient - done");

	  }



  	// *******************************************************************

}
