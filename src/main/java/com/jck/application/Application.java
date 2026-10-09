package com.jck.application;

import com.jck.comparator.*;
import com.jck.entity.*;
import com.jck.exception.DataProcessingException;
import com.jck.factory.ArrayFactory;
import com.jck.factory.IntegerArrayFactory;
import com.jck.reader.*;
import com.jck.repository.ArrayRepository;
import com.jck.specification.BySumGreaterThanSpecification;
import com.jck.validator.*;
import com.jck.warehouse.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    private static final String INPUT_FILE_PATH = "data/input.txt";

    public static void main(String[] args) {
        try {
            processFile();
            demonstrateRepository();
        } catch (DataProcessingException e) {
            LOGGER.error("Application failed: {}", e.getMessage(), e);
        }
    }

    private static void processFile() {
        ArrayReader reader = new ArrayReaderImpl();
        ArrayValidator validator = new ArrayValidatorImpl();
        ArrayFactory factory = new IntegerArrayFactory();

        List<String> lines = reader.readLines(INPUT_FILE_PATH);

        for (String line : lines) {
            boolean isValid = validator.isValid(line);
            if (isValid) {
                factory.create(line);
            } else {
                LOGGER.warn("Invalid data format skipped: {}", line);
            }
        }
    }

    private static void demonstrateRepository() {
        ArrayRepository repository = ArrayRepository.getInstance();
        Warehouse warehouse = Warehouse.getInstance();

        List<IntegerArray> allArrays = repository.findAll();
        LOGGER.info("Total arrays in repository: {}", allArrays.size());

        List<IntegerArray> sortedBySize = repository.findAllSorted(new BySizeComparator());
        LOGGER.info("Arrays sorted by size:");
        for (IntegerArray array : sortedBySize) {
            String id = array.getId();
            WarehouseEntry entry = warehouse.getEntry(id);
            if (entry != null) {
                int sum = entry.getSum();
                int size = array.getSize();
                LOGGER.info("  {} - size: {}, sum: {}", id, size, sum);
            }
        }

        List<IntegerArray> sortedByFirstElement = repository.findAllSorted(new ByFirstElementComparator());
        LOGGER.info("Arrays sorted by first element:");
        for (IntegerArray array : sortedByFirstElement) {
            String id = array.getId();
            int firstElement = array.getFirstElement();
            LOGGER.info("  {} - first element: {}", id, firstElement);
        }

        BySumGreaterThanSpecification spec = new BySumGreaterThanSpecification(10, warehouse);
        List<IntegerArray> filtered = repository.findBySpecification(spec);
        LOGGER.info("Arrays with sum > 10: {}", filtered.size());

        if (!allArrays.isEmpty()) {
            IntegerArray firstArray = allArrays.get(0);
            String firstId = firstArray.getId();
            WarehouseEntry entryBefore = warehouse.getEntry(firstId);
            if (entryBefore != null) {
                int sumBefore = entryBefore.getSum();
                LOGGER.info("Before modification - sum of {}: {}", firstId, sumBefore);
            }
            firstArray.setElement(0, 100);
            WarehouseEntry entryAfter = warehouse.getEntry(firstId);
            if (entryAfter != null) {
                int sumAfter = entryAfter.getSum();
                LOGGER.info("After modification - sum of {}: {}", firstId, sumAfter);
            }
        }
    }
}