/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.net.URL;
import java.io.IOException;
import java.awt.Image;
import java.awt.Desktop;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.JSONObject;

/**
 *
 * @author Mos
 */
public class SelectedTitleGUI extends javax.swing.JFrame {

    private static final Logger LOGGER = Logger.getLogger(SelectedTitleGUI.class.getName());

    final String netflixID;
    final String title;
    final String imageURL;
    final String synopsis;
    final String rating;
    final String type;
    final String releaseYear;
    final String runtime;

    /**
     * Creates new form SelectedTitleGUI
     *
     * @param selectedTitle
     */
    public SelectedTitleGUI(JSONObject selectedTitle) {
        initComponents();
        SetupLogging.setup(LOGGER);

        this.netflixID = selectedTitle.getString("netflixid");
        this.title = selectedTitle.getString("title");
        this.imageURL = selectedTitle.getString("image");
        this.synopsis = selectedTitle.getString("synopsis");
        this.rating = selectedTitle.getString("rating");
        this.type = selectedTitle.getString("type");
        this.releaseYear = selectedTitle.getString("released");
        this.runtime = selectedTitle.getString("runtime");

        // Display poster image for selected title.
        setTitlePosterImage();
        setTitleInfo();
    }

    public SelectedTitleGUI() {
        initComponents();
        netflixID = title = imageURL = synopsis = rating = type = releaseYear = runtime = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePosterImage = new javax.swing.JLabel();
        titleSubtext = new javax.swing.JLabel();
        titleName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        titleSynopsis = new javax.swing.JLabel();
        returnToMainButton = new javax.swing.JButton();
        watchOnNetflixButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titlePosterImage.setFont(new java.awt.Font("Helvetica Neue World", 1, 14)); // NOI18N
        titlePosterImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlePosterImage.setText("Title poster image");
        titlePosterImage.setPreferredSize(new java.awt.Dimension(166, 233));
        getContentPane().add(titlePosterImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        titleSubtext.setFont(new java.awt.Font("Helvetica Neue World", 0, 14)); // NOI18N
        titleSubtext.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titleSubtext.setText("Year · runtime");
        getContentPane().add(titleSubtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 420, 50));

        titleName.setFont(new java.awt.Font("Helvetica Neue World", 1, 24)); // NOI18N
        titleName.setText("Title name");
        titleName.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(titleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 410, 50));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 420, -1));

        titleSynopsis.setFont(new java.awt.Font("Helvetica Neue World", 0, 14)); // NOI18N
        titleSynopsis.setText("Title synopsis");
        titleSynopsis.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(titleSynopsis, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 420, 160));

        returnToMainButton.setText("Return to Main");
        returnToMainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnToMainButtonActionPerformed(evt);
            }
        });
        getContentPane().add(returnToMainButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));

        watchOnNetflixButton.setText("Watch on Netflix");
        watchOnNetflixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                watchOnNetflixButtonActionPerformed(evt);
            }
        });
        getContentPane().add(watchOnNetflixButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, -1));

        setSize(new java.awt.Dimension(663, 347));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void returnToMainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnToMainButtonActionPerformed
        LOGGER.log(Level.FINE, "\"{0}\" button pressed\n{1}", new Object[]{evt.getActionCommand(), evt.paramString()});
        this.dispose();

    }//GEN-LAST:event_returnToMainButtonActionPerformed

    private void watchOnNetflixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_watchOnNetflixButtonActionPerformed
        LOGGER.log(Level.FINE, "\"{0}\" button pressed\n{1}", new Object[]{evt.getActionCommand(), evt.paramString()});

        LOGGER.log(Level.FINER, "Creating Netflix URL for title");
        String netflixURL = "https://www.netflix.com/title/" + netflixID;

        try {
            LOGGER.log(Level.FINE, "Opening URL in browser...");
            Desktop.getDesktop().browse(new URL(netflixURL).toURI());
        } catch (IOException | URISyntaxException e) {
            LOGGER.log(Level.SEVERE, "Something went wrong. Could not open broswer.\n{0}\n{1}",
                    new Object[]{e.toString(), e});
        }
    }//GEN-LAST:event_watchOnNetflixButtonActionPerformed

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
            java.util.logging.Logger.getLogger(SelectedTitleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectedTitleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectedTitleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectedTitleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectedTitleGUI().setVisible(true);
            }
        });
    }

    private void setTitlePosterImage() {
        LOGGER.log(Level.FINE, "Adding poster image");

        LOGGER.log(Level.FINER, "Removing placeholder text");
        titlePosterImage.setText("");

        try {
            LOGGER.log(Level.FINE, "Fetching poster image from URL: {0}", imageURL);
            URL url = new URL(imageURL);
            Image image = ImageIO.read(url);
            titlePosterImage.setIcon(new ImageIcon(image));
            LOGGER.log(Level.FINE, "Poster image is set");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Poster image could not be added; placing placeholder text instead...");
            titlePosterImage.setText("Image not available");
        }
    }

    private void setTitleInfo() {
        LOGGER.log(Level.FINE, "Creating selected title info element");

        LOGGER.log(Level.FINER, "Adding title name");
        titleName.setText("<html>" + title + "</html>");

        LOGGER.log(Level.FINER, "Creating subtext for selected title");
        String subtext = releaseYear;
        if (type.equals("movie")) {
            LOGGER.log(Level.FINER, "Title is a movie; adding runtime info to subtext");
            subtext += " · Movie · " + runtime;
        } else {
            subtext += " · Series";
        }

        LOGGER.log(Level.FINER, "Adding subtext");
        titleSubtext.setText(subtext);
        titleSynopsis.setText("<html><p style=\"line-height: 2%;\">" + synopsis + "</p></html>");

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton returnToMainButton;
    private javax.swing.JLabel titleName;
    private javax.swing.JLabel titlePosterImage;
    private javax.swing.JLabel titleSubtext;
    private javax.swing.JLabel titleSynopsis;
    private javax.swing.JButton watchOnNetflixButton;
    // End of variables declaration//GEN-END:variables
}
