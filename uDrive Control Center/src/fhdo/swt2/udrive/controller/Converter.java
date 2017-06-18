/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.controller;

import fhdo.swt2.udrive.model.DerRestDerInKeineKategoriePasstService;
import fhdo.swt2.udrive.model.KundenService;
import fhdo.swt2.udrive.model.dto.Fahrschueler;
import fhdo.swt2.udrive.model.dto.Fahrstunde;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * Converter Class
 *
 * @author ExaShox
 */
public class Converter {

    /**
     * Konstruktor
     */
    public Converter() {
    }

    /**
     * Convert ArrayList ti DefaultTableModel
     * 
     * @param list
     * @return 
     */
    public DefaultTableModel convertFahrstundeToDefaultTableModel(ArrayList<Fahrstunde> list) {
        DefaultTableModel tableModel = new DefaultTableModel();
        
        for (Fahrstunde fahrstunde : list) {
          Object[] data = { fahrstunde.getId(), 
                            fahrstunde.getDatum(),
                            fahrstunde.getKundeName(),
                            fahrstunde.getFahrlehrerName(),
                            fahrstunde.getPlz()};
         tableModel.addRow(data);   
        }
        
        return tableModel;
    }
    /**
     * Convert ArrayList ti DefaultTableModel
     * 
     * @param list
     * @return 
     */
    public DefaultTableModel convertFahrschuelerToDefaultTableModel(ArrayList<Fahrschueler> list) {
        DefaultTableModel tableModel = new DefaultTableModel();
        
        for (Fahrschueler fahrschueler : list) {
          Object[] data = { fahrschueler.getId(), 
                            fahrschueler.getVorname()+ 
                            fahrschueler.getNachname(),
                            fahrschueler.getFullAddress(),
                            fahrschueler.getGuthaben()};
         tableModel.addRow(data);   
        }
        
        return tableModel;
    }

    /**
     * Convert ResultSet to DefaultTableModel with header
     *
     * @param resultSet
     * @return DefaultTableModel
     */
    public DefaultTableModel convertToDefaultTableModel(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData;
            metaData = resultSet.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(resultSet.getObject(columnIndex));
                }
                data.add(vector);
            }

            return new DefaultTableModel(data, columnNames);
        } catch (SQLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            return new DefaultTableModel();
        }
    }

    /**
     * Convert ResultSet to 2D String Array
     *
     * @param resultSet
     * @return String[][]
     */
    public String[][] convertTo2DStringArray(ResultSet resultSet) {
        String[][] resultArray = new String[0][0];

        try {
            ResultSetMetaData metaData;
            metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // data of the table
            Vector<String[]> data = new Vector<>();
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(resultSet.getString(columnIndex));
                }
                data.add(vector.toArray(new String[vector.size()]));
            }

            resultArray = data.toArray(new String[data.size()][]);
        } catch (SQLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultArray;
    }
}
