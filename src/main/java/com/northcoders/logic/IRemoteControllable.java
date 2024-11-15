package com.northcoders.logic;

import com.northcoders.Position;

public interface IRemoteControllable <R>{
// TODO: CHange rotate to return new direction facing CompassDirection
// TODO: moveFOrward to update robot as well as return new Position, rather than have to call getPosition

    public Position moveForward() throws UnknownCompassDirectionException, UnknownRoverCurrentPosException;
    public boolean rotate(RotateDirection direction);
    public Position getCurrentPosition();

    default void peekBackward() {
        System.out.println("Backward functionality not provided by default.");
    }

    default void moveBackward() {
        System.out.println("Backward functionality not provided by default.");
    }

}
