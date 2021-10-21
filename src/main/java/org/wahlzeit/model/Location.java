package org.wahlzeit.model;

/**
 * A location represents the place of creation of a (uploaded) photo.
 */
public class Location {

    /**
     *
     */
    public Coordinate coordinate;

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @methodtype constructor
     * @param coordinate
     */
    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
