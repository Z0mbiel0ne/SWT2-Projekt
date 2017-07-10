package fhdo.swt2.udrive.model.services;

import fhdo.swt2.udrive.model.ConnectionManager;
import fhdo.swt2.udrive.model.services.objects.Fahrlehrer;
import fhdo.swt2.udrive.model.services.objects.Fahrschueler;
import fhdo.swt2.udrive.model.services.objects.Fahrstunde;
import fhdo.swt2.udrive.model.services.objects.Treffpunkt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ExaShox
 */
public class BuchungsServices {
    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Konstruktor
     */
    public BuchungsServices() {
        log.fine("Creating " + this.getClass().getName());
    }

    /**
     * Gimme Fahrstunde
     *
     * @param kundenID
     * @return
     */
    public ArrayList<Fahrstunde> getFahrstundeTable(int kundenID) {
        log.info("Get List of Fahrstunden for Kunden");
        
        ArrayList<Fahrstunde> fahrstundeList = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString
                    = "SELECT "
                    + "fs.FahrstundeID AS 'ID', "
                    + "fs.Datum AS 'Datum', "
                    + "ku.Vorname AS 'Schüler', "
                    + "pe.Vorname AS 'Fahrlehrer', "
                    + "tp.Straße AS 'Strasse', "
                    + "tp.Postleitzahl AS 'PLZ', "
                    + "tp.Stadt AS 'Stadt' "
                    + "FROM kunde AS ku INNER JOIN fahrstunde AS fs "
                    + "ON ku.KundeID = fs.KundeID "
                    + "INNER JOIN fahrlehrer AS fl "
                    + "ON fl.FahrlehrerID = fs.FahrlehrerID "
                    + "INNER JOIN personal AS pe "
                    + "ON pe.PersonalID = fl.PersonalID "
                    + "INNER JOIN treffpunkt AS tp "
                    + "ON tp.TreffpunktID = fs.TreffpunktID "
                    + "WHERE ku.KundeID = " + kundenID;

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            while (rs.next()) {
                Fahrstunde fahrstunde = new Fahrstunde();

                fahrstunde.setId(rs.getInt("ID"));
                fahrstunde.setKundeName(rs.getString("Schüler"));
                fahrstunde.setFahrlehrerName(rs.getString("Fahrlehrer"));
                fahrstunde.setStrasse(rs.getString("Strasse"));
                fahrstunde.setPlz(rs.getString("PLZ"));
                fahrstunde.setStadt(rs.getString("Stadt"));
                fahrstunde.setDatum(rs.getDate("Datum"));

                fahrstundeList.add(fahrstunde);
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        } finally {
            return fahrstundeList;
        }
    }

    /**
     * Löscht in der Tabelle Fahrstunde den eintrag mit der ID
     *
     * @param fahrstunde
     */
    public void deleteFahrstunde(Fahrstunde fahrstunde) {
        log.fine("Try to delete Fahrstunde");
        
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;
            String sqlString = "DELETE FROM fahrstunde WHERE FahrstundeID = ?";

            stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, fahrstunde.getId());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
            log.info("Fahrstunde deleted");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Erstellt einen Fahrstunde in der Tabelle Fahrstunden
     *
     * @param datum
     * @param treffpunkt
     * @param fahrlehrer
     * @param kunde
     * @param rechnungID
     */
    public void insertFahrstunde(Date datum, Treffpunkt treffpunkt,
            Fahrlehrer fahrlehrer, Fahrschueler kunde, int rechnungID) {
        
        log.fine("Try to insert Fahrstunde");
        
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            java.sql.Date sqlDate = new java.sql.Date(datum.getTime());

            String sqlString
                    = "INSERT INTO fahrstunde ("
                    + "Datum,"
                    + "TreffpunktID,"
                    + "FahrlehrerID,"
                    + "KundeID,"
                    + "RechnungID ) "
                    + "VALUES (?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sqlString);
            stmt.setDate(1, sqlDate);
            stmt.setInt(2, treffpunkt.getId());
            stmt.setInt(3, fahrlehrer.getFahrlehrerID());
            stmt.setInt(4, kunde.getId());
            stmt.setInt(5, rechnungID);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
            log.info("Fahrstunde inserted");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Liefert ALLE Treffpunkte in der Form ID|Straße
     *
     * @return Bsp: [0][0] : 1 [0][1] : Essen
     */
    public ArrayList<Treffpunkt> getTreffpunkte() {
        log.info("Get list of Treffpunkt");
        
        ArrayList<Treffpunkt> treffpunktList = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString
                    = "SELECT * "
                    + "FROM treffpunkt;";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            while (rs.next()) {
                Treffpunkt treffpunkt = new Treffpunkt();

                treffpunkt.setId(rs.getInt("TreffpunktID"));
                treffpunkt.setStrasse(rs.getString("Straße"));
                treffpunkt.setPlz(rs.getString("Postleitzahl"));
                treffpunkt.setStadt(rs.getString("Stadt"));

                treffpunktList.add(treffpunkt);
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        } finally {
            return treffpunktList;
        }
    }
}
