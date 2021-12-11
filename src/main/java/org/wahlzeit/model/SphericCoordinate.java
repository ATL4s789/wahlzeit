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
    public SphericCoordinate(double longitude, double latitude, double radius) throws IllegalStateException {
        this.longitude = longitude % (2 * Math.PI);     // make sure angles are < 2Pi, since 4Pi = 2Pi
        this.latitude = latitude % (2 * Math.PI);
        this.radius = radius;
        incWriteCount();
        assertClassInvariants();
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate() {    }

    public double getLongitude() throws IllegalStateException {
        assertClassInvariants();
        return longitude;
    }

    public double getLatitude() throws IllegalStateException {
        assertClassInvariants();
        return latitude;
    }

    public double getRadius() throws IllegalStateException {
        assertClassInvariants();
        return radius;
    }


    /**
     * subclass specific implementations of Coordinate Interface methods
     */
    public SphericCoordinate asSphericCoordinate() throws IllegalStateException {
        assertClassInvariants();
        return this;
    }

    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException, NullPointerException {
        assertClassInvariants();
        double x = this.radius * Math.cos(this.latitude) * Math.sin(this.longitude);
        double y = this.radius * Math.sin(this.latitude) * Math.sin(this.longitude);
        double z = this.radius * Math.cos(this.longitude);
        CartesianCoordinate c = new CartesianCoordinate(x, y, z);
        assertNotNull(c);
        c.assertClassInvariants();
        return c;
    }

    protected void assertClassInvariants() throws IllegalStateException {
        if(this.longitude < 0 || this.longitude >= 2*Math.PI) {
            throw new IllegalStateException("Longitude is not in range(0, 2*PI). Value is: " + this.longitude);
        }
        if(this.latitude < 0 || this.latitude >= 2*Math.PI) {
            throw new IllegalStateException("Latitude is not in range(0, 2*PI). Value is: " + this.latitude);
        }
    }

    protected double doGetCentralAngle(SphericCoordinate to) throws NullPointerException, IllegalStateException {
        assertNotNull(to);
        assertClassInvariants();
        to.assertClassInvariants();
        double centralAngle = Math.acos(Math.sin(this.getLongitude()) * Math.sin(to.getLongitude())
                + Math.cos(this.getLongitude()) * Math.cos(to.getLongitude()) * Math.cos(Math.abs(this.getLatitude() - to.getLatitude())));
        assert(0 <= centralAngle && centralAngle < 2*Math.PI);
        return centralAngle;
    }

    /**
     * subclass specific implementations of DataObject methods
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException, NullPointerException, IllegalStateException {
        assertNotNull(rset);
        assertClassInvariants();
        CartesianCoordinate c = this.asCartesianCoordinate();
        try {
            doWriteOn(rset, c.getX(), c.getY(), c.getZ());
        } catch (SQLException e) {
            throw new SQLException("Could not write on resultSet: " + rset.toString() + ". " + e.getMessage());
        }
    }


    @Override
    public void readFrom(ResultSet rset) throws SQLException, NullPointerException, IllegalStateException, ArithmeticException {
        assertNotNull(rset);
        assertClassInvariants();
        double x;
        double y;
        double z;
        try {
            x = rset.getDouble("coordinate_x");
            y = rset.getDouble("coordinate_y");
            z = rset.getDouble("coordinate_z");
        } catch (SQLException e) {
            throw new SQLException("Could not read from resultSet: " + rset + ". " + e.getMessage());
        }
        CartesianCoordinate c = new CartesianCoordinate(x, y, z);
        c.assertClassInvariants();

        SphericCoordinate s = c.asSphericCoordinate();
        this.longitude = s.getLongitude();
        this.latitude = s.getLatitude();
        this.radius = s.getRadius();
        assertClassInvariants();
    }

    @Override
    public boolean equals(Object obj) throws NullPointerException, IllegalStateException {
        assertNotNull(obj);
        assertClassInvariants();
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof SphericCoordinate)) {
            return false;
        }
        return isEqual((SphericCoordinate) obj);
    }

    @Override
    public int hashCode() throws IllegalStateException {
        assertClassInvariants();
        return Objects.hash(this.longitude, this.latitude, this.radius);
    }


}
