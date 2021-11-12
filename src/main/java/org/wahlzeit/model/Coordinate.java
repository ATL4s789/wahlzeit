package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.lang.Math;
import java.util.Objects;

/**
 * Coordinates in the 3-dimensional cartesian system
 */
public class Coordinate extends DataObject {

    private static final float EPSILON = (float) 0.001;


    /**
     * cartesian coordinates
     */
    private float x;
    private float y;
    private float z;

    /**
     * @methodtype constructor
     */
    public Coordinate(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        incWriteCount();
    }

    /**
     * @methodtype constructor
     */
    public Coordinate() {    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }


    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateFloat("coordinate_x", this.x);
        rset.updateFloat("coordinate_y", this.y);
        rset.updateFloat("coordinate_z", this.z);
    }

    public void readFrom(ResultSet rset) throws SQLException {
        x = rset.getFloat("coordinate_x");
        y = rset.getFloat("coordinate_y");
        z = rset.getFloat("coordinate_z");
    }

    @Override
    public boolean isDirty() {
        boolean selfDirty = this.writeCount != 0;

        return selfDirty;
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        incWriteCount();
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    /**
     * @param coordinate
     */
    public float getDistance(Coordinate coordinate) {
        return (float) Math.sqrt((coordinate.getX() - x)*(coordinate.getX() - x) + (coordinate.getY() - y)*(coordinate.getY() - y)
                + (coordinate.getZ() - z)*(coordinate.getZ() - z));
    }

    /**
     * @param coordinate
     */
    public boolean isEqual(Coordinate coordinate) {
        boolean eqX = Math.abs(coordinate.getX() - x) <= EPSILON;
        boolean eqY = Math.abs(coordinate.getY() - y) <= EPSILON;
        boolean eqZ = Math.abs(coordinate.getZ() - z) <= EPSILON;

        return eqX && eqY && eqZ;
    }

    /**
     * @param obj
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Coordinate)) {
            return false;
        }
        return isEqual((Coordinate) obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
