package com.northcoders.inputparser;

import com.northcoders.PlateauSize;

/**
 * Currently sets upper right coordinates, thus determining size of area
 * from 0,0 to input coords passed to Init Instruction
 *
 * TODO: What if diff shaped ares
 * TODO: Poss rename to PlayingAreaSize as may have diff types of playing areas?
 */
public class InitInstructionParser {
    public PlateauSize parse(String input) {
        System.out.println(this.getClass().getSimpleName()+  ".parse");
        return null;
    }
}
