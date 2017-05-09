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
<<<<<<< HEAD
    
        public void deleteKunde(int id)
    {
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
        }
    }
    
=======

>>>>>>> refs/remotes/origin/master
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
            String sqlString = "SELECT KundeID, CONCAT(Vorname, ' ', Nachname), CONCAT(Straße, ', ', Postleitzahl, ' ', Stadt), Guthaben from kunde";
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

    public TableModel getFortschrittTable(int kundenID) {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt;
        DefaultTableModel tab = new DefaultTableModel();
        try {
            // select data
            String sqlString
                    = "SELECT fs.FahrstundeID, fs.Datum, ku.Vorname, pe.Vorname, CONCAT(tp.Straße, ', ', tp.Postleitzahl, ' ', tp.Stadt) AS Adresse"
                    + "FROM kunde AS ku"
                    + "INNER JOIN fahrstunde AS fs"
                    + "ON ku.KundeID = fs.KundeID"
                    + "INNER JOIN fahrlehrer AS fl"
                    + "ON fl.FahrlehrerID = fs.FahrlehrerID"
                    + "INNER JOIN personal AS pe"
                    + "ON pe.PersonalID = fl.PersonalID"
                    + "INNER JOIN treffpunkt AS tp"
                    + "ON tp.TreffpunktID = fs.TreffpunktID"
                    + "WHERE ku.KundenID = " + kundenID;
            
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
            if (pass.equals(test)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(Bonusaufgabe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
