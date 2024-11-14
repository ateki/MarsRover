package com.northcoders.inputparser;

import com.northcoders.CompassDirection;
import com.northcoders.PlateauSize;
import com.northcoders.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartInstructionParserTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("Returning int created from parse input string of integer when 1 single digit integer (1-9) is provided.")
    void parseCoord_WithValidSingleDigitInteger() {

        StartInstructionParser  startParser = new StartInstructionParser();
        String coord = "7";
        int expected=7;

        int actual = startParser.parseCoord(coord);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Returning int created from parse input string of integer when 2   digit integers (10>) provided")
    void parseCoord_WithValidMultiDigitInteger() {

        StartInstructionParser  startParser = new StartInstructionParser();
        String coord = "14";
        int expected=14;

        int actual = startParser.parseCoord(coord);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Returning int created from parse input string of integer with trailing white space")
    void parseCoord_WithValidIntegerWithTrailingWhitespace() {

        StartInstructionParser  startParser = new StartInstructionParser();
        String coord = "14         ";
        int expected=14;

        int actual = startParser.parseCoord(coord);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Returning int created from parse input string of integer with leading white space")
    void parseCoord_WithValidIntegerWithLeadingWhitespace() {

        StartInstructionParser  startParser = new StartInstructionParser();
        String coord = "   14";
        int expected = 14;

        int actual = startParser.parseCoord(coord);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Returning IllegalArgumentException created from parse input coordinate represented by invalid char")
    void parseCoord_WithInValidWithChars() {

        StartInstructionParser  startParser = new StartInstructionParser();
        String invalidCoord = "   Fourteen";

        try {
            startParser.parseCoord(invalidCoord);
            fail("Expecting IllegalArgumentException for attempting to parse word for a coordinate.");

        } catch(IllegalArgumentException ex) {
            assertEquals("Invalid coord input to parse method. Expecting integer.",  ex.getMessage());
        }

    }

    @Test
    @DisplayName("Returning IllegalArgumentException created from parse input coordinate when null input")
    void parseCoord_WithInValidNullString() {

        StartInstructionParser  startParser = new StartInstructionParser();
        String nullInput = null;

        try {
            startParser.parseCoord(nullInput);
            fail("Expecting IllegalArgumentException for attempting to parse word for a coordinate.");

        } catch(IllegalArgumentException ex) {
            assertEquals("Invalid coord input to parse method. Expecting integer.",  ex.getMessage());
        }

    }

    @Test
    @DisplayName("Returning IllegalArgumentException created from parse input coordinate when empty string provided")
    void parseCoord_WithInValidEmptyString() {

        StartInstructionParser  startParser = new StartInstructionParser();
        String emptyString = "";

        try {
            startParser.parseCoord(emptyString);
            fail("Expecting IllegalArgumentException for attempting to parse word for a coordinate.");

        } catch(IllegalArgumentException ex) {
            assertEquals("Invalid coord input to parse method. Expecting integer.",  ex.getMessage());
        }

    }

    @Test
    @DisplayName("Returning CompassDirection.N from parsing input string when valid upper case direction N supplied")
    void parseDirection_WithValidUpperCaseDirection()  {

        StartInstructionParser  startParser = new StartInstructionParser();
        String validDirection = "N";
        CompassDirection expectedDirection = CompassDirection.N;

        CompassDirection actualDirection = startParser.parseDirection(validDirection);

        assertEquals(expectedDirection, actualDirection);
    }

    @Test
    @DisplayName("Returning CompassDirection.E from parsing input string when valid lower case direction e supplied")
    void parseDirection_WithValidLowerCaseCharDirection()  {

        StartInstructionParser  startParser = new StartInstructionParser();
        String validDirection = "e";
        CompassDirection expectedDirection = CompassDirection.E;

        CompassDirection actualDirection = startParser.parseDirection(validDirection);
        assertEquals(expectedDirection, actualDirection);
    }

    @Test
    @DisplayName("Returning CompassDirection from parsing input strings grouping tests for all valid upper case direction chars (N,S,W,E)")
    void parseDirection_WithAllValidDirectionStrings()  {

        StartInstructionParser  startParser = new StartInstructionParser();
        String test1DirectionN = "N";
        String test2DirectionS = "S";
        String test3DirectionE = "E";
        String test4DirectionW = "W";


        CompassDirection expectedDirectionN = CompassDirection.N;
        CompassDirection expectedDirectionS = CompassDirection.S;
        CompassDirection expectedDirectionE = CompassDirection.E;
        CompassDirection expectedDirectionW = CompassDirection.W;

        CompassDirection actualTest1DirectionN = startParser.parseDirection(test1DirectionN);
        CompassDirection actualTest2DirectionS = startParser.parseDirection(test2DirectionS);
        CompassDirection actualTest3DirectionE = startParser.parseDirection(test3DirectionE);
        CompassDirection actualTest4DirectionW = startParser.parseDirection(test4DirectionW);

        assertAll(
                "Grouped Assertions on parse with valid compass directions:",

                () -> assertEquals(expectedDirectionN, actualTest1DirectionN),
                () -> assertEquals(expectedDirectionS, actualTest2DirectionS),
                () -> assertEquals(expectedDirectionE, actualTest3DirectionE),
                () -> assertEquals(expectedDirectionW, actualTest4DirectionW)
        );

    }


    @Test
    @DisplayName("Returning IllegalArgumentException from parsing direction input string when null string is provided")
    void parseDirection_WithInvalidNullDirectionString()  {

        StartInstructionParser  startParser = new StartInstructionParser();
        String nullString = null;

        try {
            startParser.parseDirection(nullString);
            fail("IllegalArgumentException expected: ");
        } catch (IllegalArgumentException ex) {
            assertEquals("Invalid direction input to parse method.  Expecting valid Compass direction.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Returning IllegalArgumentException from parsing direction input string when empty string is provided")
    void parseDirection_WithInvalidEmptyDirectionString()  {

        StartInstructionParser  startParser = new StartInstructionParser();
        String emptyString = "";

        try {
            startParser.parseDirection(emptyString);
            fail("IllegalArgumentException expected: ");
        } catch (IllegalArgumentException ex) {
            assertEquals("Invalid direction input to parse method.  Expecting valid Compass direction.", ex.getMessage());
        }
    }


    @Test
    @DisplayName("Returning IllegalArgumentException from parsing direction input string when invalid compass direction 'Z' string is provided")
    void parseDirection_WithInvalidDirectionString()  {

        StartInstructionParser  startParser = new StartInstructionParser();
        String invalidDirection = "Z";

        try {
            startParser.parseDirection(invalidDirection);
            fail("IllegalArgumentException expected: ");
        } catch (IllegalArgumentException ex) {
            assertEquals("Not valid compass direction enum name supplied.", ex.getMessage());
        }
    }


    @Test
    @DisplayName("Returning Position created from parse input string of integers when 2 single digit integers (1-9) and valid uppercase char representing CompassDirection  (delimited by one space) are provided as input string")
    void parse_ValidInputWithSingleDigitCoordsAndUpperCaseCompassDirection() {
        StartInstructionParser  startParser = new StartInstructionParser();
        String initInstruction = "7 8 N";
        Position expectedPosition = new Position(7, 8, CompassDirection.N);

        Position actualPosition = startParser.parse(initInstruction);

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(actualPosition.getFacing(), CompassDirection.N);
        assertEquals(actualPosition.getX(), 7);
        assertEquals(actualPosition.getY(), 8);
    }

    @Test
    @DisplayName("Returning position created from parse input string of integers when 2 single digit integers (1-9) and valid lowercase char representing CompassDirection  (delimited by one space) are provided as input string")
    void parse_ValidInputWithLowerCaseCompassDirection() {
        StartInstructionParser  startParser = new StartInstructionParser();
        String initInstruction = "7 8 n";
        Position expectedPosition = new Position(7, 8, CompassDirection.N);

        Position actualPosition = startParser.parse(initInstruction);

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(actualPosition.getFacing(), CompassDirection.N);
        assertEquals(actualPosition.getX(), 7);
        assertEquals(actualPosition.getY(), 8);
    }

    @Test
    @DisplayName("Returning position created from parse input string of integers when 2 multi digit integers (10 or greater)  and valid CompassDirection (delimited by one space) are provided as input string")
    void parse_ValidInputWithTwoMultiDigitIntegers() {
        StartInstructionParser  startParser = new StartInstructionParser();
        String initInstruction = "15 11 S";
        Position expectedPosition = new Position(15, 11, CompassDirection.S);

        Position actualPosition = startParser.parse(initInstruction);

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(actualPosition.getFacing(), CompassDirection.S);
        assertEquals(actualPosition.getX(), 15);
        assertEquals(actualPosition.getY(), 11);

    }

    @Test
    @DisplayName("Returning position created from parse valid input string of integers for coordinates and CompassDirection with leading whitespace")
    void parse_ValidInputWithLeadingWhiteSpace() {
        StartInstructionParser  startParser = new StartInstructionParser();
        String initInstruction = "   7 8 W";
        Position expectedPosition = new Position(7, 8, CompassDirection.W);

        Position actualPosition = startParser.parse(initInstruction);

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(actualPosition.getFacing(), CompassDirection.W);
        assertEquals(actualPosition.getX(), 7);
        assertEquals(actualPosition.getY(), 8);

    }

    @Test
    @DisplayName("Returning position created from parse valid input string of integers for coordinates and CompassDirection with trailing whitespace")
    void parse_ValidInputWithTrailingWhiteSpace() {
        StartInstructionParser  startParser = new StartInstructionParser();
        String initInstruction = "7 8 W     ";
        Position expectedPosition = new Position(7, 8, CompassDirection.W);

        Position actualPosition = startParser.parse(initInstruction);

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(actualPosition.getFacing(), CompassDirection.W);
        assertEquals(actualPosition.getX(), 7);
        assertEquals(actualPosition.getY(), 8);
    }


    @Test
    @DisplayName("Returning position created from parse valid input string of integers for coordinates and CompassDirection with whitespace delimited of diff lengths")
    void parse_ValidInputWithDifferingWhitespaceDelimiters() {
        // TODO: Decide if want to allow differing lengths of whitespace as delimiters.  Apply consistently to all parsers
        StartInstructionParser  startParser = new StartInstructionParser();
        String initInstruction = "7    8           W";
        Position expectedPosition = new Position(7, 8, CompassDirection.W);

        Position actualPosition = startParser.parse(initInstruction);

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(actualPosition.getFacing(), CompassDirection.W);
        assertEquals(actualPosition.getX(), 7);
        assertEquals(actualPosition.getY(), 8);
    }


    @Test
    @DisplayName("Returning position created from parse input string of integers when 2 multi digit integers (10 or greater)  and valid CompassDirection (delimited by one space) are provided as input string")
    void parse_ValidInputWithDifferentCompassDirections() {
        StartInstructionParser  startParser = new StartInstructionParser();
        String coordinatesInstruction = " 15 11 ";
        String instruction1FaceNorth = coordinatesInstruction + "N";
        String instruction2FaceSouth = coordinatesInstruction + "S";
        String instruction3FaceEast = coordinatesInstruction + "E";
        String instruction4FaceWest = coordinatesInstruction + "W";

        Position expectedPosition1 = new Position(15, 11, CompassDirection.N);
        Position expectedPosition2 = new Position(15, 11, CompassDirection.S);
        Position expectedPosition3 = new Position(15, 11, CompassDirection.E);
        Position expectedPosition4 = new Position(15, 11, CompassDirection.W);

        Position actualPosition1 = startParser.parse(instruction1FaceNorth);
        Position actualPosition2 = startParser.parse(instruction2FaceSouth);
        Position actualPosition3 = startParser.parse(instruction3FaceEast);
        Position actualPosition4 = startParser.parse(instruction4FaceWest);

        assertAll(
                "Grouped Assertions on parse with valid compass directions:",

                () -> assertTrue(expectedPosition1.equals(actualPosition1)),
                () -> assertEquals(actualPosition1.getFacing(), CompassDirection.N),
//                () -> assertEquals(actualPosition1.getX(), 15),
//                () -> assertEquals(actualPosition1.getY(), 11),
                () -> assertTrue(expectedPosition2.equals(actualPosition2)),
                () -> assertEquals(actualPosition2.getFacing(), CompassDirection.S),
//                () -> assertEquals(actualPosition2.getX(), 15),
//                () -> assertEquals(actualPosition3.getY(), 11),
                () -> assertTrue(expectedPosition3.equals(actualPosition3)),
                () -> assertEquals(actualPosition3.getFacing(), CompassDirection.E),
//                () -> assertEquals(actualPosition3.getX(), 15),
//                () -> assertEquals(actualPosition3.getY(), 11),
                () -> assertTrue(expectedPosition4.equals(actualPosition4)),
                () -> assertEquals(actualPosition4.getFacing(), CompassDirection.W)
//                () -> assertEquals(actualPosition4.getX(), 15),
//                () -> assertEquals(actualPosition4.getY(), 11)
        );
    }



    @Test
    @DisplayName("Returning IllegalArugmentException when input string provided has 2  valid integers but invalid CompassDirection")
    void parse_InvalidInputWithInvalidCompassDirections() {
        StartInstructionParser startParser = new StartInstructionParser();
        String validCoords = "15 11";
        String invalidCompassDirection = "Z";
        String invalidInstruction = validCoords + " " + invalidCompassDirection;

        try {
            startParser.parse(invalidInstruction);
            fail("IllegalArgumentException expected when pass invalid input to parse.");

        } catch (IllegalArgumentException e) {
            assertEquals("Not valid compass direction enum name supplied.", e.getMessage());
        }
    }


    @Test
    @DisplayName("Returning IllegalArgumentException when input string provided has chars instead of integers as coordinates but valid compass direction")
    void parse_InvalidInputWithCharsForCoordinates() {
        StartInstructionParser startParser = new StartInstructionParser();
        String startInstruction = "A D N";

        try {
            startParser.parse(startInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid coord input to parse method. Expecting integer.", e.getMessage());
        }
    }



    @Test
    @DisplayName("Returning IllegalArgumentException when input string too long - providing more than valid instruction ")
    void parse_InvalidInputWithInputTooLong() {
        StartInstructionParser startParser = new StartInstructionParser();
        String startInstruction = "1 2 N E extra chars";

        try {
            startParser.parse(startInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input to parse method. Two integers and one CompassDirection char required.", e.getMessage());
        }
    }


    @Test
    @DisplayName("Returning IllegalArgumentException when input string too short with only one digit- not providing enough information  ")
    void parse_InvalidInputWithInputTooShort() {
        StartInstructionParser startParser = new StartInstructionParser();
        String startInstruction = "1 ";

        try {
            startParser.parse(startInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input to parse method. Two integers and one CompassDirection char required.", e.getMessage());
        }
    }

    //

    @Test
    @DisplayName("Returning IllegalArgumentException when input string too short with only valid coordinates but missing CompassDirection")
    void parse_InvalidInputWithInputTooShortMissingCompassDirection() {
        StartInstructionParser startParser = new StartInstructionParser();
        String startInstruction = "1 3";

        try {
            startParser.parse(startInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input to parse method. Two integers and one CompassDirection char required.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Returning IllegalArgumentException when null input string provided")
    void parse_InvalidInputWithNullString() {
        StartInstructionParser startParser = new StartInstructionParser();
        String nullInstruction = null;

        try {
            startParser.parse(nullInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input to parse method. Null or empty string not valid.", e.getMessage());
        }
    }


    @Test
    @DisplayName("Returning IllegalArgumentException when input string provided is empty")
    void parse_InvalidInputWithEmptyString() {
        StartInstructionParser startParser = new StartInstructionParser();
        String emptyInstruction = "";

        try {
            startParser.parse(emptyInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input to parse method. Null or empty string not valid.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Returning position created from parse input string of integers when 2 integers (delimited by one space) are provided as input string")
    void parse_ValidInputWithCoordinatesAndFacingDirection() {
        InitInstructionParser  initParser = new InitInstructionParser();
        String initInstruction = "5 6";
        PlateauSize expectedSize = new PlateauSize(5, 6);

        PlateauSize actualSize = initParser.parse(initInstruction);

        assertEquals(actualSize.getWidth(), 5);
        assertEquals(actualSize.getLength(), 6);
        assertEquals(actualSize.getUpperX(), 5);
        assertEquals(actualSize.getUpperY(), 6);

    }




    @Test
    @DisplayName("Returning IllegalArgumentException when input string provided has an negative integer as coordinate")
    void parse_InvalidInputWithNegativeCoordinate() {
        InitInstructionParser initParser = new InitInstructionParser();
        String initInstruction = "5 -8 N";

        try {
            PlateauSize actualSize = initParser.parse(initInstruction);
            fail("Expecting IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input to parse method. Two integers required.", e.getMessage());
        }
    }



}