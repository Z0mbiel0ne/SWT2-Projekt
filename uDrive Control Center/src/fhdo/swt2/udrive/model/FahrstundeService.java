/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.model.dto.Fahrlehrer;
import fhdo.swt2.udrive.model.dto.Fahrschueler;
import fhdo.swt2.udrive.model.dto.Fahrstunde;
import fhdo.swt2.udrive.model.dto.Treffpunkt;
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
public class FahrstundeService {

    /**
     * Empty Constructor
     */
    public FahrstundeService() {
    }

    /**
     * Gimme Fahrstunde
     * 
     * @param kundenID
     * @return 
     */
    public ArrayList<Fahrstunde> getFahrstundeTable(int kundenID) {
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
                Fahrstunde fahrstunde = new Fahrstunde(
                        rs.getInt("ID"),
                        rs.getString("Schüler"),
                        rs.getString("Fahrlehrer"),
                        rs.getString("Strasse"),
                        rs.getString("PLZ"),
                        rs.getString("Stadt"),
                        rs.getDate("Datum")
                );

                fahrstundeList.add(fahrstunde);
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
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
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;
            String sqlString = "DELETE FROM fahrstunde WHERE FahrstundeID = ?";

            stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, fahrstunde.getId());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
