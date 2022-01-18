package com.jesse.jfdspringresidentadvisorresalecheck.services;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jesse.jfdspringresidentadvisorresalecheck.utils.DisplayTray;
import com.jesse.jfdspringresidentadvisorresalecheck.utils.validators.WebpageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.AWTException;
import java.io.IOException;

public class ResidentAdvisorResaleServiceImpl {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResidentAdvisorResaleServiceImpl.class);

    private final String eventUrl;
    private HtmlPage htmlPage;
    private final String ticketName;
    private String ticketDOM;
    private final DisplayTray displayTray;

    public ResidentAdvisorResaleServiceImpl(String eventUrl, String ticketName) throws AWTException {
        this.eventUrl = eventUrl;
        this.ticketName = ticketName;
        this.displayTray = new DisplayTray();
    }

    /**
     * The Controller method
     */
    public void ServiceController() throws Exception {
        HtmlPage htmlPage = getEventWebpage();
        boolean isValidResidentAdvisorPage = WebpageValidator.isValidResidentAdvisorEventPage(htmlPage);

        if (isValidResidentAdvisorPage) {
            setTicketsObject();
            isTicketAvailable();
        } else {
            this.displayTray.displayError();
            throw new Exception();
        }
    }

    /**
     * @return a HtmlPage object for the resident advisor event
     */
    public HtmlPage getEventWebpage() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

       try {
           this.htmlPage = webClient.getPage(this.eventUrl);
           LOG.info("Successfully retrieved html from: " + eventUrl);
       } catch (IOException e) {
           LOG.error("Error occurred when accessing url: " + this.eventUrl + "Error accessing event page");
           LOG.info("Trying to access RA Link again...");
       } finally {
           webClient.close();
       }
       return this.htmlPage;
    }


    /**
     * set a list of different ticket types for said event
     */
    public void setTicketsObject() {
        // Retrieve the block of html related to the ticket objects
        this.ticketDOM = this.htmlPage.getElementById("tickets").asText();
        LOG.info("Successfully retrieved ticket object from html page");
    }

    /**
     *
     * @return a boolean flag indicating if the ticket is available for purchase
     */
    public boolean isTicketAvailable() throws Exception {

        // Presence of unchecked + ticketname in html indicates the ticket is available/not sold out
        String ticketStringToValidate = "unchecked " + this.ticketName;

        boolean ticketAvailable = false;

        if (this.ticketDOM.contains(this.ticketName)) {
            if (this.ticketDOM.contains(ticketStringToValidate)) {
                LOG.info(this.ticketName + " tickets/s are now available");
                this.displayTray.displayTicketAvailable(this.ticketName);
                ticketAvailable = true;
            } else {
                LOG.info('"' + this.ticketName + '"' + " tickets are not currently available !");
            }
        } else {
            LOG.error('"' + this.ticketName + '"' + " is not in the list of tickets for the event. Please check" +
                    " the ticket type corresponds to one of those on the event page.");
            this.displayTray.displayError();
            Thread.sleep(5000);
            System.exit(0);
        }
        return ticketAvailable;
    }

}
