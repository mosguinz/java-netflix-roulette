/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

import java.util.Random;
import javax.swing.JOptionPane;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mos
 */
public class NetflixLibrary {

    private String X_RAPID_API_KEY;
    private final LocalLibraryReadWriter libWriter = new LocalLibraryReadWriter();

    NetflixLibrary() {
        this.X_RAPID_API_KEY = getXRapidAPIKey();
    }

    private static String getXRapidAPIKey() {
        String key = null;

        try {
            key = java.lang.System.getenv("X_RAPID_API_KEY");
        } catch (NullPointerException | SecurityException e) {
            key = requestUserAPIKey("Netflix library API could not be found or access in the environment variables. Please provide one here.",
                    "Netflix API key not found");
        } finally {
            System.out.println(key);
        }

        return key;
    }

    private static String requestUserAPIKey(String message, String title) {
        String key = null;

        while (key == null || key.isEmpty()) {
            key = JOptionPane.showInputDialog(null, message, title, JOptionPane.ERROR_MESSAGE);

            if (key != null) {
                key = key.replaceAll("\\s+", "");
            }
        }

        return key;
    }

    public JSONArray fetchTitles() {
        JSONArray returnedTitles = sendQuery("fetchTitles");

        return returnedTitles;
    }

    public JSONArray sendQuery(String requestType) {
        String requestURL = getEndpoint(requestType);
        JSONArray responseArray;
        JSONObject response = null;

        // Send the query.
        try {
            response = Unirest.get(requestURL)
                    .header("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", this.X_RAPID_API_KEY)
                    .asJson()
                    .getBody()
                    .getObject();
        } catch (Exception e) {
            // Temp fix for SSL handshake errors
            // TODO: Create proper popups to handle this and other connection errors.
            JOptionPane.showMessageDialog(null, "There was an error contacting Netflix library. Please try again.\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            libWriter.saveTitles(response);
        }

        // Extract the response.
        try {
            responseArray = response.getJSONArray("ITEMS");
        } catch (org.json.JSONException e) {
            X_RAPID_API_KEY = requestUserAPIKey("The Netflix library API key appears to be invalid. Please enter another one.",
                    "Invalid API key");
            return null;
        }

        return responseArray;
    }

    /**
     * Get endpoint URL for the specified query type.
     *
     * @param queryType Query type
     * @return The endpoint URL
     */
    private String getEndpoint(String queryType) {
        String requestURL;

        switch (queryType) {
            case "fetchTitles":
                requestURL = "https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi?q=-!0,3000-!0,5-!,10-!0-!Any-!Any-!Any-!-!&t=ns&cl=23&st=adv&ob=Relevance&p=&sa=or";
                break;
            case "fetchGenres":
                requestURL = "https://unogs-unogs-v1.p.rapidapi.com/api.cgi?t=genres";
                break;
            default:
                throw new IllegalArgumentException("Invalid argument.");
        }

        return requestURL;
    }

    public static JSONObject selectRandomTitle(JSONArray titles) {
        // Select a random title from the list of titles.
        Random r = new Random();
        int randomIndex = r.nextInt(titles.length());
        System.out.print("Response length: " + titles.length());
        JSONObject selectedTitle = titles.getJSONObject(randomIndex);

        return selectedTitle;
    }

}
