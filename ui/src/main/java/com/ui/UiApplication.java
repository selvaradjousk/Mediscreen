package com.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The Class UiApplication.
 */
@SpringBootApplication
@EnableFeignClients({"com.ui"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class UiApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

}
