package org.wahlzeit.model;

import java.lang.Math;

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

    /**
     * @param coordinate
     */
    public double getDistance(Coordinate coordinate) {
        return Math.sqrt((coordinate.getX() - x)*(coordinate.getX() - x) + (coordinate.getY() - y)*(coordinate.getY() - y)
                + (coordinate.getZ() - z)*(coordinate.getZ() - z));
    }

    /**
     * @param coordinate
     */
    public boolean isEqual(Coordinate coordinate) {
        if(coordinate.getX() == x && coordinate.getY() == y && coordinate.getZ() == z) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param obj
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        if(this == obj) {
            return true;
        }
        return isEqual((Coordinate) obj);
    }


}
