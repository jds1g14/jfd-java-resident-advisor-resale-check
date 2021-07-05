package com.jesse.jfdspringresidentadvisorresalecheck;

import com.jesse.jfdspringresidentadvisorresalecheck.controller.MasterController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class JfdSpringResidentAdvisorResaleCheckApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JfdSpringResidentAdvisorResaleCheckApplication.class, args);

		int lengthOfArgs = args.length;
		String url = args[0];
		int refreshPeriod = Integer.parseInt(args[1]);
		ArrayList<String> ticketNames = new ArrayList<>();

		for (int count = 2 ; count < lengthOfArgs; count++) {
			ticketNames.add(args[count]);
		}

		MasterController masterController = new MasterController();
		masterController.Controller(url, refreshPeriod, ticketNames);

	}

}
