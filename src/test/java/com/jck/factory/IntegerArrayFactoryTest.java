package com.jck.factory;

import com.jck.entity.IntegerArray;
import com.jck.entity.NumberArray;
import com.jck.repository.ArrayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegerArrayFactoryTest {
    private static final String VALID_LINE = "1, 2, 3";
    private static final String EMPTY_LINE = "";
    private static final int[] EXPECTED_VALUES = {1, 2, 3};

    private ArrayFactory factory;
    private ArrayRepository repository;

    @BeforeEach
    void setUp() {
        factory = new IntegerArrayFactory();
        repository = ArrayRepository.getInstance();
        repository.clear();
    }

    @Test
    void create_validLine_returnsIntegerArray() {
        // given
        // when
        NumberArray result = factory.create(VALID_LINE);

        // then
        assertNotNull(result);
        assertTrue(result instanceof IntegerArray);
    }

    @Test
    void create_validLine_setsCorrectValues() {
        // given
        // when
        NumberArray result = factory.create(VALID_LINE);
        IntegerArray intArray = (IntegerArray) result;

        // then
        int[] values = intArray.getValues();
        assertEquals(3, values.length);
        assertEquals(1, values[0]);
        assertEquals(2, values[1]);
        assertEquals(3, values[2]);
    }

    @Test
    void create_validLine_setsIdAndName() {
        // given
        // when
        NumberArray result = factory.create(VALID_LINE);
        IntegerArray intArray = (IntegerArray) result;

        // then
        assertNotNull(intArray.getId());
        assertNotNull(intArray.getName());
        assertTrue(intArray.getId().startsWith("array_"));
    }

    @Test
    void create_validLine_addsToRepository() {
        // given
        // when
        NumberArray result = factory.create(VALID_LINE);
        String id = result.getId();

        // then
        IntegerArray found = repository.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    void create_emptyLine_createsEmptyArray() {
        // given
        // when
        NumberArray result = factory.create(EMPTY_LINE);
        IntegerArray intArray = (IntegerArray) result;

        // then
        assertEquals(0, intArray.getSize());
    }

    @Test
    void create_multipleCalls_generatesUniqueIds() {
        // given
        // when
        NumberArray first = factory.create(VALID_LINE);
        NumberArray second = factory.create(VALID_LINE);

        // then
        String firstId = first.getId();
        String secondId = second.getId();
        assertNotNull(firstId);
        assertNotNull(secondId);
        assertTrue(!firstId.equals(secondId));
    }
}