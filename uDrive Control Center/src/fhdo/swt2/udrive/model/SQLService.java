package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.controller.Converter;
import fhdo.swt2.udrive.model.dto.Fahrlehrer;
import fhdo.swt2.udrive.model.dto.Fahrstunde;
import fhdo.swt2.udrive.model.dto.Fahrschueler;
import fhdo.swt2.udrive.model.dto.Treffpunkt;
import fhdo.swt2.udrive.model.dto.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SQL Service
 *
 * @author ExaShox
 */
public class SQLService {

    /**
     * Konstruktor
     */
    public SQLService() {
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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Fahrschueler> getKundenTable() {
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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return kundeList;
        }
    }

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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return fahrstundeList;
        }
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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updated den Geldbetrag eines Kunden
     *
     * @param id KundeID
     * @param value Betrag
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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return fahrlehrerList;
        }
    }

    /**
     * Liefert ALLE Kunden in der Form ID|Vorname
     *
     * @return Bsp: [0][0] : 1 [0][1] : Stephan
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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return kundeList;
        }
    }

    /**
     * Erstellt einen Fahrstunde in der Tabelle Fahrstunden
     *
     * @param fahrstunde
     */
    public void insertFahrstunde(Fahrstunde fahrstunde, Treffpunkt treffpunkt,
            Fahrlehrer fahrlehrer, Fahrschueler kunde, int rechnungID) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement stmt;

            java.sql.Date sqlDate = new java.sql.Date(fahrstunde.getDatum().getTime());

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
            Logger.getLogger(SQLService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
