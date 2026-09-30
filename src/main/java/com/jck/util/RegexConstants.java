package com.jck.util;

import java.util.regex.Pattern;

public final class RegexConstants {
    // Rule 9: Regex in constants
    public static final String DELIMITER_REGEX = "[\\s,;\\-\\u2013]+";
    public static final Pattern VALID_NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    private RegexConstants() {
        // Prevent instantiation
    }
}