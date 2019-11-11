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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A logging utility class for this program, because the default logging library
 * sucks.
 *
 * @author mosguinz
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
