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
    @DisplayName("Returning position created from parse input string of integers when 2 integers (delimited by one space) are provided as input string")
    void parse_ValidInputWithTwoIntegers() {
        InitInstructionParser  initParser = new InitInstructionParser();
        String initInstruction = "5 5";
        PlateauSize expectedSize = new PlateauSize(5, 5);

        PlateauSize actualSize = initParser.parse(initInstruction);

        assertEquals(actualSize, expectedSize);
        assertEquals(actualSize.length(), 5);
        assertEquals(actualSize.width(), 5);
    }

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