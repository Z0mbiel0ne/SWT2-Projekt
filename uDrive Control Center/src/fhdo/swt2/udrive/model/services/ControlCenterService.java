package fhdo.swt2.udrive.model.services;

import fhdo.swt2.udrive.model.ConnectionManager;
import fhdo.swt2.udrive.model.services.objects.Fahrlehrer;
import fhdo.swt2.udrive.model.services.objects.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SQL Service for Programm Tasks
 * @author ExaShox
 */
public class ControlCenterService {

    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Konstruktor
     */
    public ControlCenterService() {
        log.fine("Creating " + this.getClass().getName());
    }
    
    /**
     * Check the login Data of a User on Database
     * @param user User
     * @return boolean
     */
    public boolean checkPasswort(User user) {
        log.fine("Checking login data");

        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            String sqlString = "select passwort, personal.name from passwort join personal on passwort.personalID = personal.PersonalID where personal.name = ?";
            stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String test = rs.getString(1);

            stmt.close();
            conn.close();

            return user.getPassword().equals(test);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Liefert ALLE Fahrlehrer in der Form FahrlehrerID|Vorname
     *
     * @return Bsp: ArrayList [0][0] : 1 [0][1] : Hans
     */
    public ArrayList<Fahrlehrer> getFahrlehrer() {
        log.info("Get list of Fahrlehrer");
        
        ArrayList<Fahrlehrer> fahrlehrerList = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString
                    = "SELECT "
                    + "fa.FahrlehrerID AS FahrlehrerID, "
                    + "pe.PersonalID AS PersonalID, "
                    + "pe.Vorname AS Vorname, "
                    + "pe.Name AS Name, "
                    + "pe.Postleitzahl AS Postleitzahl, "
                    + "pe.Stadt AS Stadt, "
                    + "pe.Straße AS Straße, "
                    + "pe.Hausnummer AS Hausnummer "
                    + "FROM fahrlehrer AS fa "
                    + "INNER JOIN personal AS pe "
                    + "ON pe.PersonalID = fa.PersonalID;";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            while (rs.next()) {
                Fahrlehrer fahrlehrer = new Fahrlehrer();

                fahrlehrer.setFahrlehrerID(rs.getInt("FahrlehrerID"));
                fahrlehrer.setPersonalID(rs.getInt("PersonalID"));
                fahrlehrer.setVorname(rs.getString("Vorname"));
                fahrlehrer.setName(rs.getString("Name"));
                fahrlehrer.setPlz(rs.getString("Postleitzahl"));
                fahrlehrer.setStadt(rs.getString("Stadt"));
                fahrlehrer.setStrasse(rs.getString("Straße"));
                fahrlehrer.setHausnummer(rs.getString("Hausnummer"));

                fahrlehrerList.add(fahrlehrer);
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        } finally {
            return fahrlehrerList;
        }
    }
}
