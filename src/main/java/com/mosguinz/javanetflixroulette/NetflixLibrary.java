/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;


import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mos
 */
public class NetflixLibrary {
    
    private final String X_RAPID_API_KEY;
    
    NetflixLibrary() {
        this.X_RAPID_API_KEY = getXRapidAPIKey();
    }
    
    private static String getXRapidAPIKey() {
        String key = null;
        
        try {
//            key = java.lang.System.getenv("X_RAPID_API_KEY");
              throw new NullPointerException();
        }
        catch(NullPointerException | SecurityException e) {
            key = JOptionPane.showInputDialog("Netflix library API could not be found or access in the environment variables. Please provide one here.");
        }
        finally {
            System.out.println(key);            
        }
        
        return key;
    }
    
    public ArrayList<NetflixTitle> fetchTitles() {
        // Request URLs with parameters to return all titles.
        String requestURL = "https://unogs-unogs-v1.p.mashape.com/aaapi.cgi?q=-!0,3000-!0,5-!,10-!0-!Any-!Any-!Any-!-!&t=ns&cl=23&st=adv&ob=Relevance&p=&sa=or";
 
        HttpResponse<JsonNode> response = Unirest.get(requestURL)
            .header("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com")
            .header("X-RapidAPI-Key", this.X_RAPID_API_KEY)
            .asJson();
        
        // Get the list of titles from the key "ITEMS" from the response body.
        JSONObject respObject = response.getBody().getObject();
        JSONArray returnedTitles = respObject.getJSONArray("ITEMS");
        
        // Create empty ArrayList to hold titles.
        ArrayList<NetflixTitle> netflixTitles = new ArrayList<>();
        
        // Turn each title with its attributes into an instance
        // of NetflixTitle.
        // TODO: handle NullPointers (with defaultValue arg doesn't seem to work?)
        for (int i = 0; i < returnedTitles.length(); i++) {
            JSONObject t = returnedTitles.getJSONObject(i);
            NetflixTitle title = new NetflixTitle(
                t.getString("netflixid"),
                t.getString("title"),
                t.getString("image"),
                t.getString("synopsis"),
                t.getString("rating"),
                t.getString("type"),
                t.getString("released"),
                t.getString("runtime")
            );
            
            // Append created title to ArrayList.
            netflixTitles.add(title);
            
        }
        
        return netflixTitles;
    }
    
    public static NetflixTitle selectRandomTitle(ArrayList<NetflixTitle> titles) {
        // Select a random title from the list of titles.
        Random r = new Random();
        int randomIndex = r.nextInt(titles.size());
        NetflixTitle selectedTitle = titles.get(randomIndex);
        
        return selectedTitle;
    }
    
    
    
}
