package fhdo.swt2.udrive.model.dto;

/**
 * Kunden eigentlich nur Personal, aber Marcel meint das waer besser.. Idiot -
 * Transfer Object
 *
 * @author ExaShox
 */
public class Fahrlehrer {

    private int fahrlehrerID,
            personalID;
    private String vorname,
            name,
            plz,
            stadt,
            strasse,
            hausnummer;

    /**
     * Konstruktor
     *
     * @param fahrlehrerID
     * @param personalID
     * @param vorname
     * @param name
     * @param plz
     * @param stadt
     * @param strasse
     * @param hausnummer
     */
    public Fahrlehrer(int fahrlehrerID, int personalID, String vorname, String name, String plz, String stadt, String strasse, String hausnummer) {
        this.fahrlehrerID = fahrlehrerID;
        this.personalID = personalID;
        this.vorname = vorname;
        this.name = name;
        this.plz = plz;
        this.stadt = stadt;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    /**
     * Konstruktor
     *
     * @param vorname
     * @param name
     * @param plz
     * @param stadt
     * @param strasse
     * @param hausnummer
     */
    public Fahrlehrer(String vorname, String name, String plz, String stadt, String strasse, String hausnummer) {
        this.vorname = vorname;
        this.name = name;
        this.plz = plz;
        this.stadt = stadt;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    public int getFahrlehrerID() {
        return fahrlehrerID;
    }

    public void setFahrlehrerID(int fahrlehrerID) {
        this.fahrlehrerID = fahrlehrerID;
    }

    public int getPersonalID() {
        return personalID;
    }

    public void setPersonalID(int personalID) {
        this.personalID = personalID;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }
}
