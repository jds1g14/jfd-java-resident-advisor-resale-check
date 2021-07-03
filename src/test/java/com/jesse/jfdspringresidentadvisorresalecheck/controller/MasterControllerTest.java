package com.jesse.jfdspringresidentadvisorresalecheck.controller;

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

import java.net.MalformedURLException;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MasterControllerTest {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(MasterController.class);

    /**
     * A valid url
     */
    @Value("${valid.url}")
    String mockValidUrl;

    @Value("${valid.ticket.name}")
    String validTicketName;

    @Value("999")
    int validRefreshPeriod;

    @Value("4")
    int invalidRefreshPeriod;

    @Value("22")
    String invalidMockValidUrl;

    @Value("https://jesse-the-best.co.uk")
    String nonRaUrl;


    @InjectMocks
    private MasterController masterController;

    //@Mock
    //private static InputValidator InputValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void isValidRefreshPeriodEventNameAndURLTrueTest() throws Exception {

        Integer response = masterController.Controller(mockValidUrl, validRefreshPeriod, validTicketName);

        Assertions.assertEquals(response, 1);

    }

    @Test
    public void isValidRefreshPeriodFalseTest() throws Exception {

        boolean exceptionThrown = false;
        String errorMessage = null;
        Integer response = 0;

        try {
            response = masterController.Controller(mockValidUrl, invalidRefreshPeriod, validTicketName);

        } catch (Exception e) {
            exceptionThrown = true;
            errorMessage= e.getMessage();
        }

        // TODO Validate logs and check if correct error msg being thrown
        Assertions.assertTrue(exceptionThrown);
        Assertions.assertEquals("Invalid Refresh period Error", errorMessage);
        Assertions.assertNotEquals(1, response);

    }

    @Test
    public void isvalidUrlFalseTest1() throws Exception {

        boolean exceptionThrown = false;
        String errorMessage = null;
        Integer response = 0;

        try {
            response = masterController.Controller(nonRaUrl, validRefreshPeriod, validTicketName);

        } catch (Exception e) {
            exceptionThrown = true;
            errorMessage= e.getMessage();
        }

        Assertions.assertTrue(exceptionThrown);
        Assertions.assertEquals("Invalid Resident Advisor Url Error", errorMessage);
        Assertions.assertNotEquals(1, response);

    }

    @Test
    public void isvalidUrlFalseTest2() throws Exception {

        boolean exceptionThrown = false;
        String errorMessage = null;
        Integer response = 0;

        try {
            response = masterController.Controller(invalidMockValidUrl, validRefreshPeriod, validTicketName);

        } catch (MalformedURLException e) {
            exceptionThrown = true;
            errorMessage= e.getMessage();
        }

        Assertions.assertTrue(exceptionThrown);
        Assertions.assertEquals("no protocol: 22", errorMessage);
        Assertions.assertNotEquals(1, response);

    }

}
