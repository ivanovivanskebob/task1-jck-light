package com.jck.factory;

import com.jck.entity.IntegerArray;
import com.jck.entity.NumberArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jck.exception.InvalidDataFormatException;

class IntegerArrayParserTest {
    private static final String VALID_LINE = "1, 2; 3 - 4";
    private static final String INVALID_LINE = "1, 2, x3";
    private static final String EMPTY_LINE = "";

    private final ArrayParser parser = new IntegerArrayParser();

    @Test
    void parse_validLine_returnsCorrectArray() {
        // given
        // when
        NumberArray result = parser.parse(VALID_LINE);

        // then
        IntegerArray intArray = (IntegerArray) result;
        int[] values = intArray.getValues();
        assertEquals(4, values.length);
        assertEquals(1, values[0]);
        assertEquals(4, values[3]);
    }

    @Test
    void parse_invalidLine_throwsException() {
        // given
        // when & then
        assertThrows(InvalidDataFormatException.class, () -> {
            parser.parse(INVALID_LINE);
        });
    }

    @Test
    void parse_emptyLine_returnsEmptyArray() {
        // given
        // when
        NumberArray result = parser.parse(EMPTY_LINE);

        // then
        IntegerArray intArray = (IntegerArray) result;
        int[] values = intArray.getValues();
        assertEquals(0, values.length);
    }
}