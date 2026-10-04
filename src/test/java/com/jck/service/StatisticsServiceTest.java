package com.jck.service;

import com.jck.entity.IntegerArray;
import com.jck.service.impl.StatisticsServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatisticsServiceTest {
    // Rule 26: Test objects as constants
    private static final int[] EMPTY_ARRAY = {};
    private static final int[] TEST_ARRAY = {3, 1, 4, 1, 5, 9, 2, 6};

    // Rule 27: Use new, not Factory
    private final StatisticsService service = new StatisticsServiceImpl();

    @Test
    void findMin_validArray_returnsMin() {
        // given (Rule 28)
        IntegerArray array = new IntegerArray(TEST_ARRAY);

        // when
        Optional<Integer> result = service.findMin(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(1, result.get());
    }

    @Test
    void findMin_emptyArray_returnsEmpty() {
        // given
        IntegerArray array = new IntegerArray(EMPTY_ARRAY);

        // when
        Optional<Integer> result = service.findMin(array);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void calculateAverage_validArray_returnsCorrectAverage() {
        // given
        IntegerArray array = new IntegerArray(new int[]{2, 4, 6});

        // when
        Optional<Double> result = service.calculateAverage(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(4.0, result.get());
    }
}