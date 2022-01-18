package com.jesse.jfdspringresidentadvisorresalecheck;

import com.jesse.jfdspringresidentadvisorresalecheck.services.ResidentAdvisorResaleServiceImpl;
import com.jesse.jfdspringresidentadvisorresalecheck.utils.DisplayTray;
import com.jesse.jfdspringresidentadvisorresalecheck.utils.validators.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class JfdJavaResidentAdvisorResaleCheckApplication {

	/**
	 *  The log running consistently
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JfdJavaResidentAdvisorResaleCheckApplication.class);

	public static void main(String[] args) throws Exception {

		SpringApplication.run(JfdJavaResidentAdvisorResaleCheckApplication.class, args);

		// Retrieve url and refresh period
		String url = args[0];
		int refreshPeriod = Integer.parseInt(args[1]);

		// Retrieve ticket names to be searched for
		String[] ticketNames = new String[args.length-2];
		for (int count = 2 ; count < args.length; count++) {
			ticketNames[count-2] = args[count];
		}

		InputValidator.isValidResidentAdvisorUrl(url);
		InputValidator.isValidRefreshPeriod(refreshPeriod);

		ServiceCaller(url, refreshPeriod, ticketNames);
	}

	private static void ServiceCaller (String url,int refreshPeriod, String[] ticketNames) throws InterruptedException {

		// Search for tickets for 9 hours before closing
		int loopInstancesIn9Hours = (9 * 60) / refreshPeriod;
		int count = 1 ;

		while (count <= loopInstancesIn9Hours ) {
			for (String ticketName : ticketNames ) {
				try {
					ResidentAdvisorResaleServiceImpl residentAdvisorResaleService = new ResidentAdvisorResaleServiceImpl(url, ticketName);
					residentAdvisorResaleService.ServiceController();
				} catch (AWTException e) {
					LOG.error("An error occurred whilst generating the displayTray/notification");
				} catch (Exception e) {
					LOG.error("An error occurred within the service");
				} finally {
					Thread.sleep((long) refreshPeriod * 60000);
				}
			}
			count++;
		}

	}

}
