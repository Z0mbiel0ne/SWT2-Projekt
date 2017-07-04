package fhdo.swt2.udrive.controller;

import fhdo.swt2.udrive.model.services.objects.Fahrschueler;
import fhdo.swt2.udrive.model.services.objects.Fahrstunde;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
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
        LinkedList<Object> columnNames = new LinkedList<>();
        columnNames.add("ID");
        columnNames.add("Datum");
        columnNames.add("Fahrschüler");
        columnNames.add("Fahrlehrer");
        columnNames.add("Adresse");

        LinkedList<LinkedList<Object>> data = new LinkedList<>();
        list.stream().map((Fahrstunde Fahrstunde) -> {
            LinkedList<Object> dataitems = new LinkedList<>();
            dataitems.add(Fahrstunde.getId());
            dataitems.add(Fahrstunde.getDatum());
            dataitems.add(Fahrstunde.getKundeName());
            dataitems.add(Fahrstunde.getFahrlehrerName());
            dataitems.add(Fahrstunde.getFullAddress());
            return dataitems;
        }).forEachOrdered((dataitems) -> {
            data.add(dataitems);
        });

        Object[][] objectArray = convertLinkedListToVector(data);
        DefaultTableModel tableModel = new DefaultTableModel(objectArray, columnNames.toArray());
        return tableModel;
    }

    /**
     * Convert ArrayList ti DefaultTableModel
     *
     * @param list
     * @return
     */
    public DefaultTableModel convertFahrschuelerToDefaultTableModel(ArrayList<Fahrschueler> list) {
        LinkedList<Object> columnNames = new LinkedList<>();
        columnNames.add("Id");
        columnNames.add("Name");
        columnNames.add("Adresse");
        columnNames.add("Guthaben");

        LinkedList<LinkedList<Object>> data = new LinkedList<>();
        list.stream().map((Fahrschueler fahrschueler) -> {
            LinkedList<Object> dataitems = new LinkedList<>();
            dataitems.add(fahrschueler.getId());
            dataitems.add(fahrschueler.getVorname() + " " + fahrschueler.getNachname());
            dataitems.add(fahrschueler.getFullAddress());
            dataitems.add(fahrschueler.getGuthaben());
            return dataitems;
        }).forEachOrdered((dataitems) -> {
            data.add(dataitems);
        });

        Object[][] objectArray = convertLinkedListToVector(data);
        DefaultTableModel tableModel = new DefaultTableModel(objectArray, columnNames.toArray());
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
            LinkedList<String> columnNames = new LinkedList<>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            LinkedList<LinkedList<Object>> data = new LinkedList<>();
            while (resultSet.next()) {
                LinkedList<Object> vector = new LinkedList<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(resultSet.getObject(columnIndex));
                }
                data.add(vector);
            }

            Object[][] objectArray = convertLinkedListToVector(data);
            DefaultTableModel tableModel = new DefaultTableModel(objectArray, columnNames.toArray());
            return tableModel;
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
            LinkedList<String[]> data = new LinkedList<>();
            while (resultSet.next()) {
                LinkedList<String> vector = new LinkedList<>();
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

    /**
     * Convert LinkedList to Object[][]
     *
     * @param list
     * @return doubleArray
     */
    private Object[][] convertLinkedListToVector(LinkedList<LinkedList<Object>> list) {
        if (list.isEmpty()) {
            return new Object[0][0];
        }

        Object[][] doubleArray = new Object[list.size()][list.getFirst().size()];
        int i = 0;

        for (LinkedList<Object> l : list) {
            int j = 0;
            for (Object o : l) {
                doubleArray[i][j++] = o;
            }
            i++;
        }
        return doubleArray;
    }
}
