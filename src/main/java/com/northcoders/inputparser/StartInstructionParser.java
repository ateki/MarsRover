package com.northcoders.inputparser;

import com.northcoders.CompassDirection;
import com.northcoders.Position;


public class StartInstructionParser implements Parsable<Position> {

      int parseCoord(String coord) {

        try {
            if (coord==null || coord.isEmpty()) {
                throw new IllegalArgumentException("Invalid coord input to parse method. Expecting integer.");
            }
            int coordinateInt = Integer.parseInt(coord.trim());
            if (coordinateInt <= 0) {
                throw new IllegalArgumentException("Invalid coordinate input to parse method. Expecting integer greater than 0.");
            }
            return coordinateInt;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid coord input to parse method. Expecting integer.");
        }
    }


     CompassDirection parseDirection(String direction) {

        if (direction == null || direction.isEmpty()) {
            throw new IllegalArgumentException("Invalid direction input to parse method.  Expecting valid Compass direction.");
        }

        if (direction.length() != 1) {
            throw new IllegalArgumentException();
        }

        // TODO: Add to utility class or to Enum class
         boolean validCompassDirection = false;
         CompassDirection matchingCompassDirection = null;

         for (CompassDirection cd : CompassDirection.values()) {
            System.out.println(cd);
            if (direction.equalsIgnoreCase(cd.name())) {
                // found enum
                matchingCompassDirection = cd;
                validCompassDirection = true;
                break;
            }
        }

        if (!validCompassDirection) {
            throw new IllegalArgumentException("Not valid compass direction enum name supplied.");
        }


        return matchingCompassDirection;

    }

    public Position parse(String input) {
        System.out.println(this.getClass().getSimpleName()+  ".parse");


        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input to parse method. Null or empty string not valid.");
        }

        String[] inputs = input.trim().split("\\s+");  // ignore any trailing/leading whitespace
        if (inputs.length != 3) {
            throw new IllegalArgumentException("Invalid input to parse method. Two integers and one CompassDirection char required.");
        }


        int x;
        int y;
        char inputDirection;
        CompassDirection direction;

        try {
            x = parseCoord(inputs[0]);
            y = parseCoord(inputs[1]);
            direction = parseDirection(inputs[2]);

//            if (x < 0 || y < 0) {
//                throw new IllegalArgumentException("Invalid input to parse method.  Expecting positive whole numbers, negative numbers were given.");
//            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input to parse method. Expecting 2 whole numbers followed by valid Compass Direction delimited by one space.");
        }


        return new Position(x,y,direction);
    }
}
