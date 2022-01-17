package com.jesse.jfdspringresidentadvisorresalecheck.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class DisplayTray {

    /**
     *  The log running consistently
     */
    private static final Logger LOG = LoggerFactory.getLogger(DisplayTray.class);

    private final TrayIcon trayIcon;


    public DisplayTray() throws AWTException {
        System.setProperty("java.awt.headless", "false");
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        this.trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        this.trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        this.trayIcon.setToolTip("System tray icon demo");

        tray.add(this.trayIcon);

    }

    /**
     * Display a java tray icon indicating that the ticket is available
     */
    public void displayTicketAvailable(String ticketName) {
        this.trayIcon.displayMessage("Resident Advisor Checker", ticketName +
                " tickets are available on ra.co", TrayIcon.MessageType.INFO);
    }

    /**
     * Display a java tray icon indicating that an error has occured
     */
    public void displayError() {
        this.trayIcon.displayMessage("Resident Advisor Checker", "An error has occurred, please view the console logs", TrayIcon.MessageType.ERROR);
    }
}
