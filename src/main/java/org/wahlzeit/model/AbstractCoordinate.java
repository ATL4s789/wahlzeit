package org.wahlzeit.model;

import org.wahlzeit.services.*;

import java.sql.*;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

    protected final double EPSILON = 0.001;


    public double getCartesianDistance(Coordinate coordinate) {
        return doGetCartesianDistance(this.asCartesianCoordinate(), coordinate.asCartesianCoordinate());
    }

    private double doGetCartesianDistance(CartesianCoordinate from, CartesianCoordinate to) {
        return Math.sqrt((from.getX() - to.getX()) * (from.getX() - to.getX())
                + (from.getY() - to.getY()) * (from.getY() - to.getY())
                + (from.getZ() - to.getZ()) * (from.getZ() - to.getZ()));
    }

    public double getCentralAngle(Coordinate coordinate) {
        return doGetCentralAngle(this.asSphericCoordinate(), coordinate.asSphericCoordinate());
    }

    private double doGetCentralAngle(SphericCoordinate from, SphericCoordinate to) {
        return Math.acos(Math.sin(from.getLongitude()) * Math.sin(to.getLongitude())
        + Math.cos(from.getLongitude()) * Math.cos(to.getLongitude()) * Math.cos(Math.abs(from.getLatitude() - to.getLatitude())));
    }

    /**
     * cartesian coordinates in database, thus isEqual() can be handled
     * in parent class AbstractCoordinate
     */
    public boolean isEqual(Coordinate coordinate) {
        CartesianCoordinate from = this.asCartesianCoordinate();
        CartesianCoordinate to = coordinate.asCartesianCoordinate();

        boolean eqX = Math.abs(from.getX() - to.getX()) <= EPSILON;
        boolean eqY = Math.abs(from.getY() - to.getY()) <= EPSILON;
        boolean eqZ = Math.abs(from.getZ() - to.getZ()) <= EPSILON;

        return eqX && eqY && eqZ;
    }

    protected void doWriteOn(ResultSet rset, double x, double y, double z) throws SQLException {
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
