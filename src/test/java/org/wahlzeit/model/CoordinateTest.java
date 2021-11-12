package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Math;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;



/**
 * Test cases for the Coordinate class.
 */
public class CoordinateTest {

    /**
     *
     */
    @Test
    public void testEqualsSelf() {
        Coordinate c = new Coordinate(0, 0, 0);
        assertTrue(c.equals(c));
    }

    /**
     *
     */
    @Test
    public void testEqualsCopy() {
        Coordinate c1 = new Coordinate(0, 0, 0);
        Coordinate c2 = new Coordinate(0, 0, 0);
        assertTrue(c1.equals(c2));
    }

    /**
     *
     */
    @Test
    public void testNotEqualsFalseClass() {
        Coordinate c = new Coordinate(0, 0, 0);
        Photo photo = new Photo();
        assertTrue(!c.equals(photo));
    }

    /**
     *
     */
    @Test
    public void testNotEqualsNull() {
        Coordinate c = new Coordinate(0, 0, 0);
        assertTrue(!c.equals(null));
    }

    /**
     *
     */
    @Test
    public void testGetDistanceToSelfZero() {
        Coordinate c = new Coordinate(0, 0, 0);
        assertTrue(c.getDistance(c) == 0);
    }

    /**
     *
     */
    @Test
    public void testGetDistanceRandom() {
        float randomX = (float) Math.random() * 1000;
        float randomY = (float) Math.random() * 1000;
        float randomZ = (float) Math.random() * 1000;
        Coordinate c1 = new Coordinate(randomX, randomY, randomZ);
        Coordinate c2 = new Coordinate(-randomX, -randomY, -randomZ);
        assertTrue(c1.getDistance(c2) == Math.sqrt(Math.pow(2* c1.getX(), 2) + Math.pow(2* c1.getY(), 2)
                + Math.pow(2* c1.getZ(), 2)));
    }


    @Test
    public void testSerialization() throws SQLException {
        // ARRANGE
        Coordinate coordinate = new Coordinate(2, 3, 5);
        ResultSet rset = Mockito.mock(ResultSet.class);

        // ACT
        coordinate.writeOn(rset);

        // ASSERT
        verify(rset, Mockito.times(1)).updateFloat("coordinate_x", coordinate.getX());
        verify(rset, Mockito.times(1)).updateFloat("coordinate_y", coordinate.getY());
        verify(rset, Mockito.times(1)).updateFloat("coordinate_z", coordinate.getZ());
    }

}
