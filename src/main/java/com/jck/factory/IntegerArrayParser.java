package com.jck.factory;

import com.jck.entity.NumberArray;
import com.jck.entity.builder.IntegerArrayBuilder;
import com.jck.exception.InvalidDataFormatException;
import com.jck.util.RegexConstants;

import java.util.ArrayList;
import java.util.List;

public class IntegerArrayParser extends ArrayParser {
    @Override
    public NumberArray parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            int[] emptyValues = new int[0];
            return new IntegerArrayBuilder().setValues(emptyValues).build();
        }

        String[] stringParts = line.split(RegexConstants.DELIMITER_REGEX);

        // Filter out empty parts
        List<Integer> parsedList = new ArrayList<>();
        for (String part : stringParts) {
            String trimmedPart = part.trim();
            if (!trimmedPart.isEmpty()) {
                try {
                    parsedList.add(Integer.parseInt(trimmedPart));
                } catch (NumberFormatException e) {
                    throw new InvalidDataFormatException("Cannot parse integer from: " + trimmedPart);
                }
            }
        }

        // Convert List to array
        int[] parsedValues = new int[parsedList.size()];
        for (int i = 0; i < parsedList.size(); i++) {
            parsedValues[i] = parsedList.get(i);
        }

        return new IntegerArrayBuilder().setValues(parsedValues).build();
    }
}
