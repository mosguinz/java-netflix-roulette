/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mos
 */
public class SetupLogging {

    /**
     * Set up loggers.
     *
     * @param logger instance of Logger.
     */
    static void setup(Logger logger) {

        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);

        logger.log(Level.FINER, "Setting up logger for {0}", logger.getName());
        logger.log(Level.FINEST, "Creating ConsoleHandler");
        logger.log(Level.FINEST, "Setting handler level as {0}", handler.getLevel().toString());
        logger.log(Level.FINEST, "Adding console handler");
        logger.log(Level.FINEST, "Setting logger level as {0}", handler.getLevel().toString());
    }

}
