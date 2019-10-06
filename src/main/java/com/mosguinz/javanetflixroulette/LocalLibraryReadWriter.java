/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Mos
 */
public class LocalLibraryReadWriter {
    
    private final String HOME_PATH = getHomePath();
    private final String LIBRARY_PATH = getLibraryPath();
    
    /**
     * Get the user's home directory.
     * @return The location of the user's home directory
     */
    private static String getHomePath() {
        return System.getProperty("user.home");
    }

    /**
     * Get the home directory for this application.
     * @return The location of this application's home directory
     */
    private String getLibraryPath() {
        return HOME_PATH + File.separator + "netflixRoulette";
    }
    
    /**
     * Create the library directory.
     * @return true if and only if the directory was created; false otherwise
     */
    public boolean makeLibraryDirectory() {        
        File f = new File(LIBRARY_PATH);
        return f.mkdirs();
    }
    
    /**
     * Save the response of Netflix titles
     * @param titles JSONArray of the returned titles
     * @return true if and only if the titles were saved; false otherwise
     */
    public boolean saveTitles(JSONObject titles) {
        FileOutputStream stream;
        boolean saved = true;
        
        try {
            System.out.print(titles);
            stream = new FileOutputStream(LIBRARY_PATH + File.separator + "test.json");
            byte[] b = titles.toString(2).getBytes();
            stream.write(b);
            stream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocalLibraryReadWriter.class.getName()).log(Level.SEVERE, null, ex);
            saved = false;
        } catch (IOException ex) {
            Logger.getLogger(LocalLibraryReadWriter.class.getName()).log(Level.SEVERE, null, ex);
            saved = false;
        }
        
        return saved;
    }
}