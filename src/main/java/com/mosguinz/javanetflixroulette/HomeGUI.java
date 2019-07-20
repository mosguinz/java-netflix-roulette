/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

import java.util.ArrayList;

/**
 *
 * @author Mos
 */
public class HomeGUI extends javax.swing.JFrame {
    
    private NetflixLibrary netflixLibrary;

    /**
     * Creates new form HomeGUI
     */
    public HomeGUI() {
        initComponents();
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

        titleLabel = new javax.swing.JLabel();
        rollButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Netflix roulette");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 24)); // NOI18N
        titleLabel.setText("Netflix roulette");
        getContentPane().add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        rollButton.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        rollButton.setText("Roll");
        rollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollButtonActionPerformed(evt);
            }
        });
        getContentPane().add(rollButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        setSize(new java.awt.Dimension(618, 347));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollButtonActionPerformed
        // Fetch Netflix library and select a random title.
        ArrayList<NetflixTitle> netflixTitles = netflixLibrary.fetchTitles();
        NetflixTitle selectedTitle = NetflixLibrary.selectRandomTitle(netflixTitles);
        
        System.out.println(selectedTitle.netflixID);
        System.out.println(selectedTitle.title);
        System.out.println(selectedTitle.imageURL);
        System.out.println(selectedTitle.synopsis);
        System.out.println(selectedTitle.rating);
        System.out.println(selectedTitle.type);
        System.out.println(selectedTitle.releaseYear);
        System.out.println(selectedTitle.runtime);
//        
//        this.dispose();
        new SelectedTitleGUI(selectedTitle).setVisible(true);
    }//GEN-LAST:event_rollButtonActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton rollButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
