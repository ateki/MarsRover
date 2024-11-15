package com.northcoders.logic;

// TODO Rename NullRoverCurrentPosition  NullPosition UnknownPosition
public class UnknownRoverCurrentPosException extends Exception {
    public UnknownRoverCurrentPosException(String errorMessage) {
        super(errorMessage);
    }
}
