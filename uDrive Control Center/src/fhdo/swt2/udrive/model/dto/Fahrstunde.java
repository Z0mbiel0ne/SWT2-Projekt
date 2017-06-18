/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model.dto;

import java.util.Date;

/**
 * Fahrstunde - Transfer Obeject
 *
 * @author ExaShox
 */
public class Fahrstunde {

    private int id;
    private String kundeName,
            FahrlehrerName,
            strasse,
            plz,
            stadt;
    private Date datum;

    /**
     * Full constructor
     * 
     * @param id
     * @param kundeName
     * @param FahrlehrerName
     * @param strasse
     * @param plz
     * @param stadt
     * @param datum 
     */
    public Fahrstunde(int id, String kundeName, String FahrlehrerName, String strasse, String plz, String stadt, Date datum) {
        this.id = id;
        this.kundeName = kundeName;
        this.FahrlehrerName = FahrlehrerName;
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
        this.datum = datum;
    }

    /**
     * Noref constructor
     * 
     * @param kundeName
     * @param FahrlehrerName
     * @param strasse
     * @param plz
     * @param stadt
     * @param datum 
     */
    public Fahrstunde(String kundeName, String FahrlehrerName, String strasse, String plz, String stadt, Date datum) {
        this.kundeName = kundeName;
        this.FahrlehrerName = FahrlehrerName;
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
        this.datum = datum;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKundeName() {
        return kundeName;
    }

    public void setKundeName(String kundeName) {
        this.kundeName = kundeName;
    }

    public String getFahrlehrerName() {
        return FahrlehrerName;
    }

    public void setFahrlehrerName(String FahrlehrerName) {
        this.FahrlehrerName = FahrlehrerName;
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
