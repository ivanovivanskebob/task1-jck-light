package com.jck.factory;

import com.jck.entity.NumberArray;
import com.jck.entity.builder.IntegerArrayBuilder;
import com.jck.exception.InvalidDataFormatException;
import com.jck.util.RegexConstants;

public class IntegerArrayParser extends ArrayParser {
    @Override
    public NumberArray parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            int[] emptyValues = new int[0];
            return new IntegerArrayBuilder().setValues(emptyValues).build();
        }

        String[] stringParts = line.split(RegexConstants.DELIMITER_REGEX);
        int[] parsedValues = new int[stringParts.length];

        for (int i = 0; i < stringParts.length; i++) {
            String part = stringParts[i].trim();
            if (!part.isEmpty()) {
                try {
                    parsedValues[i] = Integer.parseInt(part);
                } catch (NumberFormatException e) {
                    throw new InvalidDataFormatException("Cannot parse integer from: " + part);
                }
            }
        }

        return new IntegerArrayBuilder().setValues(parsedValues).build();
    }
}
