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
        this.longitude = longitude % (2 * Math.PI);     // make sure angles are < 2Pi, since 4Pi = 2Pi
        this.latitude = latitude % (2 * Math.PI);
        this.radius = radius;
        incWriteCount();
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate() {    }

    public double getLongitude() {
        assert (0 <= this.longitude && this.longitude < 2*Math.PI);
        return longitude;
    }

    public double getLatitude() {
        assert (0 <= this.latitude && this.latitude < 2*Math.PI);
        return latitude;
    }

    public double getRadius() {
        return radius;
    }


    /**
     * subclass specific implementations of Coordinate Interface methods
     */
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();
        return this;
    }

    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        double x = this.radius * Math.cos(this.latitude) * Math.sin(this.longitude);
        double y = this.radius * Math.sin(this.latitude) * Math.sin(this.longitude);
        double z = this.radius * Math.cos(this.longitude);
        CartesianCoordinate c = new CartesianCoordinate(x, y, z);
        assertClassInvariants();
        assertNotNull(c);
        return c;
    }

    protected void assertClassInvariants() {
        assert (0 <= this.longitude && this.longitude < 2*Math.PI && 0 <= this.latitude && this.latitude < 2*Math.PI);
    }

    protected double doGetCentralAngle(SphericCoordinate to) {
        assertNotNull(to);
        assertClassInvariants();
        to.assertClassInvariants();
        return Math.acos(Math.sin(this.getLongitude()) * Math.sin(to.getLongitude())
                + Math.cos(this.getLongitude()) * Math.cos(to.getLongitude()) * Math.cos(Math.abs(this.getLatitude() - to.getLatitude())));
    }

    /**
     * subclass specific implementations of DataObject methods
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        assertClassInvariants();
        CartesianCoordinate c = this.asCartesianCoordinate();
        doWriteOn(rset, c.getX(), c.getY(), c.getZ());
    }


    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        assertClassInvariants();
        double x = rset.getDouble("coordinate_x");
        double y = rset.getDouble("coordinate_y");
        double z = rset.getDouble("coordinate_z");
        CartesianCoordinate c = new CartesianCoordinate(x, y, z);

        SphericCoordinate s = c.asSphericCoordinate();
        this.longitude = s.getLongitude();
        this.latitude = s.getLatitude();
        this.radius = s.getRadius();
        assertClassInvariants();
    }

    @Override
    public boolean equals(Object obj) {
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
    public int hashCode() {
        assertClassInvariants();
        return Objects.hash(this.longitude, this.latitude, this.radius);
    }


}
