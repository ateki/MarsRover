package com.northcoders.inputparser;

//TODO implements IParser ???
// Should we have an unknown instruction parser
// TODO: Rename Moves to MovesData

public class MoveInstructionParser implements Parsable<Moves> {

    public Moves parse(String input) {
        System.out.println(this.getClass().getSimpleName()+  ".parse");
        return null;    // TODO
    }
}
