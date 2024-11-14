package com.northcoders.inputparser;

import com.northcoders.PlateauSize;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Currently sets upper right coordinates, thus determining size of area
 * from 0,0 to input coords passed to Init Instruction
 *
 * TODO: What if diff shaped ares
 * TODO: Poss rename to PlayingAreaSize as may have diff types of playing areas?
 */
public class InitInstructionParser implements Parsable<PlateauSize> {

    /**
     * Parses input string provided into 2 integers, seperated by one space into x,y coordinates
     * Using these coordinates the parser then passes to return Plateau Size.

     * @param input String should be of format "<Integer> "</Integer>" where integers
     *              are upper X,Y coordinates of Plateau area. Note one space only accepted as valid.
     *
     * @return PlateauSize object given X,Y coords of upper right of PlateauArea.
     *
     * @throws IllegalArgumentException thrown for cases of invalid input:
     * such as  null/empty string, strings with other chars or any strings that do not confirm to strict requiremetns of "<Integer> </Integer>"
     *
     */
    public PlateauSize parse(String input) throws IllegalArgumentException {
        System.out.println(this.getClass().getSimpleName()+  ".parse");

        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input to parse method. Null or empty string not valid.");
        }


        String[] numbers = input.trim().split("\\s+");  // ignore any trailing/leading whitespace
        if (numbers.length != 2) {
            throw new IllegalArgumentException("Invalid input to parse method. Two integers required.");
        }

        int x;
        int y;
        try {
             x = Integer.parseInt(numbers[0]);
             y = Integer.parseInt(numbers[1]);

            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("Invalid input to parse method.  Expecting positive whole numbers, negative numbers were given.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input to parse method. Expecting 2 whole numbers delimited by one space.");
        }

        // for now (x,y)  coords are  measured from Origin (0,0) to calc length and width
        return new PlateauSize(x, y);
    }


}
