package com.jesse.jfdspringresidentadvisorresalecheck.controller;

import com.jesse.jfdspringresidentadvisorresalecheck.utils.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class MasterController {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(MasterController.class);

    public Integer Controller (String url, int refreshPeriod, ArrayList<String> ticketNames) throws Exception {

        InputValidator.isValidResidentAdvisorUrl(url);
        InputValidator.isValidRefreshPeriod(refreshPeriod);

        ResidentAdvisorResaleController residentAdvisorResaleController = new ResidentAdvisorResaleController();

        //1st if condition added for testing purposes. u think I'm waiting 5 mins per unit-test, what's all that about ?
        if (refreshPeriod == 999) {
            for (String ticketName : ticketNames ) {
                residentAdvisorResaleController.Controller(url, ticketName);
            }

        } else {
            int loopInstancesIn9Hours = (9 * 60) / refreshPeriod;
            int count = 0 ;

            // Break out of loop after 9 hours
            while (count < loopInstancesIn9Hours ) {

                for (String ticketName : ticketNames ) {
                    residentAdvisorResaleController.Controller(url, ticketName);
                }
                Thread.sleep((long) refreshPeriod * 60000);
                count++;

            }
        }

        return 1;
    }

}
