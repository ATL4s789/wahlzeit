package org.wahlzeit.model;

import java.sql.*;
import java.util.*;

/**
 * Coordinates in the 3-dimensional spherical (longitude, latitude, radius) system
 * This class assumes the polar axis is the z-axis, and the equatorial plane is
 * the XY plane.
 */
public class SphericCoordinate extends AbstractCoordinate {

    /**
     * spherical coordinates
     */
    private double longitude;
    private double latitude;
    private double radius;

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double longitude, double latitude, double radius) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        incWriteCount();
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate() {    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getRadius() {
        return radius;
    }


    /**
     * subclass specific implementations of Coordinate Interface methods
     */
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    public CartesianCoordinate asCartesianCoordinate() {
        double x = this.radius * Math.cos(this.latitude) * Math.sin(this.longitude);
        double y = this.radius * Math.sin(this.latitude) * Math.sin(this.longitude);
        double z = this.radius * Math.cos(this.longitude);
        return new CartesianCoordinate(x, y, z);
    }

    /**
     * subclass specific implementations of DataObject methods
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        CartesianCoordinate c = this.asCartesianCoordinate();
        doWriteOn(rset, c.getX(), c.getY(), c.getZ());
    }


    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        double x = rset.getDouble("coordinate_x");
        double y = rset.getDouble("coordinate_y");
        double z = rset.getDouble("coordinate_z");
        CartesianCoordinate c = new CartesianCoordinate(x, y, z);

        SphericCoordinate s = c.asSphericCoordinate();
        this.longitude = s.getLongitude();
        this.latitude = s.getLatitude();
        this.radius = s.getRadius();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof SphericCoordinate)) {
            return false;
        }
        return isEqual((SphericCoordinate) obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.longitude, this.latitude, this.radius);
    }


}
