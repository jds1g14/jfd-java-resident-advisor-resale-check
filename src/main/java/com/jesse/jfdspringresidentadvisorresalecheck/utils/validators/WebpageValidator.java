package com.jesse.jfdspringresidentadvisorresalecheck.utils.validators;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jesse.jfdspringresidentadvisorresalecheck.utils.Constants.RA;

public class WebpageValidator {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(WebpageValidator.class);

    /**
     *
     * @param htmlPage the HtmlPage object of the webpage being queried
     * @return boolean indicating if the webpage is a resident advisor event
     */
    public static boolean isValidResidentAdvisorEventPage (HtmlPage htmlPage) {

        final String htmlPageString = htmlPage.asText();
        final boolean containsResAdvisor = htmlPageString.contains(RA);
        final boolean containsRATickets = htmlPageString.contains("RA Tickets");
        final boolean containsBuyTickets = htmlPageString.contains("Buy tickets");

        if ((containsResAdvisor == containsRATickets) && (containsResAdvisor == containsBuyTickets) && containsResAdvisor) {
            LOG.info("The url provided corresponds to a valid Resident Advisor Webpage");
            return true;
        } else {
            LOG.error("The url provided does not correspond to a valid Resident Advisor Event Webpage");
            return false;
        }

    }
}
