/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

import java.util.logging.Logger;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mos
 */
public class HomeGUI extends javax.swing.JFrame {

    private static final Logger LOGGER = Logger.getLogger(HomeGUI.class.getName());

    private NetflixLibrary netflixLibrary;
    private SelectedTitleGUI selectedTitleGUI = new SelectedTitleGUI();

    /**
     * Creates new form HomeGUI
     */
    public HomeGUI() {
        initComponents();
        LoggingUtil.setupLogger(LOGGER);

        netflixLibrary = new NetflixLibrary();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regionSelectionLabel = new javax.swing.JLabel();
        rollButton = new javax.swing.JButton();
        regionSelectionMenu = new javax.swing.JComboBox<>();
        titleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Netflix roulette");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        regionSelectionLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 14)); // NOI18N
        regionSelectionLabel.setText("Select Netflix region");
        getContentPane().add(regionSelectionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        rollButton.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        rollButton.setText("Roll");
        rollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollButtonActionPerformed(evt);
            }
        });
        getContentPane().add(rollButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        regionSelectionMenu.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        regionSelectionMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        regionSelectionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regionSelectionMenuActionPerformed(evt);
            }
        });
        getContentPane().add(regionSelectionMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 190, -1));

        titleLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 24)); // NOI18N
        titleLabel.setText("Netflix roulette");
        getContentPane().add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        setSize(new java.awt.Dimension(618, 347));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollButtonActionPerformed
        LOGGER.log(Level.FINE, "Roll button pressed");

        // Fetch Netflix library and select a random title.
        JSONArray netflixTitles = null;

        while (netflixTitles == null) {
            netflixTitles = netflixLibrary.fetchTitles();
        }

        JSONObject selectedTitle = NetflixLibrary.selectRandomTitle(netflixTitles);
        selectedTitleGUI.updateTitleInfo(selectedTitle);
        selectedTitleGUI.setVisible(true);
    }//GEN-LAST:event_rollButtonActionPerformed

    private void regionSelectionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regionSelectionMenuActionPerformed

    }//GEN-LAST:event_regionSelectionMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            LoggingUtil.logException(LOGGER, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel regionSelectionLabel;
    private javax.swing.JComboBox<String> regionSelectionMenu;
    private javax.swing.JButton rollButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
