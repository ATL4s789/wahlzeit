package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test cases for the Photo class.
 */
public class PhotoTest {

    /**
     *
     */
    @Test
    public void testGetLocation() {
        Coordinate c = new Coordinate(0,0, 0);
        Location loc = new Location(c);
        Photo photo = new Photo(loc);
        assertTrue(photo.getLocation() == loc);
        assertTrue(loc.getCoordinate() == c);
    }



}
