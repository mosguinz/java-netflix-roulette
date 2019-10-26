/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

import java.util.Iterator;
import java.util.logging.Logger;
import java.util.logging.Level;

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

    private static final Logger LOGGER = Logger.getLogger(NetflixLibrary.class.getName());

    private final String X_RAPID_API_KEY;
    private final LocalLibrary localLibrary = new LocalLibrary();

    public JSONArray availableRegions;
    public JSONArray availableGenres;

    NetflixLibrary() {
        this.X_RAPID_API_KEY = getXRapidAPIKey();
        LoggingUtil.setupLogger(LOGGER);

        availableRegions = fetchRegions();
        availableGenres = fetchGenres();
    }

    private static String getXRapidAPIKey() {
        LOGGER.log(Level.INFO, "Getting API key for Netflix library...");
        String key = null;

        try {
            key = java.lang.System.getenv("X_RAPID_API_KEY");
        } catch (NullPointerException | SecurityException e) {
            LoggingUtil.logException(LOGGER, e, "Could not find API key from environment variables...");
            key = requestUserAPIKey("Netflix library API could not be found or access in the environment variables. Please provide one here.",
                    "Netflix API key not found");
        } finally {
            LOGGER.log(Level.CONFIG, "Provided API key for Netflix library {0}", key);
        }

        return key;
    }

    private static String requestUserAPIKey(String message, String title) {
        LOGGER.log(Level.FINE, "Requesting API key from user...");
        String key = null;

        while (key == null || key.isEmpty()) {
            LOGGER.log(Level.FINER, "Creating input dialog for user to input API key");
            key = JOptionPane.showInputDialog(null, message, title, JOptionPane.ERROR_MESSAGE);

            if (key != null) {
                LOGGER.log(Level.FINER, "Stripping whitespaces from input");
                key = key.replaceAll("\\s+", "");
            }
        }

        return key;
    }

    public JSONArray fetchTitles() {
        LOGGER.log(Level.INFO, "Fetching Netflix titles...");
        return fetchData("fetchTitles");
    }

    public JSONArray fetchGenres() {
        LOGGER.log(Level.INFO, "Fetching list of genres...");
        return fetchData("fetchGenres");
    }

    public JSONArray fetchRegions() {
        LOGGER.log(Level.INFO, "Fetching list of available regions...");
        return fetchData("fetchAvailableRegions");
    }

    private JSONArray fetchData(String queryType) {
        LOGGER.log(Level.INFO, "Fetching data for queryType: {0}", queryType);
        JSONArray data = localLibrary.loadSavedResponse(queryType);

        if (data == null) {
            return sendQuery(queryType);
        }

        return data;
    }

    public JSONArray sendQuery(String queryType) {
        LOGGER.log(Level.INFO, "Sending query to uNoGS API server: {0}", queryType);
        String requestURL = getEndpoint(queryType);
        JSONArray responseArray;
        JSONObject response = null;

        // Send the query.
        try {
            LOGGER.log(Level.FINE, "Sending query at: {0}", requestURL);
            response = Unirest.get(requestURL)
                    .header("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", this.X_RAPID_API_KEY)
                    .asJson()
                    .getBody()
                    .getObject();
        } catch (Exception e) {
            // Temp fix for SSL handshake errors
            // TODO: Create proper popups to handle this and other connection errors.
            LoggingUtil.logException(LOGGER, e, "There was a problem contacting the Netflix library.");
            JOptionPane.showMessageDialog(null, "There was an error contacting Netflix library. Please try again.\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Verify that response is valid.
        JSONArray responseContent = verifyResponse(response);

        if (responseContent != null) {
            responseContent = extractResponse(responseContent, queryType);
            localLibrary.saveResponse(responseContent, queryType);
        }

        return responseContent;
    }

    /**
     * Extract the response for the genres.
     *
     * For some reason, items in the "ITEMS" key are nested in single key-pair
     * values for each genre, where the key is the genre names and its value is
     * an array containing numerical ID(s) that are associated with the genre.
     *
     * This method maps the SUPERCATEGORY genre names to the list of IDs of its
     * SUBCATEGORIES.
     *
     * Assumes that the response is in the format where the SUPERCATEGORIES are
     * listed FIRST and starts with the word "All".
     *
     * For example, the SUPERCATEGORY of "Action" titles may contain
     * SUBCATEGORIES, such as "Action Comedies," "Action Sci-Fi & Fantasy,"
     * "Action Thrillers," etc.
     *
     * So, the list of IDs that includes those SUBCATEGORIES genres will be
     * listed under the key "All Action". And the individual IDs of those
     * SUBCATEGORIES can be found later on in the response.
     *
     * @param responseContent
     * @return
     */
    private static JSONArray extractSupercategoryGenres(JSONArray response) {
        JSONArray supercategoryGenres = new JSONArray();

        int index = 0;

        for (Object object : response) {

            for (Iterator keys = ((JSONObject) object).keys(); keys.hasNext();) {
                String categoryName = keys.next().toString();

                if (categoryName.startsWith("All ")) {
                    supercategoryGenres.put(index, object);
                } else {
                    return supercategoryGenres;
                }

            }

            index++;
        }

        // Should not be reachable if the response is valid...
        return null;
    }

    private static JSONArray extractAvailableRegions(JSONArray response) {

        JSONArray regions = new JSONArray();
        regions.put(0, new JSONObject().put("All regions", "All"));

        int index = 0;

        for (Object object : response) {
            JSONObject region = new JSONObject();
            JSONArray r = new JSONArray(object.toString());

            String regionID = r.getString(0);
            String regionName = r.getString(2);
            region.put(regionName, regionID);

            regions.put(index, region);
            index++;
        }

        return regions;
    }

    /**
     * Verify that the response is valid.
     *
     * The content of a valid response from the UNoGS API is always an array
     * under the key "ITEMS". If this key-pair is not present in the JSON, then
     * the response is not valid.
     *
     * @param response JSONObject of that is returned from the query
     * @return The content of response as a JSONArray if the response is valid;
     * null otherwise
     */
    public static JSONArray verifyResponse(JSONObject response) {
        LOGGER.log(Level.FINE, "Verifying that the response content is valid...");

        JSONArray responseContent = null;

        try {
            responseContent = response.getJSONArray("ITEMS");
        } catch (org.json.JSONException e) {
            LoggingUtil.logException(LOGGER, e, "Response is not valid.");
        }

        return responseContent;

    }

    public static JSONArray extractResponse(JSONArray response, String queryType) {
        switch (queryType) {
            case "fetchGenres":
                return extractSupercategoryGenres(response);
            case "fetchAvailableRegions":
                return extractAvailableRegions(response);
            default:
                return response;
        }
    }

    /**
     * Get endpoint URL for the specified query type.
     *
     * @param queryType Query type
     * @return The endpoint URL
     */
    private String getEndpoint(String queryType) {
        LOGGER.log(Level.FINE, "Getting endpoint URL for query type \"{0}\"", queryType);
        String requestURL;

        switch (queryType) {
            case "fetchTitles":
                requestURL = "https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi?q=Lemony%20Snicket's%20A%20Series%20of%20Unfortunate%20Events-!2004%2C2004-!0%2C5-!6.8%2C6.8-!0-!Any-!Any-!Any-!gt-1-!%7Bdownloadable%7D&t=ns&cl=all&st=adv&ob=Relevance&p=1&sa=and";
                break;
            case "fetchGenres":
                requestURL = "https://unogs-unogs-v1.p.rapidapi.com/api.cgi?t=genres";
                break;
            case "fetchAvailableRegions":
                requestURL = "https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi?t=lc&q=available";
                break;
            default:
                LOGGER.log(Level.FINER, "Failed to get endpoint URL; invalid query type \"{0}\"", queryType);
                throw new IllegalArgumentException("Invalid argument.");
        }

        return requestURL;
    }

    public static JSONObject selectRandomTitle(JSONArray titles) {
        LOGGER.log(Level.FINE, "Selecting a random title from list of titles");

        // Select a random title from the list of titles.
        Random r = new Random();
        int respLength = titles.length();
        int randomIndex = r.nextInt(respLength);

        LOGGER.log(Level.FINE, "Chosen title #{0} from a list of {1} titles",
                new Object[]{randomIndex, respLength});

        JSONObject selectedTitle = titles.getJSONObject(randomIndex);

        return selectedTitle;
    }

}
