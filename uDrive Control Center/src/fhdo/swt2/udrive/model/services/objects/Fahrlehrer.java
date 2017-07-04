package fhdo.swt2.udrive.model.services.objects;

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
