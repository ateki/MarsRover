package com.northcoders;

/**
 * Position represented by
 *  x,y coordinates as well as direction facing.
 *  
 *  // TODO Could we do as a record?
 */
public class Position {
    private int x;
    private int y;
    private CompassDirection facing;

    public Position(int x, int y, CompassDirection facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CompassDirection getFacing() {
        return facing;
    }

    public void setFacing(CompassDirection facing) {
        this.facing = facing;
    }

    public boolean equals(Position p) {
        return (p.getX()== this.x &&
                p.getY() == this.y &&
                p.getFacing() == this.facing);
    }
}
