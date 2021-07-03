package com.jesse.jfdspringresidentadvisorresalecheck.controller;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jesse.jfdspringresidentadvisorresalecheck.services.ResidentAdvisorResaleService;
import com.jesse.jfdspringresidentadvisorresalecheck.services.ResidentAdvisorResaleServiceImpl;
import com.jesse.jfdspringresidentadvisorresalecheck.utils.ResidentAdvisorValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ResidentAdvisorResaleController {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResidentAdvisorResaleController.class);


    public void Controller (String url, String ticketName) throws Exception {
        ResidentAdvisorResaleService residentAdvisorResaleService = new ResidentAdvisorResaleServiceImpl();
        try {
            HtmlPage htmlPage = residentAdvisorResaleService.getEventWebpage(url);
            boolean isValidResidentAdvisorPage = ResidentAdvisorValidator.isValidResidentAdvisorEventPage(htmlPage);
            if (isValidResidentAdvisorPage) {
                String ticketObject = residentAdvisorResaleService.getTicketsObject(htmlPage);
                residentAdvisorResaleService.isTicketAvailable(ticketObject, ticketName, url);

            } else {
                throw new Exception("Invalid Resident Advisor Webpage Error");
            }

        } catch (IOException ioException) {
            throw new IOException("There was an error in connecting to the url: " + url);
        }

    }
}
