package org.wahlzeit.model;

import java.sql.*;
import java.util.*;


@Patterns(
        patterns = {
                @PatternInstance(
                        patternName = "Flyweight",
                        participants = {
                                "Flyweight"
                        }
                )
        }
)
/**
 * Coordinates in the 3-dimensional spherical (longitude, latitude, radius) system
 * This class assumes the polar axis is the z-axis, and the equatorial plane is
 * the XY plane.
 */
public final class SphericCoordinate extends AbstractCoordinate {

    /**
     * spherical coordinates
     */
    private final double longitude;
    private final double latitude;
    private final double radius;

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double longitude, double latitude, double radius) throws IllegalStateException {
        this.longitude = longitude % (2 * Math.PI);     // make sure angles are < 2Pi, since 4Pi = 2Pi
        this.latitude = latitude % (2 * Math.PI);
        this.radius = radius;
        assertClassInvariants();
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        double x, y, z;
        try {
            x = rset.getDouble("coordinate_x");
            y = rset.getDouble("coordinate_y");
            z = rset.getDouble("coordinate_z");
        } catch (SQLException e) {
            throw new SQLException("Could not read from resultSet: " + rset + ". " + e.getMessage());
        }
        SphericCoordinate c = (new CartesianCoordinate(x, y, z)).asSphericCoordinate();
        this.longitude = c.getLongitude();
        this.latitude = c.getLatitude();
        this.radius = c.getRadius();
        assertClassInvariants();
    }

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

    @Override
    public boolean equals(Object obj) throws NullPointerException, IllegalStateException {
        assertNotNull(obj);
        assertClassInvariants();
        if(this == obj) {
            return true;
        }
        if (!(obj instanceof Coordinate)) {
            return false;
        }
        return isEqual((Coordinate) obj);
    }

    @Override
    public int hashCode() throws IllegalStateException {
        assertClassInvariants();
        return Objects.hash(this.longitude, this.latitude, this.radius);
    }

}
