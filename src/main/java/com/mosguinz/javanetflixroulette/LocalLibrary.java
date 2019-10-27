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

import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.Locale;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The {@code LocalLibrary} class is responsible for reading and writing
 * responses
 *
 * @author Mos
 */
public class LocalLibrary {

    private static final Logger LOGGER = Logger.getLogger(LocalLibrary.class.getName());

    private final String HOME_PATH = getHomePath();
    private final String LIBRARY_PATH = getLibraryPath();
    private final int MAX_RESPONSE_AGE = 14;

    LocalLibrary() {
        LoggingUtil.setupLogger(LOGGER);
    }

    /**
     * Get the user's home directory.
     *
     * @return The location of the user's home directory
     */
    private static String getHomePath() {
        LOGGER.log(Level.FINE, "Getting user's home path...");
        return System.getProperty("user.home");
    }

    /**
     * Get the home directory for this application.
     *
     * @return The location of this application's home directory
     */
    private String getLibraryPath() {
        LOGGER.log(Level.FINE, "Creating the path for this library...");
        return HOME_PATH + File.separator + "netflixRoulette";
    }

    private String createSaveName() {
        String saveName;

        String todayDate = LocalDate.now().toString();

        return todayDate + "test.json";
    }

    /**
     * Create the library directory.
     *
     * @return true if and only if the directory was created; false otherwise
     */
    public boolean makeLibraryDirectory() {
        LOGGER.log(Level.INFO, "Creating the library directory at: {}", LIBRARY_PATH);
        File f = new File(LIBRARY_PATH);
        return f.mkdirs();
    }

    /**
     * Save the responses from uNoGS server..
     *
     * @param response JSONArray of the returned response content
     * @return true if and only if the response were saved; false otherwise
     */
    public boolean saveResponse(JSONArray response, String queryType) {
        LOGGER.log(Level.INFO, "Writing the returned Netflix titles as a JSON");
        FileOutputStream stream;
        boolean saved = true;

        JSONObject f = new JSONObject();
        f.put("DATE", LocalDate.now().toString());
        f.put("COUNT", response.length());
        f.put("ITEMS", response);

        try {
            LOGGER.log(Level.FINE, "Creating file output stream at {0}", LIBRARY_PATH);
            stream = new FileOutputStream(LIBRARY_PATH + File.separator + queryType + ".json");

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

    public JSONArray loadSavedResponse(String queryType) {
        LOGGER.log(Level.FINE, "Looking for saved responses to use...");

        String filename = LIBRARY_PATH + File.separator + queryType + ".json";
        JSONArray response = null;

        try {
            String f = new Scanner(new File(filename)).useDelimiter("\\Z").next();
            JSONObject r = new JSONObject(f);
            LOGGER.log(Level.FINE, "Found a matching saved response to use");

            response = verifySavedResponse(r, queryType);

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO, "No saved response found...");
        } catch (JSONException e) {
            LoggingUtil.logException(LOGGER, e, "Could not load saved responses...");
        }

        return response;
    }

    private JSONArray verifySavedResponse(JSONObject response, String queryType) {

        if (isUpToDate(response)) {
            return NetflixLibrary.verifyResponse(response);
        }

        return null;

    }

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

}
