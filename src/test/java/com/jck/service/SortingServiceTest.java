package com.jck.service;

import com.jck.service.impl.SortingServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortingServiceTest {
    // Rule 26: Test objects as constants
    private static final int[] EMPTY_ARRAY = {};
    private static final int[] UNSORTED_ARRAY = {5, 2, 8, 1, 9, 3};
    private static final int[] EXPECTED_SORTED_ARRAY = {1, 2, 3, 5, 8, 9};
    private static final int[] SINGLE_ELEMENT_ARRAY = {42};
    private static final int[] ALREADY_SORTED_ARRAY = {1, 2, 3, 4, 5};
    private static final int[] NEGATIVE_NUMBERS_ARRAY = {-5, 2, -8, 1, 0, 3};
    private static final int[] EXPECTED_NEGATIVE_SORTED = {-8, -5, 0, 1, 2, 3};

    // Rule 27: Use new, not Factory
    private final SortingService service = new SortingServiceImpl();

    // ===== Bubble Sort Tests =====

    @Test
    void sortBubble_validArray_returnsSortedArray() {
        // given (Rule 28)
        int[] input = UNSORTED_ARRAY.clone();

        // when
        int[] result = service.sortBubble(input);

        // then
        assertArrayEquals(EXPECTED_SORTED_ARRAY, result);
    }

    @Test
    void sortBubble_emptyArray_returnsEmptyArray() {
        // given
        int[] input = EMPTY_ARRAY.clone();

        // when
        int[] result = service.sortBubble(input);

        // then
        assertEquals(0, result.length);
    }

    @Test
    void sortBubble_nullInput_returnsNull() {
        // given
        int[] input = null;

        // when
        int[] result = service.sortBubble(input);

        // then
        assertNull(result);
    }

    @Test
    void sortBubble_singleElement_returnsSameArray() {
        // given
        int[] input = SINGLE_ELEMENT_ARRAY.clone();

        // when
        int[] result = service.sortBubble(input);

        // then
        assertArrayEquals(SINGLE_ELEMENT_ARRAY, result);
    }

    @Test
    void sortBubble_alreadySorted_returnsSameArray() {
        // given
        int[] input = ALREADY_SORTED_ARRAY.clone();

        // when
        int[] result = service.sortBubble(input);

        // then
        assertArrayEquals(ALREADY_SORTED_ARRAY, result);
    }

    @Test
    void sortBubble_negativeNumbers_sortsCorrectly() {
        // given
        int[] input = NEGATIVE_NUMBERS_ARRAY.clone();

        // when
        int[] result = service.sortBubble(input);

        // then
        assertArrayEquals(EXPECTED_NEGATIVE_SORTED, result);
    }

    @Test
    void sortBubble_doesNotModifyOriginalArray() {
        // given
        int[] original = UNSORTED_ARRAY.clone();
        int[] expectedOriginal = UNSORTED_ARRAY.clone();

        // when
        service.sortBubble(original);

        // then
        assertArrayEquals(expectedOriginal, original);
    }

    // ===== Quick Sort Tests =====

    @Test
    void sortQuick_validArray_returnsSortedArray() {
        // given
        int[] input = UNSORTED_ARRAY.clone();

        // when
        int[] result = service.sortQuick(input);

        // then
        assertArrayEquals(EXPECTED_SORTED_ARRAY, result);
    }

    @Test
    void sortQuick_emptyArray_returnsEmptyArray() {
        // given
        int[] input = EMPTY_ARRAY.clone();

        // when
        int[] result = service.sortQuick(input);

        // then
        assertEquals(0, result.length);
    }

    @Test
    void sortQuick_nullInput_returnsNull() {
        // given
        int[] input = null;

        // when
        int[] result = service.sortQuick(input);

        // then
        assertNull(result);
    }

    @Test
    void sortQuick_singleElement_returnsSameArray() {
        // given
        int[] input = SINGLE_ELEMENT_ARRAY.clone();

        // when
        int[] result = service.sortQuick(input);

        // then
        assertArrayEquals(SINGLE_ELEMENT_ARRAY, result);
    }

    @Test
    void sortQuick_alreadySorted_returnsSameArray() {
        // given
        int[] input = ALREADY_SORTED_ARRAY.clone();

        // when
        int[] result = service.sortQuick(input);

        // then
        assertArrayEquals(ALREADY_SORTED_ARRAY, result);
    }

    @Test
    void sortQuick_negativeNumbers_sortsCorrectly() {
        // given
        int[] input = NEGATIVE_NUMBERS_ARRAY.clone();

        // when
        int[] result = service.sortQuick(input);

        // then
        assertArrayEquals(EXPECTED_NEGATIVE_SORTED, result);
    }

    @Test
    void sortQuick_doesNotModifyOriginalArray() {
        // given
        int[] original = UNSORTED_ARRAY.clone();
        int[] expectedOriginal = UNSORTED_ARRAY.clone();

        // when
        service.sortQuick(original);

        // then
        assertArrayEquals(expectedOriginal, original);
    }

    // ===== Integration Tests =====

    @Test
    void bothAlgorithms_produceSameResult() {
        // given
        int[] inputForBubble = UNSORTED_ARRAY.clone();
        int[] inputForQuick = UNSORTED_ARRAY.clone();

        // when
        int[] bubbleResult = service.sortBubble(inputForBubble);
        int[] quickResult = service.sortQuick(inputForQuick);

        // then
        assertArrayEquals(bubbleResult, quickResult);
    }

    @Test
    void bothAlgorithms_handleLargeArray() {
        // given
        int[] largeArray = {100, 50, 75, 25, 90, 10, 60, 40, 80, 30};
        int[] expectedLarge = {10, 25, 30, 40, 50, 60, 75, 80, 90, 100};
        int[] inputForBubble = largeArray.clone();
        int[] inputForQuick = largeArray.clone();

        // when
        int[] bubbleResult = service.sortBubble(inputForBubble);
        int[] quickResult = service.sortQuick(inputForQuick);

        // then
        assertArrayEquals(expectedLarge, bubbleResult);
        assertArrayEquals(expectedLarge, quickResult);
    }
}