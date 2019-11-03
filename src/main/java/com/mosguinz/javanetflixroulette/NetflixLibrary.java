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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.Random;
import javax.swing.JOptionPane;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The {@link NetflixLibrary} class is responsible for interacting with the
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
 * @author mosguinz
 */
public class NetflixLibrary {

    /**
     * The {@link Logger} object for the class.
     */
    private static final Logger LOGGER = Logger.getLogger(NetflixLibrary.class.getName());

    /**
     * The API key to use for sending queries to the uNoGS API.
     *
     * @see #getXRapidAPIKey()
     */
    private final String X_RAPID_API_KEY;
    private final LocalLibrary localLibrary = new LocalLibrary();

    private String queryRegion = "All regions";
    private String queryGenres = "All";
    private String titlesQueryString;

    public JSONArray availableRegions;
    public JSONArray availableGenres;

    /**
     * Set up an instance of {@link NetflixLibrary}.
     * <p>
     * Upon instantiation, the instance will:
     * <ul>
     * <li>obtain the API key provided in the environment variable under the key
     * {@link #X_RAPID_API_KEY X_RAPID_API_KEY};</li>
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
     * {@link #X_RAPID_API_KEY X_RAPID_API_KEY} or be provided when prompted.
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
     * Set the Netflix region to be used for querying titles.
     *
     * @param region the region selected by user from
     * {@link HomeGUI#getSelectedRegion()}
     */
    public void setQueryRegion(String region) {
        LOGGER.log(Level.INFO, "Setting query region to {0}", region);
        this.queryRegion = region;
    }

    /**
     * Fetch a list of Netflix titles.
     *
     * @return a {@link JSONArray} of available titles
     */
    public JSONArray fetchTitles() {
        LOGGER.log(Level.INFO, "Fetching Netflix titles available in: {0}", queryRegion);
        getTitlesQueryString();
        return fetchData("fetchTitles");
    }

    /**
     * Fetch a list of genres.
     *
     * @return a {@link JSONArray} of available genres
     */
    public JSONArray fetchGenres() {
        LOGGER.log(Level.INFO, "Fetching list of genres...");
        return fetchData("fetchGenres");
    }

    /**
     * Fetch a list of Netflix regions.
     *
     * @return a {@link JSONArray} of available regions
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
     * @return a {@link JSONArray} of the requested data
     */
    private JSONArray fetchData(String queryType) {
        LOGGER.log(Level.INFO, "Fetching data for queryType: {0}", queryType);

        // Look for saved responses first.
        JSONArray data = localLibrary.loadSavedResponse(queryType, titlesQueryString);

        if (data == null) {
            LOGGER.log(Level.INFO, "Can't find a valid response to use... sending a query to uNoGS API instead...");
            data = sendQuery(queryType);
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
     * @return a {@link JSONArray} of the requested data if the response was
     * successfully validated and extracted; {@code null} otherwise
     */
    public JSONArray sendQuery(String queryType) {
        LOGGER.log(Level.INFO, "Sending query to uNoGS API server: {0}", queryType);
        String requestURL = getEndpoint(queryType);
        JSONObject response = null;
        JSONArray responseContent = null;

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
            LoggingUtil.logException(LOGGER, e, "There was a problem contacting the Netflix library.");
            HomeGUI.displayErrorMessage("There was a problem contacting the Netflix library. Please try again.",
                    "Connection failed", e);
        }

        try {
            // Verify that response is valid.
            responseContent = verifyResponse(response);

            // If it is, extract it and save the response.
            if (responseContent != null) {
                responseContent = extractResponse(responseContent, queryType);
                localLibrary.saveResponse(responseContent, queryType, titlesQueryString);
            }

        } catch (NegativeArraySizeException e) {
            LOGGER.log(Level.WARNING, "No matching titles", e);
            HomeGUI.displayErrorMessage("We found no matching Netflix titles!\n"
                    + "Perhaps try again with different filters?",
                    "No matching titles", e);

        } catch (NoSuchFieldException e) {
            LOGGER.log(Level.SEVERE, "PARSING ERROR -- CHECK API DOC FOR UPDATED FORMAT", e);
            HomeGUI.displayErrorMessage("It looks like this program needs to be updated.\n"
                    + "We are unable to read the response from the Netflix catalogue. :(",
                    "Uh oh!", e);
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
     * @param response the raw response in the form of {@link JSONArray} from
     * the API
     * @return a {@link JSONArray} of the (supercategory) genres, where each
     * entry is a {@link JSONObject} with the key being the genre name, if the
     * response is in an expected format; {@code null} otherwise
     * @throws NoSuchFieldException if the response is not in the expected
     * format
     */
    private static JSONArray extractSupercategoryGenres(JSONArray response) throws NoSuchFieldException {
        LOGGER.log(Level.FINE, "Extracting genres and its IDs...");
        JSONArray itemsKey = new JSONArray();
        JSONObject supercategoryGenres = new JSONObject();

        for (Object object : response) {

            JSONObject entry = (JSONObject) object;

            for (Iterator keys = entry.keys(); keys.hasNext();) {
                String categoryName = keys.next().toString();

                if (categoryName.startsWith("All ")) {
                    supercategoryGenres.put(categoryName.substring(4), entry.getJSONArray(categoryName));
                } else {
                    itemsKey.put(0, supercategoryGenres);
                    return itemsKey;
                }

            }

        }

        // Should not be reachable if the response is valid...
        throw new NoSuchFieldException("Could not extract the genres from the given response; unexpected format");
    }

    /**
     * Extract response for the list of available regions and its IDs.
     * <p>
     * The response contains a lot of other information aside from the internal
     * ID, such as region pricing, currency, etc -- likely from the result of
     * web scraping. These information are filtered out as they are unnecessary.
     *
     * @param response the raw response in the form of {@link JSONArray} from
     * the API
     * @return a {@link JSONArray} of the available Netflix regions, where each
     * entry is a {@link JSONObject} with the key being the region name, if the
     * response is in an expected format
     * @throws NoSuchFieldException if the response is not in the expected
     * format
     */
    private static JSONArray extractAvailableRegions(JSONArray response) throws NoSuchFieldException {
        LOGGER.log(Level.FINE, "Extracting available Netflix regions and its IDs");

        JSONArray itemsKey = new JSONArray();
        JSONObject regions = new JSONObject();
        for (Object object : response) {
            JSONArray r = new JSONArray(object.toString());

            try {
                String regionID = r.getString(0);
                String regionName = r.getString(2);
                regions.put(regionName, regionID);
            } catch (org.json.JSONException e) {
                throw new NoSuchFieldException("Could not extract the regions from the given response; unexpected format");
            }
        }

        itemsKey.put(0, regions);
        return itemsKey;
    }

    /**
     * Verify that the response is valid.
     * <p>
     * The content of a valid response from the UNoGS API is always an array
     * (parsed as {@link JSONArray}) under the key "ITEMS". If this key-pair is
     * not present in the JSON, then the response is assumed to be invalid.
     *
     * @param response the {@link JSONObject} returned from the query
     * @return the content of response as a {@link JSONArray} if the response is
     * valid; {@code null} otherwise
     * @throws NegativeArraySizeException if the response is blank -- i.e., if
     * the query did not match any results
     */
    public static JSONArray verifyResponse(JSONObject response) throws NegativeArraySizeException {
        LOGGER.log(Level.FINE, "Verifying that the response content is valid...");

        JSONArray responseContent;

        try {
            responseContent = response.getJSONArray("ITEMS");
            if (responseContent.length() == 0) {
                throw new NegativeArraySizeException("Response is blank! No valid items in response content.");
            }
        } catch (org.json.JSONException e) {
            LoggingUtil.logException(LOGGER, e, "Response is not valid.");
            return null;
        }

        return responseContent;

    }

    /**
     * Call methods respective to the given {@code queryType} to extract its
     * response.
     *
     * @param response the raw response in the form of {@link JSONArray} from
     * the API
     * @param queryType must be either {@code fetchGenres},
     * {@code fetchRegions}, or {@code fetchAvailableRegions}
     * @return a {@link JSONArray} of an extracted response if successful;
     * {@code null} otherwise
     * @throws NoSuchFieldException if the response is not in the expected
     * format
     */
    public static JSONArray extractResponse(JSONArray response, String queryType) throws NoSuchFieldException {
        switch (queryType) {
            case "fetchGenres":
                return extractSupercategoryGenres(response);
            case "fetchAvailableRegions":
                return extractAvailableRegions(response);
            default:
                return response;
        }
    }

    private String getRegionID() {
        LOGGER.log(Level.FINE, "Getting region ID for: {0}", queryRegion);
        String regionID;

        if (queryRegion.equals("All regions")) {
            regionID = "all";
        } else {
            regionID = availableRegions.getJSONObject(0).getString(queryRegion);
        }

        LOGGER.log(Level.FINE, "Region ID for {0} is {1}", new Object[]{queryRegion, regionID});
        return regionID;
    }

    /**
     * Construct a query string to use for requesting titles.
     * <p>
     * Because the way parameters are passed with this API is just so f*cking
     * abhorrent, the query string just look absolutely disgusting and
     * nonsensical.
     * <p>
     * The query parameters passed for this endpoint aren't actually
     * "parameters" that would have otherwise been separated by ampersands. Most
     * of them are crammed under the parameter {@code q}, which are then
     * separated by {@code !-}.
     * <p>
     * The best part is, the order at which the parameters are shown on the API
     * docs page AREN'T EVEN IN THE SAME ORDER AS THE ACTUAL QUERY STRING.
     * WHY?!?!??!
     * <p>
     * The order of the parameters in the query string is:
     * <p>
     * {@code q={query}-!{syear},{eyear}-!{snfrate},{enfrate}-!{simdbrate},{eimdbrate}-!{genreid}-!{vtype}-!{audio}-!{subtitle}-!{imdbvotes}-!{downloadable}&t=ns&cl={clist}&st=adv&ob={sortby}&p={page}&sa={andor}}
     * <p>
     * Most of which, can be ignored for the purpose of this application. They
     * have all been hard-coded to include all of the titles, so that the only
     * two parameters that will be set by this application -- i.e., region and
     * genres -- will be the only factors that filters out the titles. Which
     * boils the parameters down to:
     * <p>
     * {@code q=-!0,3000-!0,10-!0,10-!{GENRE_IDs}-!Any-!Any-!Any-!-!&t=ns&cl={COUNTRY_LIST}&st=adv&ob=Relevance&p=1&sa=or},
     * where:
     * <ul>
     * <li>{@code GENRE_IDs} is a list of selected genres, represented by their
     * IDs and separated by a comma; and
     * <ul>
     * <li>if all genres are selected, the ID is {@code 0}</li>
     * </ul></li>
     * <li>{@code COUNTRY_LIST} is a list of selected regions, represented by
     * their IDs and separated by a comma
     * <ul>
     * <li>if all regions are selected, the ID is {@code all}</li>
     * <li>from this application, users can only choose to include all regions
     * or one region only; there is very little to no use case where a user
     * would need to filter for two or more specific regions</li>
     * <li>note that these country codes are <b>not</b> the two-letter ISO
     * 3166-1 alpha-2 codes, such as {@code US}, {@code NZ}, {@code AU}, etc.,
     * as noted in the API documentation
     * </ul></li>
     * </ul>
     *
     * @return a {@link String} that is the query string for requesting titles
     * available in the specified regions (per {@link #queryRegion queryRegion})
     * @see
     * <a href="https://rapidapi.com/unogs/api/unogs?endpoint=5690bcdee4b0e203818a6518">
     * https://rapidapi.com/unogs/api/unogs?endpoint=5690bcdee4b0e203818a6518</a>
     * for this endpoint's documentation
     */
    private void getTitlesQueryString() {
        LOGGER.log(Level.INFO, "Creating a query string to look for titles that are available in: {0}.", queryRegion);
        String genreIDs = "0";
        String regionID = getRegionID();
        titlesQueryString = ("q=-!0%2C3000-!0%2C10-!0%2C10-!" + genreIDs + "-!Any-!"
                + "Any-!Any-!-!&t=ns&cl=" + regionID + "&st=adv&ob=Relevance&p=1&sa=or");

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
                requestURL = "https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi?" + titlesQueryString;
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
     * Select a random title from a {@link JSONArray} of titles.
     *
     * @param titles a {@link JSONArray} of titles
     * @return the selected title as a {@link JSONObject}
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

    /**
     * Get a sorted list of available regions.
     *
     * @return An alphabetically-ordered list of Netflix regions
     */
    public Object[] getAvailableRegionsList() {
        LOGGER.log(Level.FINE, "Getting a list of available regions");

        JSONObject regions = availableRegions.getJSONObject(0);
        ArrayList<String> r = new ArrayList<>();

        for (Iterator keys = regions.keys(); keys.hasNext();) {
            r.add(keys.next().toString());
        }

        // Ensure "All regions" is always at the top.
        Collections.sort(r);
        r.add(0, "All regions");

        return r.toArray();

    }

    /**
     * Get a sorted list of available genres.
     *
     * @return An alphabetically-ordered {@link ArrayList} of available genres
     */
    public ArrayList<String> getAvailableGenresList() {
        LOGGER.log(Level.FINE, "Getting a list of available genres");

        JSONObject genres = availableGenres.getJSONObject(0);
        ArrayList<String> g = new ArrayList<>();

        for (Iterator keys = genres.keys(); keys.hasNext();) {
            g.add(keys.next().toString());
        }

        Collections.sort(g);

        return g;
    }

}
