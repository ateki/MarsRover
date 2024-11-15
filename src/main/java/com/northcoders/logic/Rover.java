package com.northcoders.logic;

import com.northcoders.CompassDirection;
import com.northcoders.Position;

public class Rover  implements IRemoteControllable {

    private boolean hasPosition = false;
    private boolean isInitialised = false;
    private String name;
    private Position currentPosition;
    // TODO: We could split up x,y coord with direction facing
    // if to do that here, do so in constructor



    public Rover() {
        // think should be able to create Rover without placing on a location
    }

    public Rover(Position startingPos) {
        // think should be able to create Rover without placing on a location
        // TODO: validate position else will be 0,0
        this.currentPosition = startingPos;
        this.hasPosition = true;
        this.isInitialised = true;
    }

    public void setStartingPos(Position startingPos) {
        this.currentPosition = startingPos;
        this.hasPosition = true;
        this.isInitialised = true;
    }

    public void clearStartingPosition() {
        this.currentPosition = null;
        this.hasPosition = false;
        this.isInitialised = false;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * TODO: Poss rename lookToRotate in line with future lookahead function
     * Looks ahead to determine what direction would be facing after rotated as per provided instruction
     *
     * @param instruction determines if to rotate Right/Left
     * @param facing determines starting direction facing
     * @return new facing direction once rotated as per instruction from starting facing direction
     *
     * @throws UnknownRotateDirectionException
     * @throws UnknownCompassDirectionException
     */
    protected CompassDirection peekToRotate(RotateDirection instruction, CompassDirection facing) throws UnknownRotateDirectionException, UnknownCompassDirectionException {

        if (facing == null ) {
            throw new UnknownCompassDirectionException("No facing direction provided: unable to determine starting direction facing.");
        }
        if (instruction == null) {
            throw new UnknownRotateDirectionException("No instruction provided: unable to determine if to look right or left.");
        }

        CompassDirection newFacing;

        if (instruction.name().equalsIgnoreCase("LEFT")) {
            // rotate anticlockwise
            switch (facing) {
                case N -> newFacing = CompassDirection.W;
                case S -> newFacing = CompassDirection.E;
                case E -> newFacing = CompassDirection.N;
                case W -> newFacing = CompassDirection.S;
                default -> throw new UnknownCompassDirectionException(
                        "Unexpected compass direction: " + facing.toString());

            }
        } else {
            // rotate clockwise
            switch (facing) {
                case N -> newFacing = CompassDirection.E;
                case S -> newFacing = CompassDirection.W;
                case E -> newFacing = CompassDirection.S;
                case W -> newFacing = CompassDirection.N;
                default -> throw new UnknownCompassDirectionException(
                        "Unexpected compass direction: " + facing.toString());

            }
        }
        return newFacing;
    }

    /**
     * Rotates rover left 90 degrees.
     * @return True for now
     * as no exceptions thrown up.
     * TODO: Need to return RotateResult or at least boolean
     */
    public boolean rotateLeft() {
        return rotate(RotateDirection.LEFT);
    }

    /**
     * Rotates rover right 90 degrees.
     * @return True for now
     * as no exceptions thrown up.
     * TODO: Need to return RotateResult or at least boolean
     */
    public boolean rotateRight() {
        return rotate(RotateDirection.RIGHT);
    }

    /**
     * Given direction Rover is currently facing, determines what direction would be facing
     * if to turn 90 degrees left.  If able to successfully make this move, rotates updating
     * rover's current position with new facing direction.
     *
     * Rover should have been initialised and have starting position before rotating.
     *
     * Note: decision made that Rover does not understand Instructions L R M.  That is the job
     * of controller to take such instructions, interpret them and request Rover to move in certain way.
     *
     * @return boolean for now
     *      Note: if Rover has not yet been initialised, there will be no known starting position for Rover.
     *      In such cases, null will be returned for now.
     * TODO: Decide if need success/error message to feed back
     * Could return Result: success, successMsg, errorMsg hasError
     */
    public boolean rotate(RotateDirection rotateDirection) {

        boolean hasRotated = false;
        try {

            if (hasPosition()) {

                CompassDirection newDirection = peekToRotate(rotateDirection, this.currentPosition.getFacing());

                if (newDirection == null) {
                    System.out.println("Unable to rotate robot left.");
                    // TODO Return result object with error, exception

                } else {
                    updateFacingDirection(newDirection);
                    hasRotated=true;
                }
            } else {
                System.out.println("Robot not initialised. Has not been given a starting position.");
                // TODO Return result object with error, exception

            }


        } catch(UnknownRotateDirectionException | UnknownCompassDirectionException e) {
            System.out.println(e.getMessage());
            // TODO Return result object with error, exception
        }

        return hasRotated;
    }



    protected Position lookahead(int numberOfPositions) throws UnknownRoverCurrentPosException, UnknownCompassDirectionException {

            // take current position as arg or read from rover itself thus no mistakes

            // 1: validate got current pos and robot has been initialised
            Position currentPosition = getCurrentPosition();

            if (numberOfPositions<=0) {
                throw new IllegalArgumentException("Request to handle invalid number of positions");
            }


            if (currentPosition==null) {
                throw new UnknownRoverCurrentPosException("No current position known.");
            }

            int x = currentPosition.getX();
            int y = currentPosition.getY();
            CompassDirection facing = currentPosition.getFacing();

            switch (facing) {
                case N -> y += numberOfPositions;
                case S -> y -= numberOfPositions;
                case E -> x += numberOfPositions;
                case W -> x -= numberOfPositions;
                default -> throw new UnknownCompassDirectionException("Unrecognised compass direction");
            }

            return new Position(x, y, facing);
        }

    /**
     * Ability to look ahed to work out coordinates if move forward.  Return new position
     *  Note this method does not move Rover to new position.  To do this call on move(Position)
     *  This is useful when controller wants to check if new position is empty before moving to it.

     * @return
     * @throws UnknownCompassDirectionException
     */
    // TODO Return Position for err cases with err message
    // TODO: Logger add
    protected Position lookahead() throws UnknownCompassDirectionException, UnknownRoverCurrentPosException {
        return lookahead(1);

        //        // take current position as arg or read from rover itself thus no mistakes
//
//        // 1: validate got current pos and robot has been initialised
//        Position currentPosition = getCurrentPosition();
//
//        if (currentPosition==null) {
//            throw new UnknownRoverCurrentPosException("No current position known.");
//        }
//
//        int x = currentPosition.getX();
//        int y = currentPosition.getY();
//        CompassDirection facing = currentPosition.getFacing();
//
//        switch (facing) {
//            case N -> y += 1;
//            case S -> y -= 1;
//            case E -> x += 1;
//            case W -> x -= 1;
//            default -> throw new UnknownCompassDirectionException("Unrecognised compass direction");
//        }
//
//        return new Position(x, y, facing);
    }



    public Position moveForward() throws UnknownCompassDirectionException, UnknownRoverCurrentPosException {

        if (!hasPosition()) {
            System.out.println("error robot does not have starting position");
            // TODO: Decide how to deal with  C
            //  Could return PositionResult object rather than throw Exception
            throw new UnknownRoverCurrentPosException("Rover has no current position.");
        } else {

            Position newPosition = lookahead();  // get new position
            setCurrentPosition(newPosition);        // TODO What if newPosition not updated for some reason?

            return newPosition;
        }
    }

    private void updateFacingDirection(CompassDirection direction) {
        this.currentPosition.setFacing(direction);
    }

    private void updateCoords(int x, int y) {
        this.currentPosition.setX(x);
        this.currentPosition.setY(y);
    }


    public boolean hasPosition() {
        return hasPosition;
    }

    public void setHasPosition(boolean hasPosition) {
        this.hasPosition = hasPosition;
    }

    public boolean isInitialised() {
        return isInitialised;
    }

    public void setInitialised(boolean initialised) {
        isInitialised = initialised;
    }
}
