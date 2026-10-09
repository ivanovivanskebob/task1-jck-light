package com.jck.service;

import com.jck.entity.IntegerArray;
import com.jck.service.impl.StatisticsServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatisticsServiceTest {
    private static final int[] EMPTY_ARRAY = {};
    private static final int[] TEST_ARRAY = {3, 1, 4, 1, 5, 9, 2, 6};
    private static final String TEST_ID = "test_id";
    private static final String TEST_NAME = "test_name";

    private final StatisticsService service = new StatisticsServiceImpl();

    @Test
    void findMin_validArray_returnsMin() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, TEST_ARRAY);

        // when
        OptionalInt result = service.findMin(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(1, result.getAsInt());
    }

    @Test
    void findMin_emptyArray_returnsEmpty() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, EMPTY_ARRAY);

        // when
        OptionalInt result = service.findMin(array);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void findMax_validArray_returnsMax() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, TEST_ARRAY);

        // when
        OptionalInt result = service.findMax(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(9, result.getAsInt());
    }

    @Test
    void findMax_emptyArray_returnsEmpty() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, EMPTY_ARRAY);

        // when
        OptionalInt result = service.findMax(array);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void calculateSum_validArray_returnsSum() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, new int[]{1, 2, 3, 4, 5});

        // when
        OptionalInt result = service.calculateSum(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(15, result.getAsInt());
    }

    @Test
    void calculateSum_emptyArray_returnsEmpty() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, EMPTY_ARRAY);

        // when
        OptionalInt result = service.calculateSum(array);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void calculateAverage_validArray_returnsCorrectAverage() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, new int[]{2, 4, 6});

        // when
        OptionalDouble result = service.calculateAverage(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(4.0, result.getAsDouble());
    }

    @Test
    void calculateAverage_emptyArray_returnsEmpty() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, EMPTY_ARRAY);

        // when
        OptionalDouble result = service.calculateAverage(array);

        // then
        assertTrue(result.isEmpty());
    }
}