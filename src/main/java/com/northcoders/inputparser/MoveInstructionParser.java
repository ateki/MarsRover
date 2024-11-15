package com.northcoders.inputparser;

//TODO implements IParser ???
// Should we have an unknown instruction parser
// TODO: Rename Moves to MovesData

import com.northcoders.Instruction;

import java.util.ArrayList;
import java.util.Arrays;


public class MoveInstructionParser implements Parsable<Moves> {
    public Moves parse(String inputInstruction) {
        return null; // TODO: Use Regexp, user MoveInstructionResult to
        // store error message rather than exception
    };

//    public Moves parse(String input) {
//        System.out.println(this.getClass().getSimpleName()+  ".parse");
//        //return null;    // TODO
//
//        char[] inputChars = input.toCharArray();
//        ArrayList<Instruction> asList = new ArrayList<>(input.length());
//
//        for(Instruction i : inputChars) {
//            // is valid instruction
//
//            asList.add(Instruction.valueOf(i));
//
//        }
//
//
//        return null;
//    }
//
//    public boolean isValidMoves
//

}
