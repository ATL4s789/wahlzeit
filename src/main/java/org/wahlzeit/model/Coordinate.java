package org.wahlzeit.model;

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

}
