package com.jesse.jfdspringresidentadvisorresalecheck.services;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.jesse.jfdspringresidentadvisorresalecheck.utils.ResidentAdvisorValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResidentAdvisorResaleServiceImpl implements ResidentAdvisorResaleService {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResidentAdvisorResaleServiceImpl.class);

    /**
     * @param eventUrl      - the url of the resident advisor event page
     * @return a HtmlPage object for the resident advisor event
     */
    @Override
    public HtmlPage getEventWebpage(String eventUrl) throws IOException {

        //TODO create a config class aka appconfig
        HtmlPage webpage;
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        //Disable css and jscript else we get hella errors
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        webpage = webClient.getPage(eventUrl);
        webClient.close();

        return webpage;
    }


    /**
     * @param htmlPage - the htmlwebpage object for the resident advisor event
     * @return a list of different ticket types for said event
     */
    @Override
    public String getTicketsObject(HtmlPage htmlPage) {

        //TODO Validate is RA page

        // Go in to hear if the result of is ra page true

        // Retrieve the block of html related to the ticket object
        DomElement ticketDOM = htmlPage.getElementById("tickets");
        String ticketDOMString = ticketDOM.asText();
        // Have to parse it using asText() instead of .tostring or toxml else it returns some garbage object

        //Run validator job to check if it is a resident advisor ticket object

        return ticketDOMString;
    }

    /**
     *
     * @param ticketDOMString - the string consisting of a list of tickets available for the event
     * @param ticketName - the name/type of the ticket that we are checking is available
     * @return a boolean flag indicating if the ticket is available for purchase
     */
    @Override
    public boolean isTicketAvailable(String ticketDOMString, String ticketName, String eventUrl) throws Exception {

        // TODO expand later to allow for multiple tickets to be queried

        //TODO expand this method to add some alerting ? Would be hella cool if a text could be sent or a windows/mac os alert was sent

        String ticketStringToValidate = "unchecked " + ticketName;
        boolean ticketAvailable = false;

        if (ticketDOMString.contains(ticketName)) {
            if (ticketDOMString.contains(ticketStringToValidate)) {
                LOG.info('"' + ticketName + '"' +  " tickets are available, visit the event page to purchase !");
                ticketAvailable = true;
            } else {
                LOG.info('"' + ticketName + '"' + " tickets are not currently available !");
            }
        } else {
            LOG.error('"' + ticketName + '"' + " is not in the list of tickets for the event at " + eventUrl +  ". Please check" +
                     " the ticket type corresponds to one of those on the event page.");
            throw new Exception("Invalid Ticket Type Error");
        }
        return ticketAvailable;
    }

}
