/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.view.ADDFahrstunde;
import fhdo.swt2.udrive.view.Fenster;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Marcel
 */
public class ADDFahrstundenFactory 
{
    ADDFahrstunde addfahrstunde;
    
    public ADDFahrstundenFactory()
    {
      
    }
    
    public Object newInstance(JTable jTable1, JTable jTable2)
    {
        try {
             addfahrstunde = new ADDFahrstunde(jTable1, jTable2);
             return addfahrstunde;
        } catch (SQLException ex) {
            Logger.getLogger(Fenster.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}



