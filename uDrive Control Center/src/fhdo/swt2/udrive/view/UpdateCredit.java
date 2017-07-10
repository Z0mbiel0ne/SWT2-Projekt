/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.view;

import fhdo.swt2.udrive.controller.Converter;
import fhdo.swt2.udrive.model.services.KundenService;
import fhdo.swt2.udrive.model.services.objects.Fahrschueler;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Marcel
 */
public class UpdateCredit extends javax.swing.JFrame {

    private final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final KundenService KUNDENSERVICES;
    private final Converter CONVERTER;
    private final int id;
    private final JTable table;

    /**
     * Creates new form updateCredit
     *
     * @param id
     * @param table
     */
    public UpdateCredit(int id, JTable table) {
        log.info("Starting UpdateCredit UI");
        
        this.table = table;
        this.id = id;

        KUNDENSERVICES = new KundenService();
        CONVERTER = new Converter();

        initComponents();
        JRootPane jRootPane = SwingUtilities.getRootPane(jButton1);
        jRootPane.setDefaultButton(jButton1);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Lang/UpdateCredit", Locale.getDefault()); // NOI18N
        jButton1.setText(bundle.getString("UpdateCredit.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText(bundle.getString("UpdateCredit.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int value;
        Fahrschueler fahrschueler = new Fahrschueler();

        try {
            value = Integer.parseInt(jTextField1.getText());

            fahrschueler.setId(id);
            fahrschueler.setGuthaben(value);

            KUNDENSERVICES.updateCredit(fahrschueler);
        } catch (NumberFormatException ex) {
            Logger.getLogger(UpdateCredit.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setModel(CONVERTER.convertFahrschuelerToDefaultTableModel(KUNDENSERVICES.getKundenTable()));
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
