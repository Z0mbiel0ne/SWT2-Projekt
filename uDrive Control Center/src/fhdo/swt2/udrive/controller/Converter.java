/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.controller;

import fhdo.swt2.udrive.model.services.objects.Fahrschueler;
import fhdo.swt2.udrive.model.services.objects.Fahrstunde;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Function;
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
        Vector<Object> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("Datum");
        columnNames.add("Fahrschüler");
        columnNames.add("Fahrlehrer");
        columnNames.add("Adresse");

        Vector<Vector<Object>> data = new Vector<>();
        list.stream().map((Fahrstunde Fahrstunde) -> {
            Vector<Object> dataitems = new Vector<>();
            dataitems.add(Fahrstunde.getId());
            dataitems.add(Fahrstunde.getDatum());
            dataitems.add(Fahrstunde.getKundeName());
            dataitems.add(Fahrstunde.getFahrlehrerName());
            dataitems.add(Fahrstunde.getFullAddress());
            return dataitems;
        }).forEachOrdered((dataitems) -> {
            data.add(dataitems);
        });

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        return tableModel;
    }

    /**
     * Convert ArrayList ti DefaultTableModel
     *
     * @param list
     * @return
     */
    public DefaultTableModel convertFahrschuelerToDefaultTableModel(ArrayList<Fahrschueler> list) {
        Vector<Object> columnNames = new Vector<>();
        columnNames.add("Id");
        columnNames.add("Name");
        columnNames.add("Adresse");
        columnNames.add("Guthaben");

        Vector<Vector<Object>> data = new Vector<>();
        list.stream().map((Fahrschueler fahrschueler) -> {
            Vector<Object> dataitems = new Vector<>();
            dataitems.add(fahrschueler.getId());
            dataitems.add(fahrschueler.getVorname() + " " + fahrschueler.getNachname());
            dataitems.add(fahrschueler.getFullAddress());
            dataitems.add(fahrschueler.getGuthaben());
            return dataitems;
        }).forEachOrdered((dataitems) -> {
            data.add(dataitems);
        });

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
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
