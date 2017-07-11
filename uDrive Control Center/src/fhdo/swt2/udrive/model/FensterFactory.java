/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.view.Fenster;

/**
 * Factory for Fenster
 * @author Marcel
 */
public class FensterFactory {
    Fenster fenster;
    
    /**
     * Creates a new Instance of Fenster
     * @return Object of Fenster
     */
    public Fenster newInstance()
    {
        fenster = new Fenster();
        return fenster;
    }
}
