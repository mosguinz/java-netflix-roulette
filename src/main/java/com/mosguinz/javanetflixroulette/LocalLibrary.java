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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The {@code LocalLibrary} class is responsible for reading and writing
 * responses to the user's home directory.
 *
 * @author mosguinz
 */
public class LocalLibrary {

    /**
     * The {@link Logger} object for the class.
     */
    private static final Logger LOGGER = Logger.getLogger(LocalLibrary.class.getName());

    /**
     * The user's home directory.
     * <p>
     * This application's local library will live under this directory.
     *
     * @see #createHomePath()
     */
    private static final File HOME_PATH = createHomePath();

    /**
     * This application's local library directory.
     * <p>
     * Responses from the API will be read and written here.
     *
     * @see #createLibraryPath()
     */
    private static final File LIBRARY_PATH = createLibraryPath();

    /**
     * The maximum age of a response in days.
     * <p>
     * Responses that are older than the specified value ({@value} days) will
     * not be used and will be overwritten by new responses.
     *
     * @see #isUpToDate(org.json.JSONObject)
     */
    private static final int MAX_RESPONSE_AGE = 14;

    /**
     * Set up an instance of {@link LocalLibrary}.
     */
    LocalLibrary() {
        LoggingUtil.setupLogger(LOGGER);
        makeLibraryDirectory();
    }

    /**
     * Create the user's home directory.
     *
     * @return The {@link File} object to the user's home directory
     */
    private static File createHomePath() {
        LOGGER.log(Level.FINE, "Getting user's home path...");
        return new File(System.getProperty("user.home"));
    }

    /**
     * Create the home directory for this application.
     *
     * @return The {@link File} object to this application's home directory
     */
    private static File createLibraryPath() {
        LOGGER.log(Level.FINE, "Creating the path for this library...");
        return new File(HOME_PATH, "netflixRoulette");
    }

    /**
     * Get the user's home directory.
     *
     * @return The {@link File} object to the user's home directory
     */
    public static File getHomePath() {
        return HOME_PATH;
    }

    /**
     * Get the home directory for this application.
     *
     * @return The {@link File} object to this application's home directory
     */
    public static File getLibraryPath() {
        return LIBRARY_PATH;
    }

    /**
     * Get the filename to use for reading/writing a response.
     * <p>
     * For titles, it will append a number to the end of the name and increment
     * it to prevent overwriting existing response. It is <b>not</b> used for
     * differentiating the parameters used.
     * <p>
     * For other types of query, it will return the same name, corresponding to
     * the query type. This will result in overwriting the existing file, as it
     * is assumed to be either invalid or expired.
     *
     * @param queryType must be either {@code fetchGenres}, {@code fetchTitles},
     * or {@code fetchAvailableRegions}
     * @return a {@link String} that is the filename for the response
     */
    private static String getResponseFilename(String queryType) {
        LOGGER.log(Level.INFO, "Creating filename for the response");

        if (queryType.equals("fetchTitles")) {

            String filename = "fetchTitles.0.json";
            File f = new File(LIBRARY_PATH, filename);

            for (int x = 1; f.exists(); x++) {
                filename = "fetchTitles." + x + ".json";
                System.out.println(filename);
                f = new File(LIBRARY_PATH, filename);
            }

            return filename;

        } else {
            return queryType + ".json";
        }
    }

    /**
     * Create the library directory.
     *
     * @return {@code true} if and only if the directory was created;
     * {@code false} otherwise
     */
    private static boolean makeLibraryDirectory() {
        LOGGER.log(Level.INFO, "Creating the library directory at: {}", LIBRARY_PATH.toString());

        if (LIBRARY_PATH.exists()) {
            LOGGER.log(Level.INFO, "Library directory already exists");
            return false;
        } else {
            return LIBRARY_PATH.mkdirs();
        }

    }

    /**
     * Save the responses from uNoGS server.
     *
     * @param response {@code JSONArray} of the returned response content
     * @param queryType must be either {@code fetchGenres}, {@code fetchTitles},
     * or {@code fetchAvailableRegions}
     * @param titlesQueryString a {@link String} that is the query string for
     * requesting titles
     * @return {@code true} if and only if the response were saved;
     * {@code false} otherwise
     */
    public boolean saveResponse(JSONArray response, String queryType, String titlesQueryString) {
        LOGGER.log(Level.INFO, "Writing the returned Netflix titles as a JSON");
        FileOutputStream stream;
        boolean saved = true;

        JSONObject f = new JSONObject();
        f.put("DATE", LocalDate.now().toString());
        f.put("ITEMS", response);
        if (queryType.equals("fetchTitles")) {
            f.put("Q-STRING", titlesQueryString);
        }

        try {
            LOGGER.log(Level.FINE, "Creating file output stream at {0}", LIBRARY_PATH);
            String filename = getResponseFilename(queryType);
            stream = new FileOutputStream(LIBRARY_PATH + File.separator + filename);
            LOGGER.log(Level.FINE, "Saving the response as: {0}", filename);

            LOGGER.log(Level.FINE, "Pretty printing JSON response...");
            byte[] b = f.toString(2).getBytes();

            LOGGER.log(Level.INFO, "Writing the response...");
            stream.write(b);

            LOGGER.log(Level.FINE, "Closing file stream...");
            stream.close();
        } catch (FileNotFoundException e) {
            LoggingUtil.logException(LOGGER, e);
            saved = false;
        } catch (IOException e) {
            LoggingUtil.logException(LOGGER, e);
            saved = false;
        }

        if (saved) {
            LOGGER.log(Level.INFO, "Response sucessfully written");
        } else {
            LOGGER.log(Level.WARNING, "Failed to write the response, check exception");
        }

        return saved;
    }

    /**
     * Load and parse the saved response.
     * <p>
     * Load and return the response file as a {@link JSONObject}.
     *
     * @param filename the name of the JSON file
     * @return the response file as a {@link JSONObject}
     */
    private JSONObject loadSavedResponse(String filename) {

        JSONObject responseFile = null;

        try {
            try (Scanner f = new Scanner(new File(LIBRARY_PATH, filename)).useDelimiter("\\Z")) {
                String r = f.next();
                responseFile = new JSONObject(r);
                LOGGER.log(Level.FINE, "Found a matching saved response to use");
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO, "No saved response found...");
        } catch (JSONException e) {
            LoggingUtil.logException(LOGGER, e, "Could not load saved responses...");
        }

        return responseFile;
    }

    /**
     * Find a response with the matching query string.
     * <p>
     * Looks for a saved response for the query type {@code fetchTitles} that
     * has the matching query string. The query string a {@link String}
     * constructed from the provided parameters.
     * <p>
     * This method will look for a response that contains a list of titles
     * corresponding to the parameters provided. If one cannot be found, then it
     * will return {@code null}.
     *
     * @param titlesQueryString a {@link String} that is the query string for
     * requesting titles; only applicable for {@code fetchTitles}
     * @return the response file as a {@link JSONObject}
     * @see NetflixLibrary#setTitlesQueryString()
     */
    private JSONObject loadMatchingResponseQuery(String titlesQueryString) {
        LOGGER.log(Level.INFO, "Looking for a response with a matching query string");
        File[] responses = LIBRARY_PATH.listFiles();

        if (responses != null) {
            for (File response : responses) {
                String filename = response.getName();
                if (filename.startsWith("fetchTitles")) {
                    JSONObject r = loadSavedResponse(filename);
                    if (r.getString("Q-STRING").equals(titlesQueryString)) {
                        return r;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Load the saved response.
     *
     * @param queryType must be either {@code fetchGenres}, {@code fetchTitles},
     * or {@code fetchAvailableRegions}
     * @param titlesQueryString a {@link String} that is the query string for
     * requesting titles; only applicable for {@code fetchTitles}
     * @return a {@link JSONArray} of the requested data
     */
    public JSONArray getSavedResponse(String queryType, String titlesQueryString) {
        LOGGER.log(Level.FINE, "Looking for saved responses to use...");
        JSONObject response;

        if (queryType.equals("fetchTitles")) {
            response = loadMatchingResponseQuery(titlesQueryString);
        } else {
            response = loadSavedResponse(queryType + ".json");
        }

        if (response != null) {
            return verifySavedResponse(response);
        }

        return null;
    }

    /**
     * Verify that the saved response is valid.
     * <p>
     * This method will invoke {@link #isUpToDate isUpToDate} to check whether
     * the response has expired. If it is still valid, it will invoke
     * {@link NetflixLibrary#verifyResponse} to verify that the key
     * {@code ITEMS} is present.
     *
     * @param response the {@link JSONObject} parsed from the file
     * @return the content of response as a {@code JSONArray} if the response is
     * valid; {@code null} otherwise
     */
    private JSONArray verifySavedResponse(JSONObject response) {

        if (isUpToDate(response)) {
            return NetflixLibrary.verifyResponse(response);
        }

        return null;

    }

    /**
     * Check if the saved response is up-to-date.
     * <p>
     * Written responses will have an ISO-8601 timestamp inserted in its JSON
     * file. This is used to check whether the response is fresh enough to be
     * used.
     * <p>
     * The constant {@link #MAX_RESPONSE_AGE MAX_RESPONSE_AGE} determines the
     * maximum acceptable response age in days. It is set at
     * {@value #MAX_RESPONSE_AGE}.
     * <p>
     * If a timestamp cannot be found or is invalid, the response is assumed to
     * be outdated or invalid, and the method will return {@code false}.
     *
     * @param response the {@link JSONObject} parsed from the file
     * @return {@code true} if a valid timestamp is found and the number of days
     * between the current system time ({@link LocalDate#now}) and the date in
     * question is below the number specified in
     * {@link #MAX_RESPONSE_AGE MAX_RESPONSE_AGE}.
     */
    private boolean isUpToDate(JSONObject response) {
        LOGGER.log(Level.FINE, "Checking if the response is up to date");
        String date;

        try {
            date = response.getString("DATE");
        } catch (JSONException e) {
            LoggingUtil.logException(LOGGER, e, "Could not verify response date");
            return false;
        }

        // Will always be in ISO-8601 format uuuu-MM-dd if date is written by this library.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd").withLocale(Locale.ROOT);
        LocalDate dt = LocalDate.parse(date, dtf);

        int responseAge = (int) ChronoUnit.DAYS.between(dt, LocalDate.now());

        LOGGER.log(Level.INFO, "Response is {0} days old; maximum age is {1} days");

        return responseAge < MAX_RESPONSE_AGE;

    }

    /**
     * Get the file count in the library folder.
     * <p>
     * Assumes that all files in the folder are the JSON files of the responses
     * from the uNoGS API.
     *
     * @return number of files present in the library folder,
     * {@link #LIBRARY_PATH}.
     */
    public static int getLibraryFileCount() {
        LOGGER.log(Level.INFO, "Calculating the number of responses in the library folder...");
        return LIBRARY_PATH.listFiles().length;
    }

    /**
     * Get the size of the library folder, in bytes.
     *
     * @return the size of the library folder, in bytes
     */
    public static long getLibraryFolderSize() {
        LOGGER.log(Level.INFO, "Calculating the size of the library folder...");
        long length = 0;
        for (File file : LIBRARY_PATH.listFiles()) {
            if (file.isFile()) {
                length += file.length();
            } // else {
//              length += getFolderSize(file);
//          }   Could probably make this recursive if there are nested folders...
//              not required for now, though.
        }

        return length;
    }

    /**
     * Delete everything in the library folder.
     */
    public static void clearLibraryFolder() {
        LOGGER.log(Level.INFO, "Clearing the library folder...");
        for (File file : LIBRARY_PATH.listFiles()) {
            file.delete();
        }
    }

}
