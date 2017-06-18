package fhdo.swt2.udrive.model.dto;

/**
 * Fahrschueler - Transfer Object
 *
 * @author ExaShox
 */
public class Fahrschueler {

    private int id,
            hausnummer,
            guthaben;
    private String vorname,
            nachname,
            plz,
            stadt,
            strasse,
            kontonummer,
            blz,
            iban,
            bic;

    /**
     * Full constructor
     *
     * @param id
     * @param hausnummer
     * @param guthaben
     * @param vorname
     * @param nachname
     * @param plz
     * @param stadt
     * @param strasse
     * @param kontonummer
     * @param blz
     * @param iban
     * @param bic
     */
    public Fahrschueler(int id, int hausnummer, int guthaben, String vorname,
            String nachname, String plz, String stadt, String strasse,
            String kontonummer, String blz, String iban, String bic) {
        this.id = id;
        this.hausnummer = hausnummer;
        this.guthaben = guthaben;
        this.vorname = vorname;
        this.nachname = nachname;
        this.plz = plz;
        this.stadt = stadt;
        this.strasse = strasse;
        this.kontonummer = kontonummer;
        this.blz = blz;
        this.iban = iban;
        this.bic = bic;
    }

    /**
     * Noref constructor
     *
     * @param vorname
     * @param nachname
     * @param plz
     * @param stadt
     * @param strasse
     * @param kontonummer
     * @param blz
     * @param iban
     * @param bic
     * @param hausnummer
     */
    public Fahrschueler(String vorname, String nachname, String plz, String stadt,
            String strasse, String kontonummer, String blz, String iban,
            String bic, int hausnummer) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.plz = plz;
        this.stadt = stadt;
        this.strasse = strasse;
        this.kontonummer = kontonummer;
        this.blz = blz;
        this.iban = iban;
        this.bic = bic;
        this.hausnummer = hausnummer;
        this.guthaben = 0;
    }

    /**
     * Only kunde constructor
     *
     * @param id
     * @param hausnummer
     * @param guthaben
     * @param vorname
     * @param nachname
     * @param plz
     * @param stadt
     * @param strasse
     */
    public Fahrschueler(int id, int hausnummer, int guthaben, String vorname, String nachname,
            String plz, String stadt, String strasse) {
        this.id = id;
        this.hausnummer = hausnummer;
        this.guthaben = guthaben;
        this.vorname = vorname;
        this.nachname = nachname;
        this.plz = plz;
        this.stadt = stadt;
        this.strasse = strasse;
    }

    /**
     * Get full address
     *
     * @return
     */
    public String getFullAddress() {
        return strasse + " " + hausnummer + ", " + stadt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
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

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getBlz() {
        return blz;
    }

    public void setBlz(String blz) {
        this.blz = blz;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getGuthaben() {
        return guthaben;
    }

    public void setGuthaben(int guthaben) {
        this.guthaben = guthaben;
    }
}
