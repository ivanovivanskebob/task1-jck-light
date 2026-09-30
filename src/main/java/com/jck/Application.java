package com.jck;

import com.jck.entity.IntegerArray;
import com.jck.entity.NumberArray;
import com.jck.exception.DataProcessingException;
import com.jck.factory.ArrayParser;
import com.jck.factory.IntegerArrayParser;
import com.jck.reader.FileReaderService;
import com.jck.reader.LocalFileReaderService;
import com.jck.service.StatisticsService;
import com.jck.service.impl.StatisticsServiceImpl;
import com.jck.validator.DataValidator;
import com.jck.validator.DefaultDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    private static final String INPUT_FILE_PATH = "data/input.txt";

    public static void main(String[] args) {
        try {
            processFile();
        } catch (DataProcessingException e) {
            LOGGER.error("Application failed: {}", e.getMessage(), e);
        }
    }

    private static void processFile() {
        FileReaderService reader = new LocalFileReaderService();
        DataValidator validator = new DefaultDataValidator();
        ArrayParser parser = new IntegerArrayParser();
        StatisticsService statsService = new StatisticsServiceImpl();

        List<String> lines = reader.readLines(INPUT_FILE_PATH);

        for (String line : lines) {
            boolean isValid = validator.isValid(line);
            if (isValid) {
                NumberArray array = parser.parse(line);
                processArray(array, statsService);
            } else {
                LOGGER.warn("Invalid data format skipped: {}", line);
            }
        }
    }

    private static void processArray(NumberArray array, StatisticsService service) {
        IntegerArray intArray = (IntegerArray) array;

        Optional<Integer> min = service.findMin(intArray);
        Optional<Integer> max = service.findMax(intArray);
        Optional<Integer> sum = service.calculateSum(intArray);
        Optional<Double> avg = service.calculateAverage(intArray);

        LOGGER.info("Min: {}, Max: {}, Sum: {}, Avg: {}",
                min.orElse(null), max.orElse(null), sum.orElse(null), avg.orElse(null));
    }
}