package udrive.control.center;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
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
    public void addKunde(String vorname, String nachname, int plz, String stadt, String strasse, int hausnummer, int kontonummer, int blz, int iban, int bic){
       Connection conn = ConnectionManager.getConnection();
        CallableStatement stmt;
        try {
                     
            String sqlString = "{CALL addKunde(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            
            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 
            
            stmt.setString(1, vorname); // Parameter setzen
            stmt.setString(2,nachname);
            stmt.setInt(3,plz);
            stmt.setString(4,stadt);
            stmt.setString(5,strasse);
            stmt.setInt(6,hausnummer);
            stmt.setInt(7,kontonummer);
            stmt.setInt(8,blz);
            stmt.setInt(9,iban);
            stmt.setInt(10,bic);
            
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen
            
            stmt.close();
        
        } catch (SQLException ex) {
            //TODO Logger
            //Logger.getLogger(Bonusaufgabe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    /**
     * Liefert alle vorhandenen Bezirke und gibt diese aus
     */
    public void getBezirke()
    {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt; 
        try{           
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
    
    public TableModel getKundenTable()
    {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt; 
        DefaultTableModel tab = new DefaultTableModel();
        try{           
            String sqlString = "SELECT KundeID, Concat(Nachname,Vorname), Stadt, Guthaben from kunde";
            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen
            
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            while (rs.next())
            {
                Object [] rowData = new Object[numberOfColumns];
                for (int i = 0; i < rowData.length; ++i)
                {
                    rowData[i] = rs.getObject(i+1);
                }
                tab.addRow(rowData);
            }
            

        } catch (SQLException ex) {
            //Logger.getLogger(Bonusaufgabe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tab;
    }
    
    public boolean checkPasswort(String user, String pass)
    {
        try 
        {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt;

            String sqlString = "select passwort, personal.name from passwort join personal on passwort.personalID = personal.PersonalID where personal.name = ?";
            stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String test = rs.getString(1);
            if(pass.equals(test))
            {
                return true;
            }
            else
            {
                return false;
            }
           } 
            catch (SQLException ex) {
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
