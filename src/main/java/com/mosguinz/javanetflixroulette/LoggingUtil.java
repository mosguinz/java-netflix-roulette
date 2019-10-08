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

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Mos
 */
public class LoggingUtil {

    /**
     * Set up loggers.
     *
     * @param logger instance of Logger.
     */
    static void setupLogger(Logger logger) {

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

    /**
     * Log an exception.
     *
     * @param logger The logger as instance of Logger
     * @param e The exception to log
     */
    static void logException(Logger logger, Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        logger.log(Level.SEVERE, "{0}\n{1}\n{2}", new Object[]{e.toString(), e.getMessage(), sw.toString()});
    }

    /**
     * Log an exception with a message.
     *
     * @param logger The logger as instance of Logger
     * @param e The exception to log
     * @param msg The message explaining the exception
     */
    static void logException(Logger logger, Exception e, String msg) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        logger.log(Level.SEVERE, "{0}\n{1}\n{2}\n{3}", new Object[]{msg, e.toString(), e.getMessage(), sw.toString()});
    }

}
