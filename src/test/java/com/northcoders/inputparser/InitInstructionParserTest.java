package com.northcoders.inputparser;

import com.northcoders.CompassDirection;
import com.northcoders.PlateauSize;
import com.northcoders.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitInstructionParserTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("Returning position created from parse input string of integers when 2 single digit integers (1-9)  (delimited by one space) are provided as input string")
    void parse_ValidInputWithTwoSingleDigitIntegers() {
        InitInstructionParser  initParser = new InitInstructionParser();
        String initInstruction = "5 6";
        PlateauSize expectedSize = new PlateauSize(5, 6);

        PlateauSize actualSize = initParser.parse(initInstruction);

        assertTrue(expectedSize.equals(actualSize));
        assertEquals(actualSize.getWidth(), 5);
        assertEquals(actualSize.getLength(), 6);
        assertEquals(actualSize.getUpperX(), 5);
        assertEquals(actualSize.getUpperY(), 6);

    }

    @Test
    @DisplayName("Returning position created from parse input string of integers when 2 multi digit integers (10 or greater)  (delimited by one space) are provided as input string")
    void parse_ValidInputWithTwoMultiDigitIntegers() {
        InitInstructionParser  initParser = new InitInstructionParser();
        String initInstruction = "15 11";
        PlateauSize expectedSize = new PlateauSize(15, 11);

        PlateauSize actualSize = initParser.parse(initInstruction);


        assertTrue(expectedSize.equals(actualSize));
        assertEquals(actualSize.getWidth(), 15);
        assertEquals(actualSize.getLength(), 11);
        assertEquals(actualSize.getUpperX(), 15);
        assertEquals(actualSize.getUpperY(), 11);

    }


    @Test
    @DisplayName("Returning position created from parse valid input string of integers for coordinates and CompassDirection with leading whitespace")
    void parse_ValidInputWithLeadingWhiteSpace() {
        InitInstructionParser  initParser = new InitInstructionParser();
        String initInstruction = "          15 11";
        PlateauSize expectedSize = new PlateauSize(15, 11);

        PlateauSize actualSize = initParser.parse(initInstruction);

        assertTrue(expectedSize.equals(actualSize));

        assertEquals(actualSize.getWidth(), 15);
        assertEquals(actualSize.getLength(), 11);
        assertEquals(actualSize.getUpperX(), 15);
        assertEquals(actualSize.getUpperY(), 11);

    }

    @Test
    @DisplayName("Returning position created from parse valid input string of integers for coordinates and CompassDirection with trailing whitespace")
    void parse_ValidInputWithTrailingWhiteSpace() {
        InitInstructionParser  initParser = new InitInstructionParser();
        String initInstruction = "7 11     ";
        PlateauSize expectedSize = new PlateauSize(7, 11);

        PlateauSize actualSize = initParser.parse(initInstruction);


        assertTrue(expectedSize.equals(actualSize));
        assertEquals(actualSize.getWidth(), 7);
        assertEquals(actualSize.getLength(), 11);
        assertEquals(actualSize.getUpperX(), 7);
        assertEquals(actualSize.getUpperY(), 11);
    }


    @Test
    @DisplayName("Returning position created from parse valid input string of integers for coordinates and CompassDirection with whitespace delimited of diff lengths")
    void parse_ValidInputWithDifferingWhitespaceDelimiters() {
        InitInstructionParser  initParser = new InitInstructionParser();
        String initInstruction = "      7 11";
        PlateauSize expectedSize = new PlateauSize(7, 11);

        PlateauSize actualSize = initParser.parse(initInstruction);


        assertTrue(expectedSize.equals(actualSize));
        assertEquals(actualSize.getWidth(), 7);
        assertEquals(actualSize.getLength(), 11);
        assertEquals(actualSize.getUpperX(), 7);
        assertEquals(actualSize.getUpperY(), 11);
    }

    /**======================*/
    @Test
    @DisplayName("Returning IllegalArgumentException when input string provided has 2 integers, but one is -ve")
    void parse_InvalidInputWithOneNegativeInteger() {
        InitInstructionParser  initParser = new InitInstructionParser();
        String initInstruction = "5 -5";

        try {
            PlateauSize actualSize = initParser.parse(initInstruction);
            fail("Expecting IllegalArgumentException");

        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Invalid input to parse method.  Expecting positive whole numbers, negative numbers were given.");
        }
    }

    @Test
    @DisplayName("Returning IllegalArgumentException when input string provided has two -ve integers,")
    void parse_InvalidInputWithTwoNegativeIntegers() {
        InitInstructionParser initParser = new InitInstructionParser();
        String initInstruction = "-5 -5";

        try {
            PlateauSize actualSize = initParser.parse(initInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input to parse method.  Expecting positive whole numbers, negative numbers were given.");
        }
    }

    @Test
    @DisplayName("Returning IllegalArgumentException when input string provided has only one integer")
    void parse_InvalidInputWithOneIntegerOnly() {
        InitInstructionParser initParser = new InitInstructionParser();
        String initInstruction = "6   ";

        try {
            PlateauSize actualSize = initParser.parse(initInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input to parse method. Two integers required.");
        }
    }

    @Test
    @DisplayName("Returning IllegalArgumentException when input string provided has one integer and one char")
    void parse_InvalidInputWithOneIntegerWithMixedChar() {
        InitInstructionParser initParser = new InitInstructionParser();
        String initInstruction = "6 D";

        try {
            PlateauSize actualSize = initParser.parse(initInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input to parse method. Expecting 2 whole numbers delimited by one space.");
        }
    }

    @Test
    @DisplayName("Returning IllegalArgumentException when input string provided is too long - more than 2 integers")
    void parse_InvalidInputTooManyIntegers() {
        InitInstructionParser initParser = new InitInstructionParser();
        String initInstruction = "6 7 8 9";

        try {
            PlateauSize actualSize = initParser.parse(initInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input to parse method. Two integers required.");
        }

    }

    @Test
    @DisplayName("Returning IllegalArgumentException when input string provided has non integers - 2 chars")
    void parse_InvalidInputWithNonIntegers() {
        InitInstructionParser initParser = new InitInstructionParser();
        String initInstruction = "C D";

        try {
            PlateauSize actualSize = initParser.parse(initInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input to parse method. Expecting 2 whole numbers delimited by one space.");
        }
    }

    @Test
    @DisplayName("Returning IllegalArgumentException when empty input string provided")
    void parse_InvalidInputWithEmptyString() {
        InitInstructionParser initParser = new InitInstructionParser();
        String initInstruction = "  ";

        try {
            PlateauSize actualSize = initParser.parse(initInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input to parse method. Two integers required.");
        }
    }

    @Test
    @DisplayName("Returning IllegalArgumentException when null input string provided")
    void parse_InvalidInputWithNullString() {
        InitInstructionParser initParser = new InitInstructionParser();
        String initInstruction = null;

        try {
            PlateauSize actualSize = initParser.parse(initInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input to parse method. Null or empty string not valid.");
        }
    }
}