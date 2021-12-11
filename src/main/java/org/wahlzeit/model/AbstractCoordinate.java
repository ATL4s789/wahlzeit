package org.wahlzeit.model;

import org.wahlzeit.services.*;

import javax.naming.*;
import java.sql.*;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

    protected final double EPSILON = 0.001;


    public double getCartesianDistance(Coordinate coordinate) throws IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(coordinate);
        return this.asCartesianCoordinate().doGetCartesianDistance(coordinate.asCartesianCoordinate());
    }

    public double getCentralAngle(Coordinate coordinate) throws IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(coordinate);
        return this.asSphericCoordinate().doGetCentralAngle(coordinate.asSphericCoordinate());
    }

    protected void assertNotNull(Object obj) throws NullPointerException {
        if(obj == null) {
            throw new NullPointerException("Argument is null");
        }
    }

    protected void assertClassInvariants() throws IllegalStateException {
        assert true;
    }

    /**
     * cartesian coordinates in database, thus isEqual() can be handled
     * in parent class AbstractCoordinate
     */
    public boolean isEqual(Coordinate coordinate) throws IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(coordinate);
        CartesianCoordinate from = this.asCartesianCoordinate();
        CartesianCoordinate to = coordinate.asCartesianCoordinate();

        boolean eqX = Math.abs(from.getX() - to.getX()) <= EPSILON;
        boolean eqY = Math.abs(from.getY() - to.getY()) <= EPSILON;
        boolean eqZ = Math.abs(from.getZ() - to.getZ()) <= EPSILON;

        assertClassInvariants();
        return eqX && eqY && eqZ;
    }

    protected void doWriteOn(ResultSet rset, double x, double y, double z) throws SQLException, NullPointerException, IllegalStateException {
        assertClassInvariants();
        assertNotNull(rset);
        try {
            rset.updateDouble("coordinate_x", x);
            rset.updateDouble("coordinate_y", y);
            rset.updateDouble("coordinate_z", z);
        } catch (SQLException e) {
            throw new SQLException("Could not create Photo from resultSet: " + rset.toString() + ". " + e.getMessage());
        }
        assertClassInvariants();
    }

    @Override
    public boolean isDirty() throws IllegalStateException {
        assertClassInvariants();
        return this.writeCount != 0;
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws IllegalStateException {
        assertClassInvariants();
        incWriteCount();
        assertClassInvariants();
    }

    @Override
    public String getIdAsString() throws IllegalStateException {
        assertClassInvariants();
        return ID;
    }

}
