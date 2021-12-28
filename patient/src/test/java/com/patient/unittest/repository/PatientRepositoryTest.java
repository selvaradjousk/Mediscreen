package com.patient.unittest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.patient.model.Patient;
import com.patient.repository.PatientRepository;

@DisplayName("UNIT TESTS - REPOSITORY - PATIENT")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:datasource/createpatient.sql")
class PatientRepositoryTest {


	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	PatientRepository patientRepository;

	// *******************************************************************
	@DisplayName("@DataJpaTest & JPA components - "
			+ "GIVEN @DataJpaTest "
			+ "WHEN testing SpringData JPA repositories or JPA components"
			+ "THEN will set up a Spring application context")
	@Test
	void injectedComponentsAreNotNull() {
		
		assertNotNull(dataSource);
		assertNotNull(jdbcTemplate);
		assertNotNull(entityManager);
		assertNotNull(patientRepository);
	}
	
	// ******************************************************************

	// ******************************************************************
	// *******************LIST USERS ************************************
	// ******************************************************************
	@DisplayName("LIST OF USERS - "
			+ "GIVEN - Users in H2 Database "
			+ "WHEN request list of users SpringData JPA repositories"
			+ "THEN returns the number of user avalable in the H2 DB dataset")
	@Test
	public void should_find_all_Users() {
		
		Iterable<Patient> patients = patientRepository.findAll();
		
		assertThat(patients).hasSize(10);
	}
	

	// ******************************************************************

	



}
