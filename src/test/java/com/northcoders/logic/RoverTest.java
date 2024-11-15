package com.northcoders.logic;

import com.northcoders.CompassDirection;
import com.northcoders.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("Returning valid CompassDirections when testing to rotate left from all valid starting facing directions")
    void peekToRotate_LeftWithAllStartingDirections() {
        Rover testRover = new Rover();

        Map<CompassDirection, CompassDirection> mapResults
                = new HashMap<>();
        // starting direction facing,  expected direction after rotate
        mapResults.put(CompassDirection.N, CompassDirection.W);
        mapResults.put(CompassDirection.S, CompassDirection.E);
        mapResults.put(CompassDirection.E, CompassDirection.N);
        mapResults.put(CompassDirection.W, CompassDirection.S);

        mapResults.forEach((facing, expected) ->
        {
            try {
                assertEquals(
                        testRover.peekToRotate(RotateDirection.LEFT, facing), expected
                );

            } catch (UnknownRotateDirectionException | UnknownCompassDirectionException e) {
                fail("Exception thrown: " + e.getMessage());
            }
        });
    }


    @Test
    @DisplayName("Returning valid CompassDirections when testing to rotate RIGHT from all valid starting facing directions")
    void peekToRotate_RightWithAllStartingDirections() {
        Rover testRover = new Rover();

        Map<CompassDirection, CompassDirection> mapResults
                = new HashMap<>();
        // starting direction facing,  expected direction after rotate
        mapResults.put(CompassDirection.N, CompassDirection.E);
        mapResults.put(CompassDirection.S, CompassDirection.W);
        mapResults.put(CompassDirection.E, CompassDirection.S);
        mapResults.put(CompassDirection.W, CompassDirection.N);

        mapResults.forEach((facing, expected) ->
        {
            try {
                assertEquals(
                        testRover.peekToRotate(RotateDirection.RIGHT, facing), expected
                );

            } catch (UnknownRotateDirectionException | UnknownCompassDirectionException e) {
                fail("Exception thrown: " + e.getMessage());
            }
        });
    }


    @Test
    @DisplayName("Returning UnknownCompassDirectionException if null facing is provided")
    void peekToRotate_WithNullStartingDirection() {
        Rover testRover = new Rover();
        CompassDirection nullFacing = null;

        try {
            testRover.peekToRotate(RotateDirection.RIGHT, nullFacing);

            fail("UnknownCompassDirectionException expected due to null facing");

        } catch (UnknownCompassDirectionException e) {
            assertEquals("No facing direction provided: unable to determine starting direction facing.", e.getMessage());

        } catch (Exception e) {
            fail("UnknownCompassDirectionException expected due to null facing");
        }
    }

    @Test
    @DisplayName("Returning UnknownRotateDirectionException if null direction is provided")
    void peekToRotate_WithNullRotationDirection() {
        Rover testRover = new Rover();
        RotateDirection nullDirection = null;

        try {
            testRover.peekToRotate(nullDirection, CompassDirection.N);
            fail("No instruction provided: unable to determine if to look right or left.");

        } catch (UnknownRotateDirectionException e) {
            assertEquals("No instruction provided: unable to determine if to look right or left.", e.getMessage());

        } catch (Exception e) {
            fail("No instruction provided: unable to determine if to look right or left.");
        }
    }


    @Test
    @DisplayName("Returning rover facing CompassDirection West when starting facing North and asked to turn left")
    void rotate_Left_WithRoverStartingFacingNorth() {

        Rover testRover = new Rover();

        CompassDirection startedFacingNorth = CompassDirection.N;
        RotateDirection directionToRotate = RotateDirection.LEFT;
        CompassDirection expectedFacingWest = CompassDirection.W;

        Position startingPos = new Position(1, 1, startedFacingNorth);
        testRover.setStartingPos(startingPos);


        if (!testRover.rotate(directionToRotate)) {
            fail("Expected to rotate successfully.");
        }

        Position newPosition = testRover.getCurrentPosition();

        assertNotNull(newPosition);
        assertEquals(newPosition.getFacing(), expectedFacingWest);
    }


    @Test
    @DisplayName("Returning True and rover facing CompassDirection.N (North) when starting facing West and asked to turn RIGHT")
    void rotate_Right_WithRoverStartingFacingNorth() {

        Rover testRover = new Rover();

        CompassDirection startedFacingWest = CompassDirection.W;
        RotateDirection directionToRotate = RotateDirection.RIGHT;
        CompassDirection expectedFacingNorth = CompassDirection.N;

        Position startingPos = new Position(1, 1, startedFacingWest);
        testRover.setStartingPos(startingPos);


        if (!testRover.rotate(directionToRotate)) {
            fail("Expected to rotate successfully.");
        }

        Position newPosition = testRover.getCurrentPosition();

        assertNotNull(newPosition);
        assertEquals(expectedFacingNorth, newPosition.getFacing());
    }

    @Test
    @DisplayName("Returning False when Robot not initialised with starting position before being asked to rotate ")
    void rotateLeft_WithNullInputFacingDirection() {
        Rover testRover = new Rover();

        CompassDirection startedFacingWest = CompassDirection.W;
        RotateDirection directionToRotate = RotateDirection.RIGHT;
        CompassDirection expectedFacingNorth = CompassDirection.N;
        testRover.clearStartingPosition();  // just to make sure

        boolean successRotate = testRover.rotate(directionToRotate);

        assertFalse(successRotate);
    }


    @Test
    void rover_WithStartingPosition() {
        Position startingPos = new Position(1, 3, CompassDirection.N);
        Rover testRover = new Rover(startingPos);

        assertEquals(CompassDirection.N, testRover.getCurrentPosition().getFacing());
        assertEquals(1, testRover.getCurrentPosition().getX());
        assertEquals(3, testRover.getCurrentPosition().getY());
    }

    @Test
    void clearStartingPosition() {
        Position startingPos = new Position(1, 3, CompassDirection.N);
        Rover testRover = new Rover(startingPos);

        testRover.clearStartingPosition();

        assertNull(testRover.getCurrentPosition());
        assertFalse(testRover.hasPosition());
    }

    @Test
    @DisplayName("Returning true with Rover facing new direction of East after starting facing North before being asked to turn RIGHT")
    void rotateRight_WithStartingPositionNorth() {
        Rover testRover = new Rover();

        CompassDirection startedFacingNorth = CompassDirection.N;
        Position startingPosition = new Position(2, 3, startedFacingNorth);
        CompassDirection expectedFacingEast = CompassDirection.E;
        testRover.setStartingPos(startingPosition);

        boolean successRotate = testRover.rotateRight();
        Position newPosition = testRover.getCurrentPosition();

        assertTrue(successRotate);
        assertEquals(expectedFacingEast, newPosition.getFacing());
        assertEquals(2, newPosition.getX());
        assertEquals(3, newPosition.getY());

    }


    @Test
    @DisplayName("Returning true with Rover facing new direction of East after starting facing North before being asked to turn LEFT")
    void rotateLeft_WithStartingPositionNorth() {
        Rover testRover = new Rover();

        CompassDirection startedFacingNorth = CompassDirection.N;
        Position startingPosition = new Position(2, 3, startedFacingNorth);
        CompassDirection expectedFacingWest = CompassDirection.W;
        testRover.setStartingPos(startingPosition);

        boolean successRotate = testRover.rotateLeft();
        Position newPosition = testRover.getCurrentPosition();

        assertTrue(successRotate);
        assertEquals(expectedFacingWest, newPosition.getFacing());
        assertEquals(2, newPosition.getX());
        assertEquals(3, newPosition.getY());

    }


    @Test
    @DisplayName("Returning position with y coord incremented by 1 when performing lookahead when facing NORTH")
    void lookahead_WithRobotValidStartingPositionFacingNorth()  {
        Position startingPosition = new Position(1,3,CompassDirection.N);
        Rover testRover = new Rover(startingPosition);
        Position expectedPosition = new Position(1,4, CompassDirection.N);
        Position actualPosition = null;

        try {
             actualPosition = testRover.lookahead();
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(expectedPosition.getFacing(), actualPosition.getFacing());
        assertEquals(expectedPosition.getX(), actualPosition.getX());
        assertEquals(expectedPosition.getY(), actualPosition.getY());
    }



    @Test
    @DisplayName("Returning position with y coord decremented by 1 when performing lookahead when facing SOUTH")
    void lookahead_WithRobotValidStartingPositionFacingSouth()  {
        Position startingPosition = new Position(1,3,CompassDirection.S);
        Rover testRover = new Rover(startingPosition);
        Position expectedPosition = new Position(1,2, CompassDirection.S);
        Position actualPosition = null;

        try {
            actualPosition = testRover.lookahead();
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(expectedPosition.getFacing(), actualPosition.getFacing());
        assertEquals(expectedPosition.getX(), actualPosition.getX());
        assertEquals(expectedPosition.getY(), actualPosition.getY());
    }

    @Test
    @DisplayName("Returning position with X coord incremented by 1 when performing lookahead when facing EAST")
    void lookahead_WithRobotValidStartingPositionFacingEast()  {
        Position startingPosition = new Position(1,3,CompassDirection.E);
        Rover testRover = new Rover(startingPosition);
        Position expectedPosition = new Position(2,3, CompassDirection.E);
        Position actualPosition = null;

        try {
            actualPosition = testRover.lookahead();
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(expectedPosition.getFacing(), actualPosition.getFacing());
        assertEquals(expectedPosition.getX(), actualPosition.getX());
        assertEquals(expectedPosition.getY(), actualPosition.getY());
    }

    @Test
    @DisplayName("Returning position with X coord decremented by 1 when performing lookahead when facing WEST")
    void lookahead_WithRobotValidStartingPositionFacingWest()  {
        Position startingPosition = new Position(1,3,CompassDirection.W);
        Rover testRover = new Rover(startingPosition);
        Position expectedPosition = new Position(0,3, CompassDirection.W);
        Position actualPosition = null;

        try {
            actualPosition = testRover.lookahead();
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        //assertEquals(expectedPosition ,actualPosition);
        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(expectedPosition.getFacing(), actualPosition.getFacing());
        assertEquals(expectedPosition.getX(), actualPosition.getX());
        assertEquals(expectedPosition.getY(), actualPosition.getY());
    }


    @Test
    @DisplayName("Returning UnknownRoverCurrentPosException when performing lookahead when no current position available")
    void lookahead_WithRobotNullStartingPoint()  {
        Position startingPosition = null;
        Rover testRover = new Rover(startingPosition);

        try {
            testRover.lookahead();
            fail("Expecting exception as testing with Rover who has null starting point.");
        } catch (UnknownRoverCurrentPosException e) {
            assertEquals("No current position known.", e.getMessage());

        } catch(UnknownCompassDirectionException e) {
            fail("Expecting UnknownRoverCurrentPosException, but instead UnknownCompassDirectionException was thrown.");
        }
    }

    @Test
    @DisplayName("Returning position with X coord decremented by 5 when performing lookahead when facing WEST")
    void lookahead_TenPositions_WithRobotValidStartingPositionFacingWest()  {
        Position startingPosition = new Position(15,3,CompassDirection.W);
        Rover testRover = new Rover(startingPosition);
        Position expectedPosition = new Position(10,3, CompassDirection.W);
        Position actualPosition = null;
        int numPositions = 5;

        try {
            actualPosition = testRover.lookahead(numPositions);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertTrue(expectedPosition.equals(actualPosition));
        assertEquals(expectedPosition.getFacing(), actualPosition.getFacing());
        assertEquals(expectedPosition.getX(), actualPosition.getX());
        assertEquals(expectedPosition.getY(), actualPosition.getY());
    }

}
