package com.jck.reader;

import com.jck.exception.DataReadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalFileReaderService implements FileReaderService {
    private static final Logger LOGGER = LogManager.getLogger(LocalFileReaderService.class);

    @Override
    public List<String> readLines(String filePath) {
        // Rule 13: Relative paths only
        Path path = Paths.get(filePath);
        List<String> lines = new ArrayList<>();
        BufferedReader reader = null;

        try {
            // Rule: Java 7+ methods
            reader = Files.newBufferedReader(path);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            LOGGER.info("Successfully read {} lines from file", lines.size());
        } catch (IOException e) {
            // Rule 4 & 6: Throw custom exception, don't catch immediately
            throw new DataReadingException("Failed to read file: " + filePath, e);
        } finally {
            // Rule 7: close in finally
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