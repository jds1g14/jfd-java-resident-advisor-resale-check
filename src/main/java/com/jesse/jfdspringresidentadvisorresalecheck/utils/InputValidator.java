package com.jesse.jfdspringresidentadvisorresalecheck.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.net.URL;

@Component
public class InputValidator {

    private InputValidator() {
    }

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(InputValidator.class);


    /**
     *
     * @param url the resident advisor event url to be queried
     */
    public static void isValidResidentAdvisorUrl (final String url) throws Exception {
        //TODO Get domain name from app.properties and remove this logic

        String raDomainName = "ra.co";


        new URL(url);

        if (url.contains(raDomainName)) {
            LOG.info("The url provided is valid");
        } else {
            throw new Exception ("Invalid Resident Advisor Url Error");
        }

    }

    /**
     *
     * @param refreshPeriod - how frequently the page should be queried
     */
    public static void isValidRefreshPeriod (final int refreshPeriod) throws Exception {

        if (refreshPeriod < 5) {
            LOG.error("Refresh period is too short, must be at least 5 minutes (pls don't DDOS Resident Advisor)");
            throw new Exception("Invalid Refresh period Error");
        } else {
            LOG.info("The refresh period of " + refreshPeriod + " minutes is valid");
        }
    }
}
