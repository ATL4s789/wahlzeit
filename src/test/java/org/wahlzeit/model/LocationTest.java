package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test cases for the Location class.
 */
public class LocationTest {

    /**
     *
     */
    @Test
    public void testGetCoordinate() {
        Coordinate c = new Coordinate(0,0, 0);
        Location loc = new Location(c);
        assertTrue(loc.getCoordinate() == c);
    }






}
