package org.wahlzeit.model;

/**
 * Coordinates in the 3-dimensional cartesian system
 */
public class Coordinate {

    /**
     * cartesian coordinates
     */
    public double x;
    public double y;
    public double z;


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    /**
     * @methodtype constructor
     */
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
