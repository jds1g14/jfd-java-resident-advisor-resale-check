package com.jesse.jfdspringresidentadvisorresalecheck.services;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.awt.*;
import java.io.IOException;

public interface ResidentAdvisorResaleService {

    /**
     *
     * @return a HtmlWebpage object for the resident advisor event
     */
    HtmlPage getEventWebpage() throws IOException;

    /**
     *
     * @return a DomElement containing list of different ticket types for said event
     */
   void setTicketsObject();

    /**
     *
     * @return a Boolean indicating if the ticket is available or not
     */
   boolean isTicketAvailable() throws Exception;

}
