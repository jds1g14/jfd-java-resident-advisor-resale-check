package com.jesse.jfdspringresidentadvisorresalecheck.utils.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jesse.jfdspringresidentadvisorresalecheck.utils.Constants.RA_DOMAIN;

public class InputValidator {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(InputValidator.class);


    /**
     *
     * @param url the resident advisor event url to be queried
     */
    public static void isValidResidentAdvisorUrl (String url) throws Exception {
        if (url.contains(RA_DOMAIN)) {
            LOG.info("A valid Resident Advisor URL has been provided.");
        } else {
            throw new Exception ("The url provided does not contain " + RA_DOMAIN + " is this a Resident Advisor URL");
        }
    }

    /**
     *
     * @param refreshPeriod - how frequently the page should be queried
     */
    public static void isValidRefreshPeriod (int refreshPeriod) throws Exception {

        if (refreshPeriod < 5) {
            throw new Exception("Refresh period is too short, must be at least 5 minutes (pls don't DDOS Resident Advisor)");
        } else {
            LOG.info("The refresh period of " + refreshPeriod + " minutes is valid");
        }
    }
}
