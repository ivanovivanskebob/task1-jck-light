package com.jck.reader;

import com.jck.exception.DataReadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArrayReaderImpl implements ArrayReader {
    private static final Logger LOGGER = LogManager.getLogger(ArrayReaderImpl.class);

    @Override
    public List<String> readLines(String filePath) {
        Path path = Paths.get(filePath);
        List<String> lines = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = Files.newBufferedReader(path);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            LOGGER.info("Successfully read {} lines from file", lines.size());
        } catch (IOException e) {
            throw new DataReadingException("Failed to read file: " + filePath, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error("Failed to close reader", e);
                }
            }
        }
        return lines;
    }
}