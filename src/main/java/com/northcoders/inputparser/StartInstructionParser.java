package com.northcoders.inputparser;

import com.northcoders.Position;

public class StartInstructionParser implements Parsable<Position> {

    public Position parse(String input) {
        System.out.println(this.getClass().getSimpleName()+  ".parse");
        return null;    // TODO determine default facing position
    }
}
