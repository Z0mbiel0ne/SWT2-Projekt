package udrive.control.center;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * SQL Service
 *
 * @author ExaShox
 */
public class SQLService {

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
        Connection conn = ConnectionManager.getConnection();
        CallableStatement stmt;
        try {

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

            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            stmt.close();

        } catch (SQLException ex) {
            //TODO Logger
            //Logger.getLogger(Bonusaufgabe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteKunde(int id) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        CallableStatement stmt;
        try {
            String sqlString = "{CALL deleteKunde(?)}";
            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen
            stmt.close();
        } catch (SQLException ex) {
            //TODO Logger
            //Logger.getLogger(Bonusaufgabe.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
    }

    /**
     * Liefert alle vorhandenen Bezirke und gibt diese aus
     */
    public void getBezirke() {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt;
        try {
            String sqlString = "select lieferbezirk.plz from lieferbezirk";
            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            System.out.println("Vorhandene Bezirke:");

            while (rs.next()) { //Loop über Ergebnis
                System.out.println(rs.getInt("plz")); // Postleitzahlen ausgeben
            }

            System.out.println("");
        } catch (SQLException ex) {
            //Logger.getLogger(Bonusaufgabe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TableModel getKundenTable() {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt;
        DefaultTableModel tab = new DefaultTableModel();
        try {
            // select data
            String sqlString = "SELECT KundeID, CONCAT(Vorname, ' ', Nachname) as 'Name', CONCAT(Straße, ', ', Postleitzahl, ' ', Stadt) as 'Adresse', Guthaben from kunde";
            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

            tab = new DefaultTableModel(data, columnNames);

        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tab;
    }

    public TableModel getFahrstundeTable(int kundenID) {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt;
        DefaultTableModel tab = new DefaultTableModel();
        try {
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
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

            tab = new DefaultTableModel(data, columnNames);

        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tab;
    }

    public boolean checkPasswort(String user, String pass) {
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt;

            String sqlString = "select passwort, personal.name from passwort join personal on passwort.personalID = personal.PersonalID where personal.name = ?";
            stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String test = rs.getString(1);
            return pass.equals(test);
        } catch (SQLException ex) {
            System.out.println("Es konnte keine Verbindung hergestellt werden sie befinden sich im Testmodus");
            return true;
        }
    }

    /**
     * Löscht in der Tabelle Fahrstunde den eintrag mit der ID
     *
     * @param id FahrstundenID
     */
    public void deleteFahrstunde(int id) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        CallableStatement stmt;
        try {
            String sqlString
                    = "DELETE FROM fahrstunde"
                    + "WHERE id = ?";

            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 
            stmt.setInt(1, id);
            stmt.executeQuery(); // Query absetzen und ResultSet zurückholen
            stmt.close();
        } catch (SQLException ex) {
            //TODO Logger
            //Logger.getLogger(Bonusaufgabe.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
    }

    /**
     * Updated den Geldbetrag eines Kunden
     *
     * @param id KundeID
     * @param value Betrag
     */
    public void updateCredit(int id, int value) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        CallableStatement stmt;
        try {
            String sqlString
                    = "UPDATE kunden AS ku"
                    + "SET ku.Guthaben = ?"
                    + "WHERE ku.KundeID = ?";

            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 
            stmt.setInt(1, value);
            stmt.setInt(2, id);
            stmt.executeQuery(); // Query absetzen und ResultSet zurückholen
            stmt.close();
        } catch (SQLException ex) {
            //TODO Logger
            //Logger.getLogger(Bonusaufgabe.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
    }

    /**
     * Liefert ALLE Treffpunkte in der Form ID|Straße
     *
     * @return Bsp: [0][0] : 1 [0][1] : Essen
     */
    public String[][] getTreffpunkte() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt;
        String[][] resultArray = new String[0][0];
        try {
            // select data
            String sqlString
                    = "SELECT * \n"
                    + "FROM treffpunkt;";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<String[]> data = new Vector<String[]>();
            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getString(columnIndex));
                }
                data.add(vector.toArray(new String[vector.size()]));
            }

            resultArray = data.toArray(new String[data.size()][]);
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultArray;
    }

    /**
     * Liefert ALLE Fahrlehrer in der Form FahrlehrerID|Vorname
     *
     * @return Bsp: [0][0] : 1 [0][1] : Hans
     */
    public String[][] getFahrlehrer() {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt;
        String[][] resultArray = new String[0][0];
        try {
            // select data
            String sqlString
                    = "SELECT fa.FahrlehrerID, CONCAT(pe.Vorname, ' ', pe.Name) \n"
                    + "FROM fahrlehrer AS fa\n"
                    + "INNER JOIN personal AS pe\n"
                    + "ON pe.PersonalID = fa.PersonalID;;";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<String[]> data = new Vector<String[]>();
            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getString(columnIndex));
                }
                data.add(vector.toArray(new String[vector.size()]));
            }

            resultArray = data.toArray(new String[data.size()][]);
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultArray;
    }

    /**
     * Liefert ALLE Kunden in der Form ID|Vorname
     *
     * @return Bsp: [0][0] : 1 [0][1] : Stephan
     */
    public String[][] getKunden() {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt;
        String[][] resultArray = new String[0][0];
        try {
            // select data
            String sqlString
                    = "SELECT *\n"
                    + "FROM kunde;";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<String[]> data = new Vector<String[]>();
            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getString(columnIndex));
                }
                data.add(vector.toArray(new String[vector.size()]));
            }

            resultArray = data.toArray(new String[data.size()][]);
        } catch (SQLException ex) {
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultArray;
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

        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt;

            String sqlString
                    = "INSERT INTO fahrstunde (\n"
                    + "Datum,\n"
                    + "TreffpunktID,\n"
                    + "FahrlehrerID,\n"
                    + "KundeID,\n"
                    + "RechnungID\n"
                    + ") VALUES (\n"
                    + "?, ?, ?, ?, ?\n"
                    + ");";
            
            stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, datum);
            stmt.setInt(2, treffpunktID);
            stmt.setInt(3, fahrlehrerID);
            stmt.setInt(4, kundeID);
            stmt.setInt(5, rechnungID);
            stmt.executeQuery();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Es konnte keine Verbindung hergestellt werden sie befinden sich im Testmodus");
        }
    }

    /**
     * Get data from Database
     *
     * @param sql
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet get(String sql) throws SQLException {
        ResultSet rs;
        try (Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(sql);
        }
        return rs;
    }

    /**
     * Set data from Database
     *
     * @param sql
     * @throws SQLException
     */
    public void set(String sql) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
