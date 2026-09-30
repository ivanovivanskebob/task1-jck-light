package com.jck.service.impl;

import com.jck.entity.IntegerArray;
import com.jck.service.StatisticsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class StatisticsServiceImpl implements StatisticsService {
    private static final Logger LOGGER = LogManager.getLogger(StatisticsServiceImpl.class);

    @Override
    public Optional<Integer> findMin(IntegerArray array) {
        int[] values = array.getValues();
        if (values == null || values.length == 0) {
            LOGGER.warn("Array is empty, cannot find min");
            return Optional.empty();
        }

        int min = values[0];
        for (int i = 1; i < values.length; i++) {
            int current = values[i];
            if (current < min) {
                min = current;
            }
        }

        return Optional.of(min);
    }

    @Override
    public Optional<Integer> findMax(IntegerArray array) {
        int[] values = array.getValues();
        if (values == null || values.length == 0) {
            LOGGER.warn("Array is empty, cannot find max");
            return Optional.empty();
        }

        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            int current = values[i];
            if (current > max) {
                max = current;
            }
        }

        return Optional.of(max);
    }

    @Override
    public Optional<Integer> calculateSum(IntegerArray array) {
        int[] values = array.getValues();
        if(values == null || values.length == 0) {
            LOGGER.warn("Array is empty, cannot calculate sum");
            return Optional.empty();
        }

        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return Optional.of(sum);
    }

    @Override
    public Optional<Double> calculateAverage(IntegerArray array) {

        int[] values = array.getValues();
        if (values == null || values.length == 0) {
            LOGGER.warn("Array is empty, cannot calculate average");
            return Optional.empty();
        }

        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        double average = (double) sum / values.length;
        return Optional.of(average);
    }
}
