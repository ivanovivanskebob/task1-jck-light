package com.jck.parser;

import com.jck.exception.InvalidDataFormatException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayParserImplTest {
    private static final String VALID_LINE_COMMAS = "1, 2, 3";
    private static final String VALID_LINE_SEMICOLONS = "1; 2; 3";
    private static final String VALID_LINE_DASHES = "11- 2 - 42";
    private static final String VALID_LINE_SPACES = "5 10 15";
    private static final String VALID_LINE_MIXED = "1, 2; 3 - 4";
    private static final String INVALID_LINE = "1, 2, x3";
    private static final String EMPTY_LINE = "";
    private static final String WHITESPACE_LINE = "   ";
    private static final int[] EXPECTED_123 = {1, 2, 3};
    private static final int[] EXPECTED_11242 = {11, 2, 42};
    private static final int[] EXPECTED_51015 = {5, 10, 15};
    private static final int[] EXPECTED_1234 = {1, 2, 3, 4};

    private final ArrayParser parser = new ArrayParserImpl();

    @Test
    void parse_commaSeparated_returnsCorrectArray() {
        // given
        // when
        int[] result = parser.parse(VALID_LINE_COMMAS);

        // then
        assertArrayEquals(EXPECTED_123, result);
    }

    @Test
    void parse_semicolonSeparated_returnsCorrectArray() {
        // given
        // when
        int[] result = parser.parse(VALID_LINE_SEMICOLONS);

        // then
        assertArrayEquals(EXPECTED_123, result);
    }

    @Test
    void parse_dashSeparated_returnsCorrectArray() {
        // given
        // when
        int[] result = parser.parse(VALID_LINE_DASHES);

        // then
        assertArrayEquals(EXPECTED_11242, result);
    }

    @Test
    void parse_spaceSeparated_returnsCorrectArray() {
        // given
        // when
        int[] result = parser.parse(VALID_LINE_SPACES);

        // then
        assertArrayEquals(EXPECTED_51015, result);
    }

    @Test
    void parse_mixedDelimiters_returnsCorrectArray() {
        // given
        // when
        int[] result = parser.parse(VALID_LINE_MIXED);

        // then
        assertArrayEquals(EXPECTED_1234, result);
    }

    @Test
    void parse_emptyLine_returnsEmptyArray() {
        // given
        // when
        int[] result = parser.parse(EMPTY_LINE);

        // then
        assertEquals(0, result.length);
    }

    @Test
    void parse_whitespaceLine_returnsEmptyArray() {
        // given
        // when
        int[] result = parser.parse(WHITESPACE_LINE);

        // then
        assertEquals(0, result.length);
    }

    @Test
    void parse_nullLine_returnsEmptyArray() {
        // given
        // when
        int[] result = parser.parse(null);

        // then
        assertEquals(0, result.length);
    }

    @Test
    void parse_invalidData_throwsException() {
        // given
        // when & then
        assertThrows(InvalidDataFormatException.class, () -> {
            parser.parse(INVALID_LINE);
        });
    }
}