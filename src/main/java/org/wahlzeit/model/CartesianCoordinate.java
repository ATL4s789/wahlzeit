package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.lang.Math;
import java.util.Objects;

/**
 * Coordinates in the 3-dimensional cartesian (x,y,z) system
 */
public final class CartesianCoordinate extends AbstractCoordinate  {

    /**
     * cartesian coordinates
     */
    private final double x;
    private final double y;
    private final double z;

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariants();
    }

    public CartesianCoordinate(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        try {
            this.x = rset.getDouble("coordinate_x");
            this.y = rset.getDouble("coordinate_y");
            this.z = rset.getDouble("coordinate_z");
        } catch (SQLException e) {
            throw new SQLException("Could not read from resultSet: " + rset + ". " + e.getMessage());
        }
        assertClassInvariants();
    }

    public double getX() throws IllegalStateException {
        assertClassInvariants();
        return x;
    }

    public double getY() throws IllegalStateException {
        assertClassInvariants();
        return y;
    }

    public double getZ() throws IllegalStateException {
        assertClassInvariants();
        return z;
    }

    /**
     * subclass specific implementations of Coordinate Interface methods
     */
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException {
        assertClassInvariants();
        return this;
    }

    public SphericCoordinate asSphericCoordinate() throws ArithmeticException, IllegalStateException, NullPointerException {
        assertClassInvariants();
        double radius = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        double latitude = Math.atan2(this.y, this.x);
        double longitude = 0;
        if(radius != 0) {
            longitude = Math.acos(z / radius);
        }
        SphericCoordinate s = new SphericCoordinate(longitude, latitude, radius);
        assertNotNull(s);
        s.assertClassInvariants();
        return s;
    }

    protected double doGetCartesianDistance(CartesianCoordinate to) throws IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(to);
        double distance = Math.sqrt((this.getX() - to.getX()) * (this.getX() - to.getX())
                + (this.getY() - to.getY()) * (this.getY() - to.getY())
                + (this.getZ() - to.getZ()) * (this.getZ() - to.getZ()));
        assert(distance >= 0);
        return distance;
    }

    @Override
    public boolean equals(Object obj) throws IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(obj);
        if (this == obj) {
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
        return Objects.hash(this.x, this.y, this.z);
    }

}

