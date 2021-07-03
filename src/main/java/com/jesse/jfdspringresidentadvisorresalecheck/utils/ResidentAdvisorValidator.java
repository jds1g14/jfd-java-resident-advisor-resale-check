package com.jesse.jfdspringresidentadvisorresalecheck.utils;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ResidentAdvisorValidator {

    private ResidentAdvisorValidator() {
    }

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResidentAdvisorValidator.class);


    /**
     *
     * @param htmlPage the HtmlPage object of the webpage being queried
     * @return boolean indicating if the webpage is a resident advisor event
     */
    public static boolean isValidResidentAdvisorEventPage (final HtmlPage htmlPage) {

        String htmlPageString = htmlPage.asText();

        boolean containsResAdvisor = htmlPageString.contains("Resident Advisor");
        boolean containsRATickets = htmlPageString.contains("RA Tickets");
        boolean containsBuyTickets = htmlPageString.contains("Buy tickets");

        if ((containsResAdvisor == containsRATickets == containsBuyTickets) && (containsResAdvisor)) {
            LOG.info("The url provided corresponds to a valid Resident Advisor Webpage");
            return true;
        } else {
            LOG.info("The url provided does not correspond to a valid Resident Advisor Event Webpage");
            return false;
        }

    }
}
