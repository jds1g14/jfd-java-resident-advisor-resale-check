package com.jesse.jfdspringresidentadvisorresalecheck.services;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;

public interface ResidentAdvisorResaleService {

    /**
     *
     * @param eventUrl - the url of the resident advisor event page
     * @return a HtmlWebpage object for the resident advisor event
     */
    HtmlPage getEventWebpage(final String eventUrl) throws IOException;

    /**
     *
     * @param htmlPage - the htmlwebpage object for the resident advisor event
     * @return a DomElement containing list of different ticket types for said event
     */
   String getTicketsObject(final HtmlPage htmlPage);

    /**
     *
     * @param ticketName - the name/type of the ticket that we are checking is available
     * @return a Boolean indicating if the ticket is available or not
     */
   boolean isTicketAvailable(final String ticketDOMString, final String ticketName, final String eventUrl) throws Exception;

}
