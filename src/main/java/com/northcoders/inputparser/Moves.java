package com.northcoders.inputparser;

import com.northcoders.Instruction;

import java.util.ArrayList;
import java.util.Iterator;


public class Moves {
    private ArrayList<Instruction> moves = new ArrayList<Instruction>();
    //private ArrayList<char> moves = new ArrayList<Instruction>();

    public void addInstruction(Instruction instruction) {
        moves.add(instruction);
    }

    public void clear() {
            moves.clear();
    }

    public Iterator<Instruction> getIterator() {
        return moves.iterator();
    }
}
