package com.jck.parser;

import com.jck.exception.DataProcessingException;
import com.jck.exception.InvalidDataFormatException;
import com.jck.util.RegexConstants;
import java.util.ArrayList;
import java.util.List;

public class ArrayParserImpl implements ArrayParser {
    @Override
    public int[] parse(String line) {
        if (line == null) {
            throw new DataProcessingException("Cannot parse null line.");
        }

        if (line.isBlank()) {
            return new int[0];
        }

        String[] stringParts = line.split(RegexConstants.DELIMITER_REGEX);
        List<Integer> parsedList = new ArrayList<>();
        for (String part : stringParts) {
            String trimmedPart = part.strip();
            if (!trimmedPart.isEmpty()) {
                try {
                    int value = Integer.parseInt(trimmedPart);
                    parsedList.add(value);
                } catch (NumberFormatException e) {
                    throw new InvalidDataFormatException("Cannot parse integer from: " + trimmedPart);
                }
            }
        }

        int[] result = new int[parsedList.size()];
        for (int i = 0; i < parsedList.size(); i++) {
            result[i] = parsedList.get(i);
        }
        return result;
    }
}