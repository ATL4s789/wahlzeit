package org.wahlzeit.model;

import org.wahlzeit.services.*;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * A location represents the place of creation of a (uploaded) photo.
 */
public class Location extends DataObject {

    /**
     *
     */
    public Coordinate coordinate;

    /**
     * @methodtype constructor
     * @param coordinate
     */
    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
        incWriteCount();
    }

    /**
     * @methodtype constructor
     */
    public Location() {
        this.coordinate = new Coordinate();
        incWriteCount();
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        this.coordinate.readFrom(rset);
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        this.coordinate.writeOn(rset);
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        incWriteCount();
    }

    @Override
    public boolean isDirty() {
        boolean selfDirty = this.writeCount != 0;
        boolean coordinateDirty = this.coordinate.isDirty();

        return selfDirty || coordinateDirty;
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

}
