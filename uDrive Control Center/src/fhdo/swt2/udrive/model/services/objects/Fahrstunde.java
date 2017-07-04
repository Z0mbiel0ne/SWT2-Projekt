/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model.services.objects;

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
     * Gibt die vollständige Adresse wieder
     * 
     * @return fullAddress
     */
    public String getFullAddress() {
        return strasse + ", " + plz + " " + stadt;
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
