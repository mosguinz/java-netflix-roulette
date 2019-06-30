/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;


import java.util.ArrayList;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mos
 */
public class GetNetflixTitles {
    
    private final String X_RAPID_API_KEY;
    
    GetNetflixTitles() {
        this.X_RAPID_API_KEY = getXRapidAPIKey();
        this.getNetflixTitles();
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
    
    private ArrayList<NetflixTitle> getNetflixTitles() {
        // Request URLs with parameters to return all titles.
        String requestURL = "https://unogs-unogs-v1.p.mashape.com/aaapi.cgi?q=-!0,3000-!0,5-!,10-!0-!Any-!Any-!Any-!-!&t=ns&cl=23&st=adv&ob=Relevance&p=&sa=or";
 
        HttpResponse<JsonNode> response = Unirest.get(requestURL)
            .header("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com")
            .header("X-RapidAPI-Key", this.X_RAPID_API_KEY)
            .asJson();
        
        JSONObject respObject = response.getBody().getObject();
        JSONArray titles = respObject.getJSONArray("ITEMS");
        System.out.println(titles);
        
        
        
        
        
        
        return null;
    }
    
    
    
}
