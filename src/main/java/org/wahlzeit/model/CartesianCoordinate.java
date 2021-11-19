package org.wahlzeit.model;

import org.wahlzeit.services.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.lang.Math;
import java.util.Objects;

/**
 * Coordinates in the 3-dimensional cartesian (x,y,z) )system
 */
public class CartesianCoordinate extends AbstractCoordinate  {

    /**
     * cartesian coordinates
     */
    private double x;
    private double y;
    private double z;

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        incWriteCount();
    }

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate() {    }

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
     * subclass specific implementations of Interface methods
     */
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        double theta = Math.atan2(this.y, this.x);
        double phi = 0;
        if(radius != 0) {
            phi = Math.acos(z / radius);
        }
        return new SphericCoordinate(phi, theta, radius);
    }

    /**
     * subclass specific implementations of DataObject methods
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("coordinate_x", this.x);
        rset.updateDouble("coordinate_y", this.y);
        rset.updateDouble("coordinate_z", this.z);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        this.x = rset.getDouble("coordinate_x");
        this.y = rset.getDouble("coordinate_y");
        this.z = rset.getDouble("coordinate_z");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CartesianCoordinate)) {
            return false;
        }
        return isEqual((CartesianCoordinate) obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z);
    }

}

