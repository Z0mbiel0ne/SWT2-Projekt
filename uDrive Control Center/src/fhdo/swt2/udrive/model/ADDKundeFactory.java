/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.view.ADDKunde;
import javax.swing.JTable;

/**
 * ADDKundeFactory
 * @author Marcel
 */
public class ADDKundeFactory {

    ADDKunde addKunde;
    /**
     * Creates a new Instance of AddFahrstundenView with Table Data
     * @param jTable1 Table of Customer
     * @return Object of AddKundeView
     */
    public ADDKunde newInstance(JTable jTable1) {
        addKunde = new ADDKunde(jTable1);
        return addKunde;
    }
}
