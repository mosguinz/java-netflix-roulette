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

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /**
     * The instance of the Netflix library.
     */
    private final NetflixLibrary netflixLibrary;

    /**
     * The instance of the dialog for displaying titles.
     */
    private final SelectedTitleGUI selectedTitleGUI = new SelectedTitleGUI();

    /**
     * The disclaimer as a HTML-formatted string.
     */
    private static final String DISCLAIMER = "<html>Data obtained about titles and catalogue are provided by a third-party API provider, Unofficial Netflix Online Global Search (“uNoGS”) via RapidAPI."
            + "<br><br>This application is not endorsed by, directly affiliated with, maintained, authorized, or sponsored by Netflix, Inc., uNoGS, or RapidAPI. All product names, trademarks, and registered trademarks are property of their respective owners. All company, product and service names used in this application are for identification purposes only. Use of these names, trademarks, and brands does not imply endorsement."
            + "<br><br>Poster images are owned by their respective publisher or creator of the work depicted. The images used are for demonstration purposes only and not solely for illustrations. These images are of low resolution and may qualify as fair use or fair dealing for educational purposes.</html>";

    /**
     * The license notice as a HTML-formatted string.
     */
    private static final String LICENSE = "<html>MIT License"
            + "<br><br>Copyright (c) 2019 mosguinz"
            + "<br><br>Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:"
            + "<br><br>The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software."
            + "<br><br>THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.</html>";

    /**
     * The array of checkboxes displayed for genres.
     */
    private final JCheckBox[] genreCheckBoxes;

    /**
     * Whether the given input is valid.
     */
    private boolean inputIsValid;

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

        settingsDialog = new javax.swing.JDialog();
        settingsHeader = new javax.swing.JLabel();
        localFilesLabel = new javax.swing.JLabel();
        localFilesDescription = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        locationValue = new javax.swing.JLabel();
        openFolderLocationButton = new javax.swing.JButton();
        responsesLabel = new javax.swing.JLabel();
        responsesValue = new javax.swing.JLabel();
        cacheSizeLabel = new javax.swing.JLabel();
        cacheSizeValue = new javax.swing.JLabel();
        clearLocalCacheButton = new javax.swing.JButton();
        settingsDialogOKButton = new javax.swing.JButton();
        aboutDialog = new javax.swing.JDialog();
        aboutHeader = new javax.swing.JLabel();
        disclaimerLabel = new javax.swing.JLabel();
        disclaimerInfo = new javax.swing.JLabel();
        LicenseLabel = new javax.swing.JLabel();
        licenseInfo = new javax.swing.JLabel();
        aboutDialogOKButton = new javax.swing.JButton();
        howToUseDialog = new javax.swing.JDialog();
        helpHeader = new javax.swing.JLabel();
        howToUseLabel = new javax.swing.JLabel();
        howToUseInfo = new javax.swing.JLabel();
        faqLabel1 = new javax.swing.JLabel();
        faqInfo1 = new javax.swing.JLabel();
        faqLabel2 = new javax.swing.JLabel();
        faqInfo2 = new javax.swing.JLabel();
        faqLabel3 = new javax.swing.JLabel();
        faqInfo3 = new javax.swing.JLabel();
        goToIssueTrackerButton = new javax.swing.JButton();
        faqLabel4 = new javax.swing.JLabel();
        faqInfo4 = new javax.swing.JLabel();
        goToRepoButton = new javax.swing.JButton();
        howToUseDialogOKButton = new javax.swing.JButton();
        typeButtonGroup = new javax.swing.ButtonGroup();
        titleLabel = new javax.swing.JLabel();
        regionSelectionLabel = new javax.swing.JLabel();
        regionSelectionMenu = new javax.swing.JComboBox<>();
        titleTypeSelectionLabel = new javax.swing.JLabel();
        titleTypeSelectionButtonArea = new javax.swing.JPanel();
        anyTitleTypeButton = new javax.swing.JRadioButton();
        moviesButton = new javax.swing.JRadioButton();
        seriesButton = new javax.swing.JRadioButton();
        RatingsSelectionLabel = new javax.swing.JLabel();
        ratingsSelectionArea = new javax.swing.JPanel();
        minimumRatingLabel = new javax.swing.JLabel();
        minimumRatingValueLabel = new javax.swing.JLabel();
        minimumRatingSlider = new javax.swing.JSlider();
        maximumRatingLabel = new javax.swing.JLabel();
        maximumRatingValueLabel = new javax.swing.JLabel();
        maximumRatingSlider = new javax.swing.JSlider();
        genreSelectionLabel = new javax.swing.JLabel();
        genreSelectionToggle = new javax.swing.JPanel();
        genreSelectAllButton = new javax.swing.JButton();
        genreClearSelectionButton = new javax.swing.JButton();
        genreCheckBoxArea = new javax.swing.JPanel();
        rollButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        settingsButton = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        HowToUseButton = new javax.swing.JMenuItem();
        AboutButton = new javax.swing.JMenuItem();

        settingsDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        settingsDialog.setTitle("Help");
        settingsDialog.setMinimumSize(new java.awt.Dimension(1100, 400));
        settingsDialog.setModal(true);
        settingsDialog.setPreferredSize(new java.awt.Dimension(1100, 400));
        settingsDialog.setResizable(false);
        settingsDialog.setType(java.awt.Window.Type.POPUP);
        settingsDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        settingsHeader.setFont(new java.awt.Font("Helvetica Neue World", 1, 24)); // NOI18N
        settingsHeader.setText("Settings");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        settingsDialog.getContentPane().add(settingsHeader, gridBagConstraints);

        localFilesLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        localFilesLabel.setText("Local files");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        settingsDialog.getContentPane().add(localFilesLabel, gridBagConstraints);

        localFilesDescription.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        localFilesDescription.setText("Parts of the Netflix catalogue are cached on this computer to deliver faster responses... and save some money while we're at it.");
        localFilesDescription.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        localFilesDescription.setMinimumSize(new java.awt.Dimension(500, 10));
        localFilesDescription.setPreferredSize(new java.awt.Dimension(500, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        settingsDialog.getContentPane().add(localFilesDescription, gridBagConstraints);

        locationLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 14)); // NOI18N
        locationLabel.setText("Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        settingsDialog.getContentPane().add(locationLabel, gridBagConstraints);

        locationValue.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        locationValue.setText("Getting folder location...");
        locationValue.setMinimumSize(new java.awt.Dimension(500, 10));
        locationValue.setPreferredSize(new java.awt.Dimension(500, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        settingsDialog.getContentPane().add(locationValue, gridBagConstraints);

        openFolderLocationButton.setText("Open folder location");
        openFolderLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFolderLocationButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 0, 0);
        settingsDialog.getContentPane().add(openFolderLocationButton, gridBagConstraints);

        responsesLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 14)); // NOI18N
        responsesLabel.setText("Responses");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        settingsDialog.getContentPane().add(responsesLabel, gridBagConstraints);

        responsesValue.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        responsesValue.setText("Calculating number of responses saved...");
        responsesValue.setMinimumSize(new java.awt.Dimension(500, 10));
        responsesValue.setPreferredSize(new java.awt.Dimension(500, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        settingsDialog.getContentPane().add(responsesValue, gridBagConstraints);

        cacheSizeLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 14)); // NOI18N
        cacheSizeLabel.setText("Cache size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        settingsDialog.getContentPane().add(cacheSizeLabel, gridBagConstraints);

        cacheSizeValue.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        cacheSizeValue.setText("Calculating cache size...");
        cacheSizeValue.setMinimumSize(new java.awt.Dimension(500, 10));
        cacheSizeValue.setPreferredSize(new java.awt.Dimension(500, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        settingsDialog.getContentPane().add(cacheSizeValue, gridBagConstraints);

        clearLocalCacheButton.setText("Clear local cache");
        clearLocalCacheButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLocalCacheButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 0, 0);
        settingsDialog.getContentPane().add(clearLocalCacheButton, gridBagConstraints);

        settingsDialogOKButton.setText("Close");
        settingsDialogOKButton.setToolTipText("");
        settingsDialogOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsDialogOKButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        settingsDialog.getContentPane().add(settingsDialogOKButton, gridBagConstraints);

        aboutDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        aboutDialog.setTitle("About Netflix roulette");
        aboutDialog.setMinimumSize(new java.awt.Dimension(1000, 900));
        aboutDialog.setModal(true);
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

        aboutDialogOKButton.setText("Close");
        aboutDialogOKButton.setToolTipText("");
        aboutDialogOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutDialogOKButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        aboutDialog.getContentPane().add(aboutDialogOKButton, gridBagConstraints);

        howToUseDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        howToUseDialog.setTitle("Help");
        howToUseDialog.setMinimumSize(new java.awt.Dimension(1000, 640));
        howToUseDialog.setModal(true);
        howToUseDialog.setResizable(false);
        howToUseDialog.setType(java.awt.Window.Type.POPUP);
        howToUseDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        helpHeader.setFont(new java.awt.Font("Helvetica Neue World", 1, 24)); // NOI18N
        helpHeader.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(helpHeader, gridBagConstraints);

        howToUseLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        howToUseLabel.setText("How to use");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(howToUseLabel, gridBagConstraints);

        howToUseInfo.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        howToUseInfo.setText("<html>Don't know what to watch tonight?<br>Simply choose your Netflix region and the genres that you are interested in and click \"Roll\" -- then we'll choose a title for you!</html>");
        howToUseInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        howToUseInfo.setMinimumSize(new java.awt.Dimension(500, 10));
        howToUseInfo.setPreferredSize(new java.awt.Dimension(500, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(howToUseInfo, gridBagConstraints);

        faqLabel1.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        faqLabel1.setText("I clicked on \"Watch on Netflix\" but nothing happened!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(faqLabel1, gridBagConstraints);

        faqInfo1.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        faqInfo1.setText("<html>Make sure to select the region that you are watching Netflix on before pressing \"Roll.\"<br>Due to licensing and copyright issues, not all titles on Netflix are available in every region. Choose the region that you are watching Netflix from to make sure that you are able to watch the titles that are selected!</html>");
        faqInfo1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        faqInfo1.setMinimumSize(new java.awt.Dimension(500, 35));
        faqInfo1.setName(""); // NOI18N
        faqInfo1.setPreferredSize(new java.awt.Dimension(500, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(faqInfo1, gridBagConstraints);

        faqLabel2.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        faqLabel2.setText("I got a \"No matching titles\" message");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(faqLabel2, gridBagConstraints);

        faqInfo2.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        faqInfo2.setText("<html>You must have a distinct taste!<br>This means that there were no titles on the Netflix catalogue that matches your filter. Try broadening your search by including more genres.</html>");
        faqInfo2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        faqInfo2.setMinimumSize(new java.awt.Dimension(500, 10));
        faqInfo2.setPreferredSize(new java.awt.Dimension(500, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(faqInfo2, gridBagConstraints);

        faqLabel3.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        faqLabel3.setText("Something doesn't look right...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(faqLabel3, gridBagConstraints);

        faqInfo3.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        faqInfo3.setText("<html>Notice something strange, think something is broken, or got a suggestion in mind? Create an issue on our issue tracker and let us know!");
        faqInfo3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        faqInfo3.setMinimumSize(new java.awt.Dimension(500, 0));
        faqInfo3.setPreferredSize(new java.awt.Dimension(500, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(faqInfo3, gridBagConstraints);

        goToIssueTrackerButton.setText("Go to issue tracker");
        goToIssueTrackerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToIssueTrackerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        howToUseDialog.getContentPane().add(goToIssueTrackerButton, gridBagConstraints);

        faqLabel4.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        faqLabel4.setText("<html>Where can I find your source code, so I can <i>finally</i> mark it?</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(faqLabel4, gridBagConstraints);

        faqInfo4.setFont(new java.awt.Font("Helvetica Neue World", 0, 13)); // NOI18N
        faqInfo4.setText("<html>Find the source code to this project at <a href=\"https://github.com/mosguinz/java-netflix-roulette\">github.com/mosguinz/java-netflix-roulette</a>.");
        faqInfo4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        faqInfo4.setMinimumSize(new java.awt.Dimension(500, 0));
        faqInfo4.setPreferredSize(new java.awt.Dimension(500, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 410;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        howToUseDialog.getContentPane().add(faqInfo4, gridBagConstraints);

        goToRepoButton.setText("Go to GitHub repo");
        goToRepoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToRepoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        howToUseDialog.getContentPane().add(goToRepoButton, gridBagConstraints);

        howToUseDialogOKButton.setText("Close");
        howToUseDialogOKButton.setToolTipText("");
        howToUseDialogOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                howToUseDialogOKButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        howToUseDialog.getContentPane().add(howToUseDialogOKButton, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Netflix roulette");
        setMinimumSize(new java.awt.Dimension(1000, 650));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

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

        regionSelectionLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        regionSelectionLabel.setText("Netflix region");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        getContentPane().add(regionSelectionLabel, gridBagConstraints);

        regionSelectionMenu.setModel(getRegionNames());
        regionSelectionMenu.setToolTipText("<html>Select your Netflix region<br>\nSee the help menu (F1) for more info");
        regionSelectionMenu.setMinimumSize(new java.awt.Dimension(200, 32));
        regionSelectionMenu.setPreferredSize(new java.awt.Dimension(200, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        getContentPane().add(regionSelectionMenu, gridBagConstraints);

        titleTypeSelectionLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        titleTypeSelectionLabel.setText("Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 5, 0);
        getContentPane().add(titleTypeSelectionLabel, gridBagConstraints);

        titleTypeSelectionButtonArea.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        typeButtonGroup.add(anyTitleTypeButton);
        anyTitleTypeButton.setSelected(true);
        anyTitleTypeButton.setText("Any");
        anyTitleTypeButton.setToolTipText("Include any type of titles");
        anyTitleTypeButton.setActionCommand("Any");
        titleTypeSelectionButtonArea.add(anyTitleTypeButton);

        typeButtonGroup.add(moviesButton);
        moviesButton.setText("Movie");
        moviesButton.setToolTipText("Only include titles that are movies");
        moviesButton.setActionCommand("Movie");
        titleTypeSelectionButtonArea.add(moviesButton);

        typeButtonGroup.add(seriesButton);
        seriesButton.setText("Series");
        seriesButton.setToolTipText("Only include titles that are series");
        seriesButton.setActionCommand("Series");
        titleTypeSelectionButtonArea.add(seriesButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        getContentPane().add(titleTypeSelectionButtonArea, gridBagConstraints);

        RatingsSelectionLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        RatingsSelectionLabel.setText("Viewer ratings");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 5, 0);
        getContentPane().add(RatingsSelectionLabel, gridBagConstraints);

        ratingsSelectionArea.setLayout(new java.awt.GridBagLayout());

        minimumRatingLabel.setFont(new java.awt.Font("Helvetica Neue World", 0, 14)); // NOI18N
        minimumRatingLabel.setText("Minimum");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        ratingsSelectionArea.add(minimumRatingLabel, gridBagConstraints);

        minimumRatingValueLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 24)); // NOI18N
        minimumRatingValueLabel.setText("0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ratingsSelectionArea.add(minimumRatingValueLabel, gridBagConstraints);

        minimumRatingSlider.setMajorTickSpacing(50);
        minimumRatingSlider.setMinorTickSpacing(10);
        minimumRatingSlider.setPaintTicks(true);
        minimumRatingSlider.setValue(0);
        minimumRatingSlider.setMaximumSize(new java.awt.Dimension(200, 36));
        minimumRatingSlider.setMinimumSize(new java.awt.Dimension(200, 36));
        minimumRatingSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                minimumRatingSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        ratingsSelectionArea.add(minimumRatingSlider, gridBagConstraints);

        maximumRatingLabel.setFont(new java.awt.Font("Helvetica Neue World", 0, 14)); // NOI18N
        maximumRatingLabel.setText("Maximum");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        ratingsSelectionArea.add(maximumRatingLabel, gridBagConstraints);

        maximumRatingValueLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 24)); // NOI18N
        maximumRatingValueLabel.setText("10.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ratingsSelectionArea.add(maximumRatingValueLabel, gridBagConstraints);

        maximumRatingSlider.setMajorTickSpacing(50);
        maximumRatingSlider.setMinorTickSpacing(10);
        maximumRatingSlider.setPaintTicks(true);
        maximumRatingSlider.setValue(100);
        maximumRatingSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                maximumRatingSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        ratingsSelectionArea.add(maximumRatingSlider, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        getContentPane().add(ratingsSelectionArea, gridBagConstraints);

        genreSelectionLabel.setFont(new java.awt.Font("Helvetica Neue World", 1, 18)); // NOI18N
        genreSelectionLabel.setText("Genres");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 5, 0);
        getContentPane().add(genreSelectionLabel, gridBagConstraints);

        genreSelectionToggle.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        genreSelectAllButton.setText("Select all");
        genreSelectAllButton.setToolTipText("Select all available genres");
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
        genreClearSelectionButton.setToolTipText("<html>Unselect all available genres<br>Helpful when you're looking for specific genres");
        genreClearSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreClearSelectionButtonActionPerformed(evt);
            }
        });
        genreSelectionToggle.add(genreClearSelectionButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        getContentPane().add(genreSelectionToggle, gridBagConstraints);

        genreCheckBoxArea.setAlignmentX(0.0F);
        genreCheckBoxArea.setAlignmentY(0.0F);
        genreCheckBoxArea.setMinimumSize(new java.awt.Dimension(850, 150));
        genreCheckBoxArea.setName(""); // NOI18N
        genreCheckBoxArea.setPreferredSize(new java.awt.Dimension(850, 150));
        genreCheckBoxArea.setLayout(new java.awt.GridLayout(5, 4, -2, -2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        getContentPane().add(genreCheckBoxArea, gridBagConstraints);

        rollButton.setText("Roll");
        rollButton.setMaximumSize(new java.awt.Dimension(100, 35));
        rollButton.setMinimumSize(new java.awt.Dimension(100, 35));
        rollButton.setPreferredSize(new java.awt.Dimension(100, 35));
        rollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 0);
        getContentPane().add(rollButton, gridBagConstraints);

        fileMenu.setText("File");

        settingsButton.setText("Settings");
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        fileMenu.add(settingsButton);

        menuBar.add(fileMenu);

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

        menuBar.add(HelpMenu);

        setJMenuBar(menuBar);

        setSize(new java.awt.Dimension(1018, 697));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollButtonActionPerformed
        LOGGER.log(Level.FINE, "Roll button pressed");
        rollButton.setText("Rolling...");
        rollButton.setEnabled(false);
        rollButton.paintImmediately(rollButton.getVisibleRect());
        setQueryValues();

        if (inputIsValid) {
            getNetflixTitle();
        }

        rollButton.setText("Roll");
        rollButton.setEnabled(true);
    }//GEN-LAST:event_rollButtonActionPerformed

    private void HowToUseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HowToUseButtonActionPerformed
        howToUseDialog.setLocationRelativeTo(null);
        howToUseDialog.setVisible(true);
    }//GEN-LAST:event_HowToUseButtonActionPerformed

    private void AboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutButtonActionPerformed
        aboutDialog.setLocationRelativeTo(null);
        aboutDialog.setVisible(true);
    }//GEN-LAST:event_AboutButtonActionPerformed

    private void aboutDialogOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutDialogOKButtonActionPerformed
        aboutDialog.dispose();
    }//GEN-LAST:event_aboutDialogOKButtonActionPerformed

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

    private void settingsDialogOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsDialogOKButtonActionPerformed
        settingsDialog.dispose();
    }//GEN-LAST:event_settingsDialogOKButtonActionPerformed

    private void minimumRatingSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_minimumRatingSliderStateChanged
        minimumRatingValueLabel.setText(String.format("%.1f", (float) minimumRatingSlider.getValue() / 10));
    }//GEN-LAST:event_minimumRatingSliderStateChanged

    private void maximumRatingSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_maximumRatingSliderStateChanged
        maximumRatingValueLabel.setText(String.format("%.1f", (float) maximumRatingSlider.getValue() / 10));
    }//GEN-LAST:event_maximumRatingSliderStateChanged

    private void goToIssueTrackerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToIssueTrackerButtonActionPerformed
        try {
            LOGGER.log(Level.FINE, "Opening URL in browser...");
            Desktop.getDesktop().browse(new URL("https://github.com/mosguinz/java-netflix-roulette/issues").toURI());
        } catch (IOException | URISyntaxException e) {
            LoggingUtil.logException(LOGGER, e, "Something went wrong. Could not open broswer.");
        }
    }//GEN-LAST:event_goToIssueTrackerButtonActionPerformed

    private void goToRepoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToRepoButtonActionPerformed
        try {
            LOGGER.log(Level.FINE, "Opening URL in browser...");
            Desktop.getDesktop().browse(new URL("https://github.com/mosguinz/java-netflix-roulette").toURI());
        } catch (IOException | URISyntaxException e) {
            LoggingUtil.logException(LOGGER, e, "Something went wrong. Could not open broswer.");
        }
    }//GEN-LAST:event_goToRepoButtonActionPerformed

    private void howToUseDialogOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howToUseDialogOKButtonActionPerformed
        howToUseDialog.dispose();
    }//GEN-LAST:event_howToUseDialogOKButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        settingsDialog.setLocationRelativeTo(null);
        updateLocalLibraryMetrics();
        settingsDialog.setVisible(true);
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void clearLocalCacheButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLocalCacheButtonActionPerformed
        LocalLibrary.clearLibraryFolder();
        updateLocalLibraryMetrics();
    }//GEN-LAST:event_clearLocalCacheButtonActionPerformed

    private void openFolderLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFolderLocationButtonActionPerformed
        try {
            Desktop.getDesktop().open(LocalLibrary.getLibraryPath());
        } catch (IOException e) {
            LoggingUtil.logException(LOGGER, e);
            displayErrorMessage("Uh oh, something went wrong", "Could not open library folder.", e);
        }
    }//GEN-LAST:event_openFolderLocationButtonActionPerformed

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

        return selectedGenres;
    }

    /**
     * Get the selected type from the radio buttons.
     *
     * @return a {@link String} that is the selected type; it will be either:
     * {@code Any}, {@code Movie}, or {@code Series}
     */
    private String getSelectedTitleType() {
        return typeButtonGroup.getSelection().getActionCommand();
    }

    /**
     * Get the selected value for the minimum rating.
     *
     * @return a {@link String} that is the selected value to one decimal point
     * precision
     */
    private String getSelectedMinimumRatingValue() {
        return minimumRatingValueLabel.getText();
    }

    /**
     * Get the selected value for the maximum rating.
     *
     * @return a {@link String} that is the selected value to one decimal point
     * precision
     */
    private String getSelectedMaximumRatingValue() {
        return maximumRatingValueLabel.getText();
    }

    /**
     * Verify if the provided genre selection is valid.
     *
     * @return {@code true} if valid; {@code false} otherwise
     */
    private boolean genreSelectionIsValid(ArrayList<String> selectedGenres) {
        if (selectedGenres.isEmpty()) {
            displayErrorMessage("Please select at least one genre.", "Invalid input");
            return false;
        }
        return true;
    }

    /**
     * Verify if the provided input for ratings are valid.
     *
     * @return {@code true} if valid; {@code false} otherwise
     */
    private boolean ratingValuesIsValid() {
        if (minimumRatingSlider.getValue() > maximumRatingSlider.getValue()) {
            displayErrorMessage("The minimum rating value must be less than the maximum rating value.",
                    "Invalid input");
            return false;
        }
        return true;
    }

    /**
     * Set the selected values and set them as the query parameters.
     */
    private void setQueryValues() {

        netflixLibrary.setQueryRegion(getSelectedRegion());
        ArrayList<String> g = getSelectedGenres();
        netflixLibrary.setQueryGenres(g);
        netflixLibrary.setQueryTitleType(getSelectedTitleType());
        netflixLibrary.setQueryMinimumRating(getSelectedMinimumRatingValue());
        netflixLibrary.setQueryMaximumRating(getSelectedMaximumRatingValue());

        // Check if the values are valid.
        if (ratingValuesIsValid() && genreSelectionIsValid(g)) {
            inputIsValid = true;
        } else {
            inputIsValid = false;
        }

    }

    /**
     * Get Netflix title with the selected values.
     */
    public void getNetflixTitle() {
        JSONArray netflixTitles;
        netflixTitles = netflixLibrary.fetchTitles();

        // Pick a title and display it.
        if (netflixTitles != null) {
            JSONObject selectedTitle = NetflixLibrary.selectRandomTitle(netflixTitles);
            selectedTitleGUI.setParentFrame(this);
            selectedTitleGUI.updateTitleInfo(selectedTitle);
        }
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
     * Update metrics of the local library.
     * <p>
     * These metrics include file count, folder size, and its location. These
     * are displayed in the settings dialog.
     */
    private void updateLocalLibraryMetrics() {
        File libraryPath = LocalLibrary.getLibraryPath();
        locationValue.setText(libraryPath.getAbsolutePath());
        responsesValue.setText(String.valueOf(LocalLibrary.getLibraryFileCount()));
        cacheSizeValue.setText(String.format("%.2f MB", LocalLibrary.getLibraryFolderSize() / 1e+6));
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
                if ("Windows".equals(info.getName())) {
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
        java.awt.EventQueue.invokeLater(() -> {
            new HomeGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AboutButton;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JMenuItem HowToUseButton;
    private javax.swing.JLabel LicenseLabel;
    private javax.swing.JLabel RatingsSelectionLabel;
    private javax.swing.JDialog aboutDialog;
    private javax.swing.JButton aboutDialogOKButton;
    private javax.swing.JLabel aboutHeader;
    private javax.swing.JRadioButton anyTitleTypeButton;
    private javax.swing.JLabel cacheSizeLabel;
    private javax.swing.JLabel cacheSizeValue;
    private javax.swing.JButton clearLocalCacheButton;
    private javax.swing.JLabel disclaimerInfo;
    private javax.swing.JLabel disclaimerLabel;
    private javax.swing.JLabel faqInfo1;
    private javax.swing.JLabel faqInfo2;
    private javax.swing.JLabel faqInfo3;
    private javax.swing.JLabel faqInfo4;
    private javax.swing.JLabel faqLabel1;
    private javax.swing.JLabel faqLabel2;
    private javax.swing.JLabel faqLabel3;
    private javax.swing.JLabel faqLabel4;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel genreCheckBoxArea;
    private javax.swing.JButton genreClearSelectionButton;
    private javax.swing.JButton genreSelectAllButton;
    private javax.swing.JLabel genreSelectionLabel;
    private javax.swing.JPanel genreSelectionToggle;
    private javax.swing.JButton goToIssueTrackerButton;
    private javax.swing.JButton goToRepoButton;
    private javax.swing.JLabel helpHeader;
    private javax.swing.JDialog howToUseDialog;
    private javax.swing.JButton howToUseDialogOKButton;
    private javax.swing.JLabel howToUseInfo;
    private javax.swing.JLabel howToUseLabel;
    private javax.swing.JLabel licenseInfo;
    private javax.swing.JLabel localFilesDescription;
    private javax.swing.JLabel localFilesLabel;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel locationValue;
    private javax.swing.JLabel maximumRatingLabel;
    private javax.swing.JSlider maximumRatingSlider;
    private javax.swing.JLabel maximumRatingValueLabel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel minimumRatingLabel;
    private javax.swing.JSlider minimumRatingSlider;
    private javax.swing.JLabel minimumRatingValueLabel;
    private javax.swing.JRadioButton moviesButton;
    private javax.swing.JButton openFolderLocationButton;
    private javax.swing.JPanel ratingsSelectionArea;
    private javax.swing.JLabel regionSelectionLabel;
    private javax.swing.JComboBox<String> regionSelectionMenu;
    private javax.swing.JLabel responsesLabel;
    private javax.swing.JLabel responsesValue;
    private javax.swing.JButton rollButton;
    private javax.swing.JRadioButton seriesButton;
    private javax.swing.JMenuItem settingsButton;
    private javax.swing.JDialog settingsDialog;
    private javax.swing.JButton settingsDialogOKButton;
    private javax.swing.JLabel settingsHeader;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titleTypeSelectionButtonArea;
    private javax.swing.JLabel titleTypeSelectionLabel;
    private javax.swing.ButtonGroup typeButtonGroup;
    // End of variables declaration//GEN-END:variables
}
