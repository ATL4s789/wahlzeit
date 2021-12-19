package org.wahlzeit.model;

import org.wahlzeit.services.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * A location represents the place of creation of a (uploaded) photo.
 */
public class Location extends DataObject {

    public Coordinate coordinate;

    /**
     * @methodtype constructor
     * @param coordinate
     */
    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
        incWriteCount();
    }

    public Location(ResultSet rset) throws SQLException {
        this.coordinate = new CartesianCoordinate(rset);
        incWriteCount();
    }


    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        double x, y, z;
        try {
            x = rset.getDouble("coordinate_x");
            y = rset.getDouble("coordinate_y");
            z = rset.getDouble("coordinate_z");
        } catch (SQLException e) {
            throw new SQLException("Could not read from resultSet: " + rset + ". " + e.getMessage());
        }
        this.coordinate = new CartesianCoordinate(x, y, z);
        incWriteCount();
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        double x = this.coordinate.asCartesianCoordinate().getX();
        double y = this.coordinate.asCartesianCoordinate().getY();
        double z = this.coordinate.asCartesianCoordinate().getZ();

        try {
            rset.updateDouble("coordinate_x", x);
            rset.updateDouble("coordinate_y", y);
            rset.updateDouble("coordinate_z", z);
        } catch (SQLException e) {
            throw new SQLException("Could write on resultSet: " + rset.toString() + ". " + e.getMessage());
        }
    }

    private void assertNotNull(Object obj) throws NullPointerException {
        if(obj == null) {
            throw new NullPointerException("Argument is null");
        }
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        incWriteCount();
    }

    @Override
    public boolean isDirty() {
        return this.writeCount != 0;
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    /**
     * @methodtype set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        incWriteCount();
    }

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }



}
