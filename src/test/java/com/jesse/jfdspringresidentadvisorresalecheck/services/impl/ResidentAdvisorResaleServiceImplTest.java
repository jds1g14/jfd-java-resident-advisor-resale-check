package com.jesse.jfdspringresidentadvisorresalecheck.services.impl;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jesse.jfdspringresidentadvisorresalecheck.services.ResidentAdvisorResaleServiceImpl;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;




@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ResidentAdvisorResaleServiceImplTest {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResidentAdvisorResaleServiceImpl.class);

    /**
     * A valid url
     */
    @Value("${valid.url}")
    String mockValidUrl;

    @Value("${valid.event.name}")
    String validEventName;

    @Value("${valid.ticket.string}")
    String mockValidTicketObjectString;

    @Value("${invalid.ticket.type.error}")
    String INVALID_TICKET_TYPE_ERROR;

    @InjectMocks
    private ResidentAdvisorResaleServiceImpl residentAdvisorResaleService;

    @Mock
    WebClient webClient;

    @Mock
    private HtmlPage mockHtmlPage;

    private HtmlPage htmlPage;

    private DomElement domElement;

    private String ticketObjectString;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void isTicketAvailableTrueTest() throws Exception {


        Boolean result = residentAdvisorResaleService.isTicketAvailable(mockValidTicketObjectString, "Car Parking Pass (Valid for 1 car)", mockValidUrl);
        Assertions.assertTrue(result);
    }

    @Test
    public void isTicketAvailableFalseTest() throws Exception {


        Boolean result = residentAdvisorResaleService.isTicketAvailable(mockValidTicketObjectString, "General Admission Weekend (Inc. Camping)", mockValidUrl);
        Assertions.assertFalse(result);
    }

    @Test()
    public void isTicketAvailableNoMatchTest() {

        boolean exceptionThrown = false;
        String errorMessage = null;

        try {
            Boolean result = residentAdvisorResaleService.isTicketAvailable(mockValidTicketObjectString, "Would you rather man or mouse ?", mockValidUrl);

        } catch (Exception e) {
            exceptionThrown = true;
            errorMessage= e.getMessage();
        }

        Assertions.assertTrue(exceptionThrown);
        Assertions.assertEquals(errorMessage, INVALID_TICKET_TYPE_ERROR);
    }

}
