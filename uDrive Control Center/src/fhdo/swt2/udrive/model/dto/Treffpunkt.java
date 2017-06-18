/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model.dto;

/**
 * Treffpunkt - Transfer Object
 *
 * @author ExaShox
 */
public class Treffpunkt {

    private int id;
    private String strasse,
            plz,
            stadt;

    /**
     * Full constructor
     *
     * @param id
     * @param strasse
     * @param plz
     * @param stadt
     */
    public Treffpunkt(int id, String strasse, String plz, String stadt) {
        this.id = id;
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
    }

    /**
     * Noref constructor
     *
     * @param strasse
     * @param plz
     * @param stadt
     */
    public Treffpunkt(String strasse, String plz, String stadt) {
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
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
}
