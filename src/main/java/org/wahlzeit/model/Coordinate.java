package org.wahlzeit.model;

/**
 * Coordinates in the 3-dimensional cartesian system
 */
public class Coordinate {

    /**
     * cartesian coordinates
     */
    private double x;
    private double y;
    private double z;


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
