package com.jck.service.impl;

import com.jck.entity.IntegerArray;
import com.jck.service.StatisticsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class StatisticsServiceImpl implements StatisticsService {
    private static final Logger LOGGER = LogManager.getLogger(StatisticsServiceImpl.class);

    @Override
    public OptionalInt findMin(IntegerArray array) {
        int[] values = array.getValues();
        if (values == null || values.length == 0) {
            LOGGER.warn("Array is empty, cannot find min");
            return OptionalInt.empty();
        }

        int min = values[0];
        for (int i = 1; i < values.length; i++) {
            int current = values[i];
            if (current < min) {
                min = current;
            }
        }

        return OptionalInt.of(min);
    }

    @Override
    public OptionalInt findMax(IntegerArray array) {
        int[] values = array.getValues();
        if (values == null || values.length == 0) {
            LOGGER.warn("Array is empty, cannot find max");
            return OptionalInt.empty();
        }

        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            int current = values[i];
            if (current > max) {
                max = current;
            }
        }

        return OptionalInt.of(max);
    }

    @Override
    public OptionalInt calculateSum(IntegerArray array) {
        int[] values = array.getValues();
        if(values == null || values.length == 0) {
            LOGGER.warn("Array is empty, cannot calculate sum");
            return OptionalInt.empty();
        }

        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return OptionalInt.of(sum);
    }

    @Override
    public OptionalDouble calculateAverage(IntegerArray array) {

        int[] values = array.getValues();
        if (values == null || values.length == 0) {
            LOGGER.warn("Array is empty, cannot calculate average");
            return OptionalDouble.empty();
        }

        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        double average = (double) sum / values.length;
        return OptionalDouble.of(average);
    }
}
