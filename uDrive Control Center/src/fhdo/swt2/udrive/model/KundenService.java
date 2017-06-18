/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.model.dto.Fahrschueler;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ExaShox
 */
public class KundenService {

    /**
     * Empty Constructor
     */
    public KundenService() {
    }

    /**
     * Legt einen neuen Kunden in der Datenbank an
     *
     * @param kunde
     */
    public void addKunde(Fahrschueler kunde) {
        try (Connection conn = ConnectionManager.getConnection()) {
            CallableStatement stmt;

            String sqlString = "{CALL addKunde(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 

            stmt.setString(1, kunde.getVorname()); // Parameter setzen
            stmt.setString(2, kunde.getNachname());
            stmt.setString(3, kunde.getPlz());
            stmt.setString(4, kunde.getStadt());
            stmt.setString(5, kunde.getStrasse());
            stmt.setInt(6, kunde.getHausnummer());
            stmt.setString(7, kunde.getKontonummer());
            stmt.setString(8, kunde.getBlz());
            stmt.setString(9, kunde.getIban());
            stmt.setString(10, kunde.getBic());

            stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vernichtet einen Kunden
     *
     * @param kunde
     * @throws SQLException
     */
    public void deleteKunde(Fahrschueler kunde) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection()) {
            CallableStatement stmt;

            String sqlString = "{CALL deleteKunde(?)}";
            stmt = conn.prepareCall(sqlString); // Prepared Statement anlegen 
            stmt.setInt(1, kunde.getId());
            stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Hier nix Conventieren. Bitte weg damit
     *
     * @return ArrayList 
     */
    public ArrayList<Fahrschueler> getKundenTable() {
        return getKunden();
    }

    /**
     * Liefert ALLE Kunden
     *
     * @return ArrayList
     */
    public ArrayList<Fahrschueler> getKunden() {
        ArrayList<Fahrschueler> kundeList = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString = "SELECT * FROM kunde";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            while (rs.next()) {
                Fahrschueler kunde = new Fahrschueler(
                        rs.getInt("KundeID"),
                        rs.getInt("Hausnummer"),
                        rs.getInt("Guthaben"),
                        rs.getString("Vorname"),
                        rs.getString("Nachname"),
                        rs.getString("Postleitzahl"),
                        rs.getString("Stadt"),
                        rs.getString("Straße")
                );

                kundeList.add(kunde);
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return kundeList;
        }
    }

    /**
     * Updatet den Geldbetrag eines Kunden
     *
     * @param kunde
     */
    public void updateCredit(Fahrschueler kunde) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            String sqlString = "UPDATE kunde AS ku "
                    + "SET ku.Guthaben = ku.Guthaben + ? "
                    + "WHERE ku.KundeID = ? ";

            stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, kunde.getGuthaben());
            stmt.setInt(2, kunde.getId());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
