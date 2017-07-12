/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.controller;

import fhdo.swt2.udrive.view.Fenster;
import fhdo.swt2.udrive.view.Login;
import fhdo.swt2.udrive.model.ConnectionManager;
import fhdo.swt2.udrive.model.LoginFactory;
import fhdo.swt2.udrive.model.FensterFactory;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Hauptklasse mit Mainmethode
 * @author Marcel
 */
public class UDriveControlCenter {
    
    private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Login login;
    private static LoginFactory loginfactory;
    private static Fenster fenster;
    private static FensterFactory fensterfactory;

    /**
     * Hauptprogramm
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        log.info("Starting!");
        
        ConnectionManager manager = new ConnectionManager();
        if (manager.checkConnection()) {
            loginfactory = new LoginFactory();
            login = loginfactory.newInstance();
        } else {
            fensterfactory = new FensterFactory();
            fenster = fensterfactory.newInstance();
        }
    }

}
