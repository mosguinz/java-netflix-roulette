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

import java.util.Iterator;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.Random;
import javax.swing.JOptionPane;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The {@code NetflixLibrary} class is responsible for interacting with the
 * Unofficial Netflix Online Global Search (uNoGS) API and performing related
 * operations on its responses.
 * <p>
 * The class is responsible for operations, such as:
 * <ul>
 * <li>sending GET requests to the uNoGS API;</li>
 * <li>extracting and validating responses from the API;</li>
 * <li> selecting random titles from given parameters.</li>
 * </ul>
 *
 * @author Mos
 */
public class NetflixLibrary {

    private static final Logger LOGGER = Logger.getLogger(NetflixLibrary.class.getName());

    private final String X_RAPID_API_KEY;
    private final LocalLibrary localLibrary = new LocalLibrary();

    public JSONArray availableRegions;
    public JSONArray availableGenres;

    /**
     * Set up an instance of {@code NetflixLibrary}.
     * <p>
     * Upon instantiation, the instance will:
     * <ul>
     * <li>obtain the API key provided in the environment variable under the key
     * {@code X_RAPID_API_KEY};</li>
     * <li>fetch the list of available Netflix regions;</li>
     * <li>fetch the list of genres;</li>
     * </ul>
     */
    NetflixLibrary() {
        this.X_RAPID_API_KEY = getXRapidAPIKey();
        LoggingUtil.setupLogger(LOGGER);

        availableRegions = fetchRegions();
        availableGenres = fetchGenres();
    }

    /**
     * Get the API key for uNoGS.
     * <p>
     * Key must either be present in the environment variable under
     * {@code X_RAPID_API_KEY} or be provided when prompted.
     *
     * @return a RapidAPI key for uNoGS
     */
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

    /**
     * Display a pop-up dialogue to request an API key from the user.
     * <p>
     * Invoked when a valid API key cannot be found or obtained from the
     * environment variables.
     *
     * @param message the message displayed to the user
     * @param title the dialogue box title
     * @return the API key provided by the user
     */
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

    /**
     * Fetch a list of Netflix titles.
     *
     * @return a {@code JSONArray} of available titles
     */
    public JSONArray fetchTitles() {
        LOGGER.log(Level.INFO, "Fetching Netflix titles...");
        return fetchData("fetchTitles");
    }

    /**
     * Fetch a list of genres.
     *
     * @return a {@code JSONArray} of available genres
     */
    public JSONArray fetchGenres() {
        LOGGER.log(Level.INFO, "Fetching list of genres...");
        return fetchData("fetchGenres");
    }

    /**
     * Fetch a list of Netflix regions.
     *
     * @return a {@code JSONArray} of available regions
     */
    public JSONArray fetchRegions() {
        LOGGER.log(Level.INFO, "Fetching list of available regions...");
        return fetchData("fetchAvailableRegions");
    }

    /**
     * Fetch the data for the given {@code queryType}.
     * <p>
     * The method will fetch the data for the given {@code queryType} by looking
     * for responses saved in the local machine first. If that is not available,
     * then it will send a request to the uNoGS API to obtain the data.
     *
     * @param queryType must be either {@code fetchGenres},
     * {@code fetchRegions}, or {@code fetchAvailableRegions}
     * @return a {@code JSONArray} of the requested data
     */
    private JSONArray fetchData(String queryType) {
        LOGGER.log(Level.INFO, "Fetching data for queryType: {0}", queryType);
        JSONArray data = localLibrary.loadSavedResponse(queryType);

        if (data == null) {
            return sendQuery(queryType);
        }

        return data;
    }

    /**
     * Send a GET request to the uNOGS API server.
     * <p>
     * Will only be invoked if there is no responses saved or if the saved
     * responses are out-of-date or not valid.
     * <p>
     * When invoked, it will also attempt to:
     * <ul>
     * <li>verify whether the response is valid;</li>
     * <li>extract the response into a suitable format;</li>
     * <li><b>write the response to file</b>, so that it can be used later.</li>
     * </ul>
     *
     * @param queryType must be either {@code fetchGenres},
     * {@code fetchRegions}, or {@code fetchAvailableRegions}
     * @return a {@code JSONArray} of the requested data if the response was
     * successfully validated and extracted; {@code null} otherwise
     */
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
     * Extract response for the list of genres and its IDs.
     * <p>
     * For some reason, items in the "ITEMS" key are nested in single key-pair
     * values for each genre, where the key is the genre names and its value is
     * an array containing numerical ID(s) that are associated with the genre.
     * <p>
     * This method maps the SUPERCATEGORY genre names to the list of IDs of its
     * SUBCATEGORIES.
     * <p>
     * Assumes that the response is in the format where the SUPERCATEGORIES are
     * listed FIRST and starts with the word "All".
     * <p>
     * For example, the SUPERCATEGORY of "Action" titles may contain
     * SUBCATEGORIES, such as "Action Comedies," "Action Sci-Fi & Fantasy,"
     * "Action Thrillers," etc.
     * <p>
     * So, the list of IDs that includes those SUBCATEGORIES genres will be
     * listed under the key "All Action". And the individual IDs of those
     * SUBCATEGORIES can be found later on in the response.
     *
     * @param response the raw response in the form of {@code JSONArray} from
     * the API
     * @return a {@code JSONArray} of the (supercategory) genres, where each
     * entry is a {@code JSONObject} with the key being the genre name, if the
     * response is in an expected format; {@code null} otherwise
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

    /**
     * Extract response for the list of available regions and its IDs.
     * <p>
     * The response contains a lot of other information aside from the internal
     * ID, such as region pricing, currency, etc -- likely from the result of
     * web scraping. These information are filtered out as they are unnecessary.
     *
     * @param response the raw response in the form of {@code JSONArray} from
     * the API
     * @return a {@code JSONArray} of the available Netflix regions, where each
     * entry is a {@code JSONObject} with the key being the region name, if the
     * response is in an expected format
     */
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
     * <p>
     * The content of a valid response from the UNoGS API is always an array
     * under the key "ITEMS". If this key-pair is not present in the JSON, then
     * the response is assumed to be invalid.
     *
     * @param response the {@code JSONObject} returned from the query
     * @return the content of response as a {@code JSONArray} if the response is
     * valid; {@code null} otherwise
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

    /**
     * Call methods respective to the given {@code queryType} to extract its
     * response.
     *
     * @param response the raw response in the form of {@code JSONArray} from
     * the API
     * @param queryType must be either {@code fetchGenres},
     * {@code fetchRegions}, or {@code fetchAvailableRegions}
     * @return a {@code JSONArray} of an extracted response if successful;
     * {@code null} otherwise
     */
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
     * @param queryType must be either {@code fetchGenres},
     * {@code fetchRegions}, or {@code fetchAvailableRegions}
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

    /**
     * Select a random title from a {@code JSONArray} of titles.
     *
     * @param titles a {@code JSONArray} of titles
     * @return the selected title as a {@code JSONObject}
     */
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
