package com.jck.entity;

import com.jck.observer.Observer;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerArrayTest {
    private static final String TEST_ID = "test_id";
    private static final String TEST_NAME = "test_name";
    private static final int[] TEST_VALUES = {1, 2, 3, 4, 5};
    private static final int[] EMPTY_VALUES = {};
    private static final int NEW_VALUE = 100;
    private static final int MODIFIED_INDEX = 0;

    @Test
    void constructor_validParameters_setsFields() {
        // given
        // when
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, TEST_VALUES);

        // then
        assertEquals(TEST_ID, array.getId());
        assertEquals(TEST_NAME, array.getName());
        assertArrayEquals(TEST_VALUES, array.getValues());
    }

    @Test
    void setElement_validIndex_updatesValue() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, TEST_VALUES.clone());

        // when
        array.setElement(MODIFIED_INDEX, NEW_VALUE);

        // then
        int[] values = array.getValues();
        assertEquals(NEW_VALUE, values[MODIFIED_INDEX]);
    }

    @Test
    void setElement_validIndex_notifiesObservers() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, TEST_VALUES.clone());
        List<Object> receivedSources = new ArrayList<>();
        List<Object> receivedEvents = new ArrayList<>();
        Observer observer = (source, event) -> {
            receivedSources.add(source);
            receivedEvents.add(event);
        };
        array.getObservableDelegate().addObserver(observer);

        // when
        array.setElement(MODIFIED_INDEX, NEW_VALUE);

        // then
        assertEquals(1, receivedSources.size());
        assertEquals(array, receivedSources.get(0));
        assertEquals("elementChanged", receivedEvents.get(0));
    }

    @Test
    void getSize_nonEmptyArray_returnsCorrectSize() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, TEST_VALUES);

        // when
        int size = array.getSize();

        // then
        assertEquals(5, size);
    }

    @Test
    void getSize_emptyArray_returnsZero() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, EMPTY_VALUES);

        // when
        int size = array.getSize();

        // then
        assertEquals(0, size);
    }

    @Test
    void getFirstElement_nonEmptyArray_returnsFirstElement() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, TEST_VALUES);

        // when
        int firstElement = array.getFirstElement();

        // then
        assertEquals(1, firstElement);
    }

    @Test
    void getFirstElement_emptyArray_returnsZero() {
        // given
        IntegerArray array = new IntegerArray(TEST_ID, TEST_NAME, EMPTY_VALUES);

        // when
        int firstElement = array.getFirstElement();

        // then
        assertEquals(0, firstElement);
    }
}