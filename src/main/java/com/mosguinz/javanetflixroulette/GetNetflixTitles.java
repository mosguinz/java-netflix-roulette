/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

/**
 *
 * @author Mos
 */
public class GetNetflixTitles {
    
    private final String X_RAPID_API_KEY;
    
    GetNetflixTitles() {
        this.X_RAPID_API_KEY = getXRapidAPIKey();
    }
    
    private static String getXRapidAPIKey() {
        String key = null;
        
        try {
            key = java.lang.System.getenv("X_RAPID_API_KEY");
        }
        catch(NullPointerException e) {
            System.out.println("Key not found.");
        }
        catch(SecurityException e) {
            System.out.println("Permission required.");
        }
        finally {
            System.out.println(key);            
        }
        
        return key;
    }
    
    
    
}
