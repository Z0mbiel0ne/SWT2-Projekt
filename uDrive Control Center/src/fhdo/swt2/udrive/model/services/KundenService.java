package fhdo.swt2.udrive.model.services;

import fhdo.swt2.udrive.model.ConnectionManager;
import fhdo.swt2.udrive.model.services.objects.Fahrschueler;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Customer Service for Custom Data
 * @author ExaShox
 */
public class KundenService {

    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Konstruktor
     */
    public KundenService() {
        log.fine("Creating " + this.getClass().getName());
    }

    /**
     * Legt einen neuen Kunden in der Datenbank an
     *
     * @param kunde Kunde
     */
    public void addKunde(Fahrschueler kunde) {
        log.fine("Try to add Kunde Name:" + kunde.getVorname() + " ID: " + kunde.getId() );

        try (Connection conn = ConnectionManager.getConnection()) {
            CallableStatement stmt;

            String sqlString = "{CALL addKunde(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 

            stmt.setString(1, kunde.getVorname()); // Parameter setzen
            stmt.setString(2, kunde.getNachname());
            stmt.setString(3, kunde.getPlz());
            stmt.setString(4, kunde.getStadt());
            stmt.setString(5, kunde.getStrasse());
            stmt.setString(6, kunde.getHausnummer());
            stmt.setString(7, kunde.getKontonummer());
            stmt.setString(8, kunde.getBlz());
            stmt.setString(9, kunde.getIban());
            stmt.setString(10, kunde.getBic());

            stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            stmt.close();
            conn.close();

            log.info("Kunde added");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Löscht einen Kunden
     *
     * @param kunde Kunde
     * @throws SQLException SQLException
     */
    public void deleteKunde(Fahrschueler kunde) throws SQLException {
        log.fine("Try to delete Kunde Name: " + kunde.getVorname() + " ID: " + kunde.getId());

        try (Connection conn = ConnectionManager.getConnection()) {
            CallableStatement stmt;

            String sqlString = "{CALL deleteKunde(?)}";
            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 
            stmt.setInt(1, kunde.getId());
            stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            stmt.close();
            conn.close();

            log.info("Kunde deleted Kunde: " + kunde.getVorname() + " ID: " + kunde.getId());
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Liefert ALLE Kunden
     *
     * @return ArrayList
     */
    public ArrayList<Fahrschueler> getKunden() {
        log.info("Get table of Kunde");

        ArrayList<Fahrschueler> kundeList = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString = "SELECT * FROM kunde";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            while (rs.next()) {
                Fahrschueler fahrschueler = new Fahrschueler();

                fahrschueler.setId(rs.getInt("KundeID"));
                fahrschueler.setHausnummer(rs.getString("Hausnummer"));
                fahrschueler.setGuthaben(rs.getInt("Guthaben"));
                fahrschueler.setVorname(rs.getString("Vorname"));
                fahrschueler.setNachname(rs.getString("Nachname"));
                fahrschueler.setPlz(rs.getString("Postleitzahl"));
                fahrschueler.setStadt(rs.getString("Stadt"));
                fahrschueler.setStrasse(rs.getString("Straße"));

                kundeList.add(fahrschueler);
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        } finally {
            return kundeList;
        }
    }

    /**
     * Updatet den Geldbetrag eines Kunden
     *
     * @param kunde KudnenID
     */
    public void updateCredit(Fahrschueler kunde) {
        log.fine("Try to update Kunden Credits Name: " + kunde.getVorname() + " ID: " + kunde.getId());
        
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            String sqlString
                    = "UPDATE kunde AS ku "
                    + "SET ku.Guthaben = ku.Guthaben + ? "
                    + "WHERE ku.KundeID = ? ";

            stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, kunde.getGuthaben());
            stmt.setInt(2, kunde.getId());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
            log.info("Credtis updated");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }
}
