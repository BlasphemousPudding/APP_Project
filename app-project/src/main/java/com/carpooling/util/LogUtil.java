package com.carpooling.util;

import java.io.IOException;
import java.util.logging.*;

public class LogUtil {
    private static final Logger LOGGER = Logger.getLogger(LogUtil.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            // Create a FileHandler that writes to log.txt
            fileHandler = new FileHandler("log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            
            // Remove existing handlers from the root logger
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }
            
            // Add our FileHandler to the root logger
            rootLogger.addHandler(fileHandler);
            
            // Set the logging level
            rootLogger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }

    public static void log(Level level, String message, Throwable thrown) {
        LOGGER.log(level, message, thrown);
    }
}
