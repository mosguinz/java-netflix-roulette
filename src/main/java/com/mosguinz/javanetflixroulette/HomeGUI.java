/*
 * The MIT License
 *
 * Copyright 2019 mosguinz.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mosguinz.javanetflixroulette;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * GUI for the home menu. This is where the application will start.
 *
 * @author mosguinz
 */
public class HomeGUI extends javax.swing.JFrame {

    /**
     * The {@link Logger} object for the class.
     */
    private static final Logger LOGGER = Logger.getLogger(HomeGUI.class.getName());

    private final NetflixLibrary netflixLibrary;
    private final SelectedTitleGUI selectedTitleGUI = new SelectedTitleGUI();

    private static final String DISCLAIMER = "<html>Data obtained about titles and catalogue are provided by a third-party API provider, Unofficial Netflix Online Global Search (“uNoGS”) via RapidAPI."
            + "<br><br>This application is not endorsed by, directly affiliated with, maintained, authorized, or sponsored by Netflix, Inc., uNoGS, or RapidAPI. All product names, trademarks, and registered trademarks are property of their respective owners. All company, product and service names used in this application are for identification purposes only. Use of these names, trademarks, and brands does not imply endorsement."
            + "<br><br>Poster images are owned by their respective publisher or creator of the work depicted. The images used are for demonstration purposes only and not solely for illustrations. These images are of low resolution and may qualify as fair use or fair dealing for educational purposes.</html>";

    private static final String LICENSE = "<html>MIT License"
            + "<br><br>Copyright (c) 2019 mosguinz"
            + "<br><br>Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:"
            + "<br><br>The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software."
            + "<br><br>THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.</html>";

    private JCheckBox[] genreCheckBoxes;

    /**
     * Creates new form {@link HomeGUI}.
     */
    public HomeGUI() {
        LoggingUtil.setupLogger(LOGGER);

        netflixLibrary = new NetflixLibrary();
        initComponents();
        genreCheckBoxes = generateGenreCheckBoxes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        aboutDialog = new javax.swing.JDialog();
        aboutHeader = new javax.swing.JLabel();
        licenseInfo = new javax.swing.JLabel();
        disclaimerLabel = new javax.swing.JLabel();
        disclaimerInfo = new javax.swing.JLabel();
        LicenseLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        regionSelectionLabel = new javax.swing.JLabel();
        rollButton = new javax.swing.JButton();
        regionSelectionMenu = new javax.swing.JComboBox<>();
        titleLabel = new javax.swing.JLabel();
        regionSelectionLabel1 = new javax.swing.JLabel();
        genreCheckBoxArea = new javax.swing.JPanel();
        genreSelectionToggle = new javax.swing.JPanel();
        genreSelectAllButton = new javax.swing.JButton();
        genreClearSelectionButton = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        SettingsButton = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        HowToUseButton = new javax.swing.JMenuItem();
        AboutButton = new javax.swing.JMenuItem();

        aboutDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        aboutDialog.setTitle("About Netflix roulette");
        aboutDialog.setMinimumSize(new java.awt.Dimension(1000, 900));
        aboutDialog.setType(java.awt.Window.Type.POPUP);
        aboutDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        aboutHeader.setFont(new java.awt.Font("Helvetica Neue World", 1, 24)); // NOI18N
        aboutHeader.setText("About");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        aboutDialog.getContentPane().add(aboutHeader, gridBagConstraints);

        licenseInfo.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        licenseInfo.setText(LICENSE);
        licenseInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        licenseInfo.setMaximumSize(new java.awt.Dimension(350, 300));
        licenseInfo.setMinimumSize(new java.awt.Dimension(500, 350));
        licenseInfo.setPreferredSize(new java.awt.Dimension(500, 3500));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        aboutDialog.getContentPane().add(licenseInfo, gridBagConstraints);

        disclaimerLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        disclaimerLabel.setText("Disclaimer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        aboutDialog.getContentPane().add(disclaimerLabel, gridBagConstraints);

        disclaimerInfo.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        disclaimerInfo.setText(DISCLAIMER);
        disclaimerInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        disclaimerInfo.setMinimumSize(new java.awt.Dimension(500, 200));
        disclaimerInfo.setPreferredSize(new java.awt.Dimension(500, 200));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        aboutDialog.getContentPane().add(disclaimerInfo, gridBagConstraints);

        LicenseLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        LicenseLabel.setText("License");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        aboutDialog.getContentPane().add(LicenseLabel, gridBagConstraints);

        jButton1.setText("Close");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        aboutDialog.getContentPane().add(jButton1, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Netflix roulette");
        setMinimumSize(new java.awt.Dimension(1000, 650));
        setPreferredSize(new java.awt.Dimension(1000, 650));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        regionSelectionLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 14)); // NOI18N
        regionSelectionLabel.setText("Genres");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        getContentPane().add(regionSelectionLabel, gridBagConstraints);

        rollButton.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        rollButton.setText("Roll");
        rollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 0);
        getContentPane().add(rollButton, gridBagConstraints);

        regionSelectionMenu.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        regionSelectionMenu.setModel(getRegionNames());
        regionSelectionMenu.setToolTipText("Select your Netflix region");
        regionSelectionMenu.setMinimumSize(new java.awt.Dimension(200, 32));
        regionSelectionMenu.setPreferredSize(new java.awt.Dimension(200, 32));
        regionSelectionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regionSelectionMenuActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        getContentPane().add(regionSelectionMenu, gridBagConstraints);

        titleLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 24)); // NOI18N
        titleLabel.setText("Netflix roulette");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        getContentPane().add(titleLabel, gridBagConstraints);

        regionSelectionLabel1.setFont(new java.awt.Font("Helvetica Neue World", 1, 14)); // NOI18N
        regionSelectionLabel1.setText("Netflix region");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        getContentPane().add(regionSelectionLabel1, gridBagConstraints);

        genreCheckBoxArea.setAlignmentX(0.0F);
        genreCheckBoxArea.setAlignmentY(0.0F);
        genreCheckBoxArea.setMinimumSize(new java.awt.Dimension(850, 150));
        genreCheckBoxArea.setName(""); // NOI18N
        genreCheckBoxArea.setPreferredSize(new java.awt.Dimension(850, 150));
        genreCheckBoxArea.setLayout(new java.awt.GridLayout(5, 4, -2, -2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        getContentPane().add(genreCheckBoxArea, gridBagConstraints);

        genreSelectionToggle.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        genreSelectAllButton.setText("Select all");
        genreSelectAllButton.setMaximumSize(new java.awt.Dimension(117, 25));
        genreSelectAllButton.setMinimumSize(new java.awt.Dimension(117, 25));
        genreSelectAllButton.setOpaque(false);
        genreSelectAllButton.setPreferredSize(new java.awt.Dimension(117, 25));
        genreSelectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreSelectAllButtonActionPerformed(evt);
            }
        });
        genreSelectionToggle.add(genreSelectAllButton);

        genreClearSelectionButton.setText("Clear selection");
        genreClearSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreClearSelectionButtonActionPerformed(evt);
            }
        });
        genreSelectionToggle.add(genreClearSelectionButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        getContentPane().add(genreSelectionToggle, gridBagConstraints);

        FileMenu.setText("File");

        SettingsButton.setText("Settings");
        SettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsButtonActionPerformed(evt);
            }
        });
        FileMenu.add(SettingsButton);

        MenuBar.add(FileMenu);

        HelpMenu.setText("Help");

        HowToUseButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        HowToUseButton.setText("How to use");
        HowToUseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HowToUseButtonActionPerformed(evt);
            }
        });
        HelpMenu.add(HowToUseButton);

        AboutButton.setText("About");
        AboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutButtonActionPerformed(evt);
            }
        });
        HelpMenu.add(AboutButton);

        MenuBar.add(HelpMenu);

        setJMenuBar(MenuBar);

        setSize(new java.awt.Dimension(1018, 697));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollButtonActionPerformed
        LOGGER.log(Level.FINE, "Roll button pressed");

        // Grab the selected Netflix region.
        JSONArray netflixTitles = null;
        netflixLibrary.setQueryRegion(getSelectedRegion());
        netflixLibrary.setQueryGenres(getSelectedGenres());

        // Grab the titles for this region.
        netflixTitles = netflixLibrary.fetchTitles();

        // Pick a title and display it.
        if (netflixTitles != null) {
            JSONObject selectedTitle = NetflixLibrary.selectRandomTitle(netflixTitles);
            selectedTitleGUI.updateTitleInfo(selectedTitle);
        }
    }//GEN-LAST:event_rollButtonActionPerformed

    private void HowToUseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HowToUseButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HowToUseButtonActionPerformed

    private void SettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SettingsButtonActionPerformed

    private void AboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutButtonActionPerformed
        aboutDialog.setVisible(true);
        aboutDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_AboutButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        aboutDialog.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void regionSelectionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regionSelectionMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regionSelectionMenuActionPerformed

    private void genreClearSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreClearSelectionButtonActionPerformed
        for (JCheckBox genre : genreCheckBoxes) {
            genre.setSelected(false);
        }
    }//GEN-LAST:event_genreClearSelectionButtonActionPerformed

    private void genreSelectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreSelectAllButtonActionPerformed
        for (JCheckBox genre : genreCheckBoxes) {
            genre.setSelected(true);
        }
    }//GEN-LAST:event_genreSelectAllButtonActionPerformed

    /**
     * Bring up a dialog that displays an error message.
     *
     * @param message the message to display
     * @param title the title string for the dialog
     * @param e the Exception message to display
     */
    public static void displayErrorMessage(String message, String title, Exception e) {
        if (e != null) {
            message = message + "\n\nError message:\n" + e;
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Bring up a dialog that displays an error message.
     *
     * @param message the message to display
     * @param title the title string for the dialog
     */
    public static void displayErrorMessage(String message, String title) {
        displayErrorMessage(message, title, null);
    }

    /**
     * Get the selected Netflix region.
     *
     * @return the name of the selected region as a {@code String}
     */
    private String getSelectedRegion() {
        return String.valueOf(regionSelectionMenu.getSelectedItem());
    }

    /**
     * Get a list of available Netflix regions to display.
     *
     * @return a {@link ComboBoxModel} for the drop-down list
     */
    private ComboBoxModel getRegionNames() {
        return new DefaultComboBoxModel(netflixLibrary.getAvailableRegionsList());
    }

    /**
     * Get the selected genres.
     *
     * @return the list of the selected genres as an {@link ArrayList}
     */
    private ArrayList<String> getSelectedGenres() {
        ArrayList<String> selectedGenres = new ArrayList<>();
        for (JCheckBox genre : genreCheckBoxes) {
            if (genre.isSelected()) {
                selectedGenres.add(genre.getText());
            }
        }

        if (selectedGenres.size() == 0) {
            displayErrorMessage("Please select at least one genre.", "Invalid input");
            return null;
        }

        return selectedGenres;
    }

    /**
     * Generate check-boxes for the list of available genres.
     *
     * @return a {@link JCheckBox} array of the genre check-boxes instance.
     */
    private JCheckBox[] generateGenreCheckBoxes() {
        ArrayList<String> genres = netflixLibrary.getAvailableGenresList();
        int genresSize = genres.size();

        JCheckBox[] jCheckBoxArray = new javax.swing.JCheckBox[genresSize];
        for (int x = 0; x < genresSize; x++) {
            jCheckBoxArray[x] = new javax.swing.JCheckBox();
            jCheckBoxArray[x].setText(genres.get(x));
            jCheckBoxArray[x].setSelected(true);
            genreCheckBoxArea.add(jCheckBoxArray[x]);

        }

        return jCheckBoxArray;
    }

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
    private javax.swing.JMenuItem AboutButton;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JMenuItem HowToUseButton;
    private javax.swing.JLabel LicenseLabel;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem SettingsButton;
    private javax.swing.JDialog aboutDialog;
    private javax.swing.JLabel aboutHeader;
    private javax.swing.JLabel disclaimerInfo;
    private javax.swing.JLabel disclaimerLabel;
    private javax.swing.JPanel genreCheckBoxArea;
    private javax.swing.JButton genreClearSelectionButton;
    private javax.swing.JButton genreSelectAllButton;
    private javax.swing.JPanel genreSelectionToggle;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel licenseInfo;
    private javax.swing.JLabel regionSelectionLabel;
    private javax.swing.JLabel regionSelectionLabel1;
    private javax.swing.JComboBox<String> regionSelectionMenu;
    private javax.swing.JButton rollButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
