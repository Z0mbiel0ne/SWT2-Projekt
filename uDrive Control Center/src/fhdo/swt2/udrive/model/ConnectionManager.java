package fhdo.swt2.udrive.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Managment for SQL-Connection
 *
 * @author ExaShox
 */
public class ConnectionManager {

    private final static String URL = "jdbc:mysql://dd23226.kasserver.com:3306/",
            DATABASE = "d0265e02",
            DRIVER = "com.mysql.jdbc.Driver",
            USER = "d0265e02",
            PASSWORD = "p5p4zc2b9edc4P5K";
    private static Connection conn;

    /**
     * Get Database Connection
     *
     * @return Connection
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD); // Verbindung aufbauen
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
            System.out.println("FATAL ERROR: Driver not found!");
        }
        return conn;
    }

    /**
     * Check Connection availability. Returns true if success.
     *
     * @return boolean
     */
    public boolean checkConnection() {
        try {
            conn = getConnection();

            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
