package org.wahlzeit.model;

import org.wahlzeit.services.*;

import java.sql.*;
import java.util.*;

/**
 * Coordinates in the 3-dimensional spherical (phi,theta,r) system
 * This class assumes the polar axis is the z-axis, and the equatorial plane is
 * the XY plane.
 */
public class SphericCoordinate extends AbstractCoordinate {

    /**
     * spherical coordinates
     */
    private double phi;
    private double theta;
    private double radius;

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
        incWriteCount();
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate() {    }

    public double getPhi() {
        return phi;
    }

    public double getTheta() {
        return theta;
    }

    public double getRadius() {
        return radius;
    }


    /**
     * subclass specific implementations of Interface methods
     */
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    public CartesianCoordinate asCartesianCoordinate() {
        double x = this.radius * Math.cos(this.theta) * Math.sin(this.phi);
        double y = this.radius * Math.sin(this.theta) * Math.sin(this.phi);
        double z = this.radius * Math.cos(this.phi);
        return new CartesianCoordinate(x, y, z);
    }

    /**
     * subclass specific implementations of DataObject methods
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        CartesianCoordinate c = this.asCartesianCoordinate();

        rset.updateDouble("coordinate_x", c.getX());
        rset.updateDouble("coordinate_y", c.getY());
        rset.updateDouble("coordinate_z", c.getZ());
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        double x = rset.getDouble("coordinate_x");
        double y = rset.getDouble("coordinate_y");
        double z = rset.getDouble("coordinate_z");
        CartesianCoordinate c = new CartesianCoordinate(x, y, z);

        SphericCoordinate s = c.asSphericCoordinate();
        this.phi = s.getPhi();
        this.theta = s.getTheta();
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
        return Objects.hash(this.phi, this.theta, this.radius);
    }


}
