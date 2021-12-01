package org.wahlzeit.model;

import org.wahlzeit.services.*;

import java.sql.*;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

    protected final double EPSILON = 0.001;


    public double getCartesianDistance(Coordinate coordinate) {
        assertNotNull(coordinate);
        return this.asCartesianCoordinate().doGetCartesianDistance(coordinate.asCartesianCoordinate());
    }

    public double getCentralAngle(Coordinate coordinate) {
        assertNotNull(coordinate);
        return this.asSphericCoordinate().doGetCentralAngle(coordinate.asSphericCoordinate());
    }

    protected void assertNotNull(Object obj) {
        assert(obj != null);
    }

    /**
     * cartesian coordinates in database, thus isEqual() can be handled
     * in parent class AbstractCoordinate
     */
    public boolean isEqual(Coordinate coordinate) {
        assertNotNull(coordinate);
        CartesianCoordinate from = this.asCartesianCoordinate();
        CartesianCoordinate to = coordinate.asCartesianCoordinate();

        boolean eqX = Math.abs(from.getX() - to.getX()) <= EPSILON;
        boolean eqY = Math.abs(from.getY() - to.getY()) <= EPSILON;
        boolean eqZ = Math.abs(from.getZ() - to.getZ()) <= EPSILON;

        return eqX && eqY && eqZ;
    }

    protected void doWriteOn(ResultSet rset, double x, double y, double z) throws SQLException {
        assertNotNull(rset);
        rset.updateDouble("coordinate_x", x);
        rset.updateDouble("coordinate_y", y);
        rset.updateDouble("coordinate_z", z);
    }

    @Override
    public boolean isDirty() {
        return this.writeCount != 0;
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        incWriteCount();
    }

    @Override
    public String getIdAsString() {
        return ID;
    }

}
