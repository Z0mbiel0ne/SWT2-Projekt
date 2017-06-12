package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.controller.Converter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * SQL Service
 *
 * @author ExaShox
 */
public class SQLService {

    private final static Converter CONVERTER = new Converter();

    /**
     * Konstruktor
     */
    public SQLService() {
    }

    /**
     * Legt einen neuen Kunden in der Datenbank an
     *
     * @param vorname
     * @param nachname
     * @param plz
     * @param stadt
     * @param strasse
     * @param hausnummer
     * @param kontonummer
     * @param blz
     * @param iban
     * @param bic
     */
    public void addKunde(String vorname, String nachname, int plz, String stadt, String strasse, int hausnummer, int kontonummer, int blz, int iban, int bic) {
        try (Connection conn = ConnectionManager.getConnection()) {
            CallableStatement stmt;

            String sqlString = "{CALL addKunde(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 

            stmt.setString(1, vorname); // Parameter setzen
            stmt.setString(2, nachname);
            stmt.setInt(3, plz);
            stmt.setString(4, stadt);
            stmt.setString(5, strasse);
            stmt.setInt(6, hausnummer);
            stmt.setInt(7, kontonummer);
            stmt.setInt(8, blz);
            stmt.setInt(9, iban);
            stmt.setInt(10, bic);

            stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteKunde(int id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            CallableStatement stmt;

            String sqlString = "{CALL deleteKunde(?)}";
            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 
            stmt.setInt(1, id);
            stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getKundenTable() {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString = "SELECT KundeID, CONCAT(Vorname, ' ', Nachname) as 'Name', CONCAT(Straße, ', ', Postleitzahl, ' ', Stadt) as 'Adresse', Guthaben from kunde";
            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            DefaultTableModel tableModel = CONVERTER.convertToDefaultTableModel(rs);

            stmt.close();
            conn.close();
            return tableModel;
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public DefaultTableModel getFahrstundeTable(int kundenID) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString
                    = "SELECT fs.FahrstundeID, fs.Datum, ku.Vorname as 'Schüler', pe.Vorname as 'Fahrlehrer', CONCAT(tp.Straße, ', ', tp.Postleitzahl, ' ', tp.Stadt) AS "
                    + "Adresse FROM kunde AS ku INNER JOIN fahrstunde AS fs "
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

            DefaultTableModel tableModel = CONVERTER.convertToDefaultTableModel(rs);

            stmt.close();
            conn.close();
            return tableModel;
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean checkPasswort(String user, String pass) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            String sqlString = "select passwort, personal.name from passwort join personal on passwort.personalID = personal.PersonalID where personal.name = ?";
            stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String test = rs.getString(1);

            stmt.close();
            conn.close();
            return pass.equals(test);
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Es konnte keine Verbindung hergestellt werden Sie befinden sich im Testmodus");
            return false;
        }
    }

    /**
     * Löscht in der Tabelle Fahrstunde den eintrag mit der ID
     *
     * @param id FahrstundenID
     */
    public void deleteFahrstunde(int id) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;
            String sqlString = "DELETE FROM fahrstunde WHERE FahrstundeID = ?";

            stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updated den Geldbetrag eines Kunden
     *
     * @param id KundeID
     * @param value Betrag
     */
    public void updateCredit(int id, int value) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            String sqlString = "UPDATE kunde AS ku "
                    + "SET ku.Guthaben = ? "
                    + "WHERE ku.KundeID = ? ";

            stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, value);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Liefert ALLE Treffpunkte in der Form ID|Straße
     *
     * @return Bsp: [0][0] : 1 [0][1] : Essen
     */
    public String[][] getTreffpunkte() {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString
                    = "SELECT * \n"
                    + "FROM treffpunkt;";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            String[][] stringArray = CONVERTER.convertTo2DStringArray(rs);

            stmt.close();
            conn.close();
            return stringArray;
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Liefert ALLE Fahrlehrer in der Form FahrlehrerID|Vorname
     *
     * @return Bsp: [0][0] : 1 [0][1] : Hans
     */
    public String[][] getFahrlehrer() {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString
                    = "SELECT fa.FahrlehrerID, CONCAT(pe.Vorname, ' ', pe.Name) \n"
                    + "FROM fahrlehrer AS fa\n"
                    + "INNER JOIN personal AS pe\n"
                    + "ON pe.PersonalID = fa.PersonalID;;";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            String[][] stringArray = CONVERTER.convertTo2DStringArray(rs);

            stmt.close();
            conn.close();
            return stringArray;
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Liefert ALLE Kunden in der Form ID|Vorname
     *
     * @return Bsp: [0][0] : 1 [0][1] : Stephan
     */
    public String[][] getKunden() {
        try (Connection conn = ConnectionManager.getConnection()) {
            ResultSet rs;
            PreparedStatement stmt;
            // select data
            String sqlString
                    = "SELECT *\n"
                    + "FROM kunde;";
            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            String[][] stringArray = CONVERTER.convertTo2DStringArray(rs);

            stmt.close();
            conn.close();
            return stringArray;
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Erstellt einen Fahrstunde in der Tabelle Fahrstunden
     *
     * @param datum Datum
     * @param fahrlehrerID FahrlehrerID aus Tabelle Fahrlehrer
     * @param kundeID KundenId aus Tabelle Kunden
     * @param rechnungID
     */
    public void insertFahrstunde(String datum, int treffpunktID, int fahrlehrerID, int kundeID, int rechnungID) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date utilDate = sdf1.parse(datum);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

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
            stmt.setInt(2, treffpunktID);
            stmt.setInt(3, fahrlehrerID);
            stmt.setInt(4, kundeID);
            stmt.setInt(5, rechnungID);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    /**
     * Get data from Database
     *
     * @param sql
     * @return ResultSet
     */
    public ResultSet get(String sql) {
        ResultSet rs;
        try (Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(sql);

            stmt.close();
            conn.close();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Set data from Database
     *
     * @param sql
     */
    public void set(String sql) {
        try (Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
