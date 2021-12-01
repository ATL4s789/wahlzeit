package org.wahlzeit.model;

import org.wahlzeit.services.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.lang.Math;
import java.util.Objects;

/**
 * Coordinates in the 3-dimensional cartesian (x,y,z) system
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
     * subclass specific implementations of Coordinate Interface methods
     */
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        double latitude = Math.atan2(this.y, this.x);
        double longitude = 0;
        if(radius != 0) {
            longitude = Math.acos(z / radius);
        }
        SphericCoordinate s = new SphericCoordinate(longitude, latitude, radius);
        assertSphericClassInvariants (longitude, latitude);
        assertNotNull(s);
        return s;
    }

    private void assertSphericClassInvariants(double longitude, double latitude) {
        assert (0 <= longitude && longitude < 2*Math.PI && 0 <= latitude && latitude < 2*Math.PI);
    }


    protected double doGetCartesianDistance(CartesianCoordinate to) {
        assertNotNull(to);
        return Math.sqrt((this.getX() - to.getX()) * (this.getX() - to.getX())
                + (this.getY() - to.getY()) * (this.getY() - to.getY())
                + (this.getZ() - to.getZ()) * (this.getZ() - to.getZ()));
    }


    /**
     * subclass specific implementations of DataObject methods
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        doWriteOn(rset, this.x, this.y, this.z);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        this.x = rset.getDouble("coordinate_x");
        this.y = rset.getDouble("coordinate_y");
        this.z = rset.getDouble("coordinate_z");
    }

    @Override
    public boolean equals(Object obj) {
        assertNotNull(obj);
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

