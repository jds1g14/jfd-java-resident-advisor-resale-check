package com.jesse.jfdspringresidentadvisorresalecheck;

import com.jesse.jfdspringresidentadvisorresalecheck.controller.MasterController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JfdSpringResidentAdvisorResaleCheckApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JfdSpringResidentAdvisorResaleCheckApplication.class, args);

		String url = args[0];
		String refreshPeriod = args[1];
		String ticketName = args[2];

		MasterController masterController = new MasterController();
		masterController.Controller(url, refreshPeriod, ticketName);

	}

}
