package com.jck.validator;

import com.jck.util.RegexConstants;

import java.util.regex.Matcher;

public class DefaultDataValidator implements DataValidator {
    @Override
    public boolean isValid(String line) {
        if (line == null || line.trim().isEmpty()) {
            return true; // Empty strings are valid per requirements
        }

        String[] parts = line.split(RegexConstants.DELIMITER_REGEX);
        for (String part : parts) {
            String trimmedPart = part.trim();
            if (!trimmedPart.isEmpty()) {
                Matcher matcher = RegexConstants.VALID_NUMBER_PATTERN.matcher(trimmedPart);
                if (!matcher.matches()) {
                    return false;
                }
            }
        }
        return true;
    }
}