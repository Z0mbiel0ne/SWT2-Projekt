/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.view;

import fhdo.swt2.udrive.controller.Converter;
import fhdo.swt2.udrive.model.DerRestDerInKeineKategoriePasstService;
import fhdo.swt2.udrive.model.FahrstundeService;
import fhdo.swt2.udrive.model.KundenService;
import fhdo.swt2.udrive.model.dto.Fahrlehrer;
import fhdo.swt2.udrive.model.dto.Fahrschueler;
import fhdo.swt2.udrive.model.dto.Treffpunkt;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Marcel
 */
public class ADDFahrstunde extends javax.swing.JFrame {

    private final DerRestDerInKeineKategoriePasstService SERVICE = new DerRestDerInKeineKategoriePasstService();
    private final KundenService KUNDENSERVICE = new KundenService();
    private final FahrstundeService FAHRSTUNDENSERVICE = new FahrstundeService();
    private final Converter CONVERTER = new Converter();
    ArrayList<Treffpunkt> treffpunktList;
    ArrayList<Fahrlehrer> fahrlehrerList;
    ArrayList<Fahrschueler> fahrschuelerList;
    private final JTable table1;
    private final JTable table2;

    /**
     * Creates new form ADDFahrstunde
     *
     * @param j1
     * @param j2
     * @throws java.sql.SQLException
     */
    public ADDFahrstunde(JTable j1, JTable j2) throws SQLException {
        table1 = j1;
        table2 = j2;
        initComponents();
        JRootPane rootPane = SwingUtilities.getRootPane(jButton1);
        rootPane.setDefaultButton(jButton1);
        jLabel11.setVisible(false);

        setVisible(rootPaneCheckingEnabled);
        Date date = java.util.Calendar.getInstance().getTime();
        jXDatePicker1.setDate(date);

        jComboBox1.addItem("Auswahl...");
        treffpunktList = SERVICE.getTreffpunkte();
        for (Treffpunkt treffpunkt : treffpunktList) {
            jComboBox1.addItem(treffpunkt.getPlz());
        }

        jComboBox2.addItem("Auswahl...");
        fahrlehrerList = SERVICE.getFahrlehrer();
        for (Fahrlehrer fahrlehrer : fahrlehrerList) {
            jComboBox2.addItem(fahrlehrer.getName());
        }

        jComboBox3.addItem("Auswahl...");
        fahrschuelerList = KUNDENSERVICE.getKunden();
        for (Fahrschueler fahrschueler : fahrschuelerList) {
            jComboBox3.addItem(fahrschueler.getNachname());
        }

        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Datum");

        jLabel2.setText("Treffpunkt");

        jLabel3.setText("Fahrlehrer");

        jComboBox3.setMaximumRowCount(10);
        jComboBox3.setToolTipText("");

        jLabel4.setText("Kunde");

        jLabel5.setText("RechnungID");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Hinzufügen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Bitte korrekte Werte eingeben.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox3, 0, 128, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date datum = df.format(jXDatePicker1.getDate());

            int id;

            id = jComboBox1.getSelectedIndex();
            Treffpunkt treffpunkt = treffpunktList.get(id - 1);

            id = jComboBox2.getSelectedIndex();
            Fahrlehrer fahrlehrer = fahrlehrerList.get(id - 1);

            id = jComboBox3.getSelectedIndex();
            Fahrschueler fahrschueler = fahrschuelerList.get(id - 1);

            int rechnungID = Integer.parseInt(jTextField1.getText());

            FAHRSTUNDENSERVICE.insertFahrstunde(datum, treffpunkt, fahrlehrer, fahrschueler, rechnungID);

            int row = table1.getSelectedRow();
            table2.setModel(CONVERTER.convertToDefaultTableModel(FAHRSTUNDENSERVICE.getFahrstundeTable(Integer.parseInt(table1.getValueAt(row, 0).toString()))));
            dispose();
        } catch (NumberFormatException ex) {
            Logger.getLogger(ADDFahrstunde.class.getName()).log(Level.SEVERE, null, ex);
            jLabel11.setVisible(true);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    // End of variables declaration//GEN-END:variables
}
