package com.jck.validator;

import com.jck.util.RegexConstants;

import java.util.regex.Matcher;

public class ArrayValidatorImpl implements ArrayValidator {
    @Override
    public boolean isValid(String line) {
        if (line == null) {
            return false;
        }
        if (line.isBlank()) {
            return true;
        }

        String[] parts = line.split(RegexConstants.DELIMITER_REGEX);
        for (String part : parts) {
            String trimmedPart = part.strip();
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