package com.jesse.jfdspringresidentadvisorresalecheck.services;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.jesse.jfdspringresidentadvisorresalecheck.utils.ResidentAdvisorValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
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

        // Retrieve the block of html related to the ticket object
        DomElement ticketDOM = htmlPage.getElementById("tickets");

        // Have to parse it using asText() instead of .tostring or toxml else it returns some garbage object
        return ticketDOM.asText();
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

        String ticketStringToValidate = "unchecked " + ticketName;
        boolean ticketAvailable = false;

        if (ticketDOMString.contains(ticketName)) {
            if (ticketDOMString.contains(ticketStringToValidate)) {
                LOG.info('"' + ticketName + '"' +  " tickets are available, visit the event page to purchase !");
                displayNotification(ticketName);
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

    /**
     * @param ticketName - the name/type of the ticket that we are checking is available
     */
    public void displayNotification(String ticketName) throws AWTException {

        System.setProperty("java.awt.headless", "false");
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Resident Advisor Checker", ticketName + " tickets are available on ra.co", TrayIcon.MessageType.INFO);

    }

}
