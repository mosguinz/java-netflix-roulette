/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

import java.io.File;

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
}
