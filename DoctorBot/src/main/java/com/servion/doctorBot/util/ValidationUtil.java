package com.servion.doctorBot.util;

public class ValidationUtil {

    private ValidationUtil() {
        // prevent instantiation
    }

    public static void requireNonBlank(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
