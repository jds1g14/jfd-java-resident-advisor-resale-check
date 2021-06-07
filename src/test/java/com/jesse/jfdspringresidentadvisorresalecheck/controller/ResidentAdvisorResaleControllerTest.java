package com.jesse.jfdspringresidentadvisorresalecheck.controller;

import com.jesse.jfdspringresidentadvisorresalecheck.services.ResidentAdvisorResaleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ResidentAdvisorResaleControllerTest<string> {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(MasterController.class);

    /**
     * A valid url
     */
    @Value("${valid.url}")
    String mockValidUrl;

    @Value("https://www.google.co.uk")
    String nonRAUrl;

    @Value("${valid.ticket.name}")
    String validTicketName;

    @Value("${invalid.ticket.string}")
    String invalidTicketString;

    @InjectMocks
    private ResidentAdvisorResaleController residentAdvisorResaleController;

    @Mock
    private ResidentAdvisorResaleService residentAdvisorResaleService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void isValidResidentAdvisorPageFalseTest() {

        boolean exceptionThrown = false;
        String errorMessage = null;

        try {
            residentAdvisorResaleController.Controller(nonRAUrl, validTicketName);
        } catch (Exception e) {
            exceptionThrown = true;
            errorMessage= e.getMessage();
        }

        Assertions.assertTrue(exceptionThrown);
        Assertions.assertEquals("Invalid Resident Advisor Webpage Error", errorMessage);
    }

}
