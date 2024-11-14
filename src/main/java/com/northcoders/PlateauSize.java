package com.northcoders;

/** Currently plateau is of rectangle shape.
 * TODO: consider if plateau were to be other 2d, 3d or multiD shapes

 */
public class PlateauSize {

    // Upper X Coord
    private int upperX;
    private int upperY;
    private int length;
    private int width;

    // Lower X,Y coordinates f Plateau
    private static final int lowerX = 0;
    private static final int lowerY = 0;

    public PlateauSize(int x, int y) {
        if (x<=0 || y<=0) {
            throw new IllegalArgumentException("Invalid x,y coordinates for upper right coordinate.");
        }
        this.upperX = x;
        this.upperY = y;

        this.width = x-lowerX;
        this.length = y-lowerY;

    }

    public int getUpperX() {
        return upperX;
    }

    public int getUpperY() {
        return upperY;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }


    public boolean equals(PlateauSize p) {
        return (p.upperX == this.upperX &&
                p.upperY == this.upperY &&
                p.lowerX == this.lowerX &&
                p.lowerY == this.lowerY &&
                p.length == this.length &&
                p.width == this.width);
    }
}
