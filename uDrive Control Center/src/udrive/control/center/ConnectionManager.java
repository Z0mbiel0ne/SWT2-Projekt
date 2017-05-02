package udrive.control.center;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Managment for SQL-Connection
 * 
 * @author ExaShox
 */
public class ConnectionManager {
    private final static String 
            URL = "jdbc:mysql://localhost:3306/",
            DATABASE = "uDrive",
            DRIVER = "com.mysql.jdbc.Driver", // Verbindungsdaten
            USER = "root",
            PASSWORD = "";
    private static Connection conn;

    /**
     * Get Database Connection
     * 
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            try {
                conn = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD); // Verbindung aufbauen
            } catch (SQLException ex) {
                System.err.println(ex); // Exceptions abfangen
                System.out.println("ERROR: Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
            System.out.println("FATAL ERROR: Driver not found.");
        }
        return conn;
    }
}