package com.servion.doctorBot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    private static final int LOCATION_WIDTH = 45; // total width of class + method

    public static void logError(Exception e) {
        String errorMsg = String.format("[%s] %s", e.getClass().getSimpleName(), e.getMessage());
        logger.error("{} : {}", formatLocation(), errorMsg);
    }
    
    public static void logErrorDetails(Exception e) {
    	logError(e);
        for (StackTraceElement element : e.getStackTrace()) {
            logger.error("    at {}", element.toString());
        }	
    }

    public static void logInfo(String msg) {
        logger.info("{} : {}", formatLocation(), msg);
    }
    
    private static String formatLocation() {
    	StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
        String fullClassName = caller.getClassName(); // e.g., com.example.MyClass
        String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1); // e.g., MyClass
        String methodName = caller.getMethodName() + "()";
        String methodSignature = simpleClassName + "." + methodName;
        return String.format("%-" + LOCATION_WIDTH + "s", methodSignature);
    }
}