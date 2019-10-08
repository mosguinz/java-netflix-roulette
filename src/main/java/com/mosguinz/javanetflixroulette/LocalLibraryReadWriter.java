/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Mos
 */
public class LocalLibraryReadWriter {

    private static final Logger LOGGER = Logger.getLogger(LocalLibraryReadWriter.class.getName());

    private final String HOME_PATH = getHomePath();
    private final String LIBRARY_PATH = getLibraryPath();

    /**
     * Get the user's home directory.
     *
     * @return The location of the user's home directory
     */
    private static String getHomePath() {
        LOGGER.log(Level.FINE, "Getting user's home path...");
        return System.getProperty("user.home");
    }

    /**
     * Get the home directory for this application.
     *
     * @return The location of this application's home directory
     */
    private String getLibraryPath() {
        LOGGER.log(Level.FINE, "Creating the path for this library...");
        return HOME_PATH + File.separator + "netflixRoulette";
    }

    /**
     * Create the library directory.
     *
     * @return true if and only if the directory was created; false otherwise
     */
    public boolean makeLibraryDirectory() {
        LOGGER.log(Level.INFO, "Creating the library directory at: {}", LIBRARY_PATH);
        File f = new File(LIBRARY_PATH);
        return f.mkdirs();
    }

    /**
     * Save the response of Netflix titles.
     *
     * @param titles JSONArray of the returned titles
     * @return true if and only if the titles were saved; false otherwise
     */
    public boolean saveTitles(JSONObject titles) {
        LOGGER.log(Level.INFO, "Writing the returned Netflix titles as a JSON");
        FileOutputStream stream;
        boolean saved = true;

        try {
            LOGGER.log(Level.FINE, "Creating file output stream at {0}", LIBRARY_PATH);
            stream = new FileOutputStream(LIBRARY_PATH + File.separator + "test.json");

            LOGGER.log(Level.FINE, "Pretty printing JSON response...");
            byte[] b = titles.toString(2).getBytes();

            LOGGER.log(Level.INFO, "Saving {0} titles...", titles.getJSONArray("ITEMS").length());
            stream.write(b);

            LOGGER.log(Level.FINE, "Closing file stream...");
            stream.close();
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "{0}\n{1}", new Object[]{e.toString(), e});
            saved = false;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "{0}\n{1}", new Object[]{e.toString(), e});
            saved = false;
        }

        if (saved) {
            LOGGER.log(Level.INFO, "Response sucessfully written");
        } else {
            LOGGER.log(Level.WARNING, "Failed to write the response, check exception");
        }

        return saved;
    }
}
