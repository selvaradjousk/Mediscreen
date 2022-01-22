package com.patientHistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * The Class PatientHistoryApplication.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PatientHistoryApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PatientHistoryApplication.class, args);
	}

}
