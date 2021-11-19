package org.wahlzeit.model;

import org.wahlzeit.services.*;

import java.sql.*;

public interface Coordinate {

    /**
     * interface methods
     */
    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordinate coordinate);

    SphericCoordinate asSphericCoordinate();

    double getCentralAngle(Coordinate coordinate);

    boolean isEqual(Coordinate coordinate);

    /**
     * methods by DataObject
     * necessary for implementation of Location
     */
    void writeOn(ResultSet rset) throws SQLException;

    void readFrom(ResultSet rset) throws SQLException;

    boolean isDirty();

}
