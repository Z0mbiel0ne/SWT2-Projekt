package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.model.dto.Fahrlehrer;
import fhdo.swt2.udrive.model.dto.Treffpunkt;
import fhdo.swt2.udrive.model.dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SQL Service
 *
 * @author ExaShox
 */
public class DerRestDerInKeineKategoriePasstService {

    /**
     * Konstruktor
     */
    public DerRestDerInKeineKategoriePasstService() {
    }

    public boolean checkPasswort(User user) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            String sqlString = "select passwort, personal.name from passwort join personal on passwort.personalID = personal.PersonalID where personal.name = ?";
            stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String test = rs.getString(1);

            stmt.close();
            conn.close();
            return user.getPassword().equals(test);
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Es konnte keine Verbindung hergestellt werden Sie befinden sich im Testmodus");
            return false;
        }
    }

    /**
     * Liefert ALLE Treffpunkte in der Form ID|Straße
     *
     * @return Bsp: [0][0] : 1 [0][1] : Essen
     */
    public ArrayList<Treffpunkt> getTreffpunkte() {
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
                Treffpunkt treffpunkt = new Treffpunkt(
                        rs.getInt("TreffpunktID"),
                        rs.getString("Straße"),
                        rs.getString("Postleitzahl"),
                        rs.getString("Stadt")
                );

                treffpunktList.add(treffpunkt);
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return treffpunktList;
        }
    }

    /**
     * Liefert ALLE Fahrlehrer in der Form FahrlehrerID|Vorname
     *
     * @return Bsp: [0][0] : 1 [0][1] : Hans
     */
    public ArrayList<Fahrlehrer> getFahrlehrer() {
        ArrayList<Fahrlehrer> fahrlehrerList = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            // select data
            String sqlString
                    = "SELECT "
                    + "fa.FahrerID AS FahrerID, "
                    + "pe.PersonalID AS PersonalID, "
                    + "pe.Vorname AS Vorname, "
                    + "pe.Name AS Name, "
                    + "pe.Postleitzahl AS Postleitzahl, "
                    + "pe.Stadt AS Stadt, "
                    + "pe.Straße AS Straße, "
                    + "pe.Hausnummer AS Hausnummer "
                    + "FROM fahrlehrer AS fa\n"
                    + "INNER JOIN personal AS pe\n"
                    + "ON pe.PersonalID = fa.PersonalID;;";

            stmt = conn.prepareStatement(sqlString); // Prepared Statement anlegen 
            ResultSet rs = stmt.executeQuery(); // Query absetzen und ResultSet zurückholen

            while (rs.next()) {
                Fahrlehrer fahrlehrer = new Fahrlehrer(
                        rs.getInt("FahrerID"),
                        rs.getInt("PersonalID"),
                        rs.getString("Vorname"),
                        rs.getString("Name"),
                        rs.getString("Postleitzahl"),
                        rs.getString("Stadt"),
                        rs.getString("Straße"),
                        rs.getString("Hausnummer")
                );

                fahrlehrerList.add(fahrlehrer);
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DerRestDerInKeineKategoriePasstService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return fahrlehrerList;
        }
    }
}
