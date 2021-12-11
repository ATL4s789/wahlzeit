package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;


/**
 * Test cases for the CartesianCoordinate class.
 */
public class CoordinateTest {

    @Test
    public void testEqualities() {
        // ARRANGE
        Coordinate c1 = new CartesianCoordinate(0, 0, 0);
        Coordinate c2 = new CartesianCoordinate(0, 0, 1);
        Coordinate s1 = new SphericCoordinate(0, 0, 0);
        Coordinate s2 = new SphericCoordinate(0, 0, 1);

        testEqualitySelf(c1);
        testEqualitySelf(s1);

        testNoEquality(c1, c2);
        testNoEquality(s2, s1);

        testEqualityFalseClass(c1, s1);
        testEqualityFalseClass(s2, c2);
    }

    @Test
    public void testConversion() {
        double delta = 0.0001;
        double latitude, longitude, radius;
        Coordinate c;

        c = new CartesianCoordinate(3, 4, 5);
        latitude = c.asSphericCoordinate().getLatitude();
        longitude = c.asSphericCoordinate().getLongitude();
        radius = c.asSphericCoordinate().getRadius();

        assertEquals(7.07107, radius, delta);
        assertEquals(0.9273, latitude, delta);
        assertEquals(0.785398, longitude, delta);

        c = new CartesianCoordinate(1, 0, 0);
        latitude = c.asSphericCoordinate().getLatitude();
        longitude = c.asSphericCoordinate().getLongitude();
        radius = c.asSphericCoordinate().getRadius();

        assertEquals(1, radius, delta);
        assertEquals(0, latitude, delta);
        assertEquals(1.5708, longitude, delta);

        c = new CartesianCoordinate(0, 1, 1);
        latitude = c.asSphericCoordinate().getLatitude();
        longitude = c.asSphericCoordinate().getLongitude();
        radius = c.asSphericCoordinate().getRadius();

        assertEquals(1.41421, radius, delta);
        assertEquals(1.5708, latitude, delta);
        assertEquals(0.785398, longitude, delta);
    }

    @Test
    public void testCartesianDistances() {
        // ARRANGE
        Coordinate c1 = new CartesianCoordinate(0, 0, 0);
        Coordinate c2 = new CartesianCoordinate(0, 0, 1);
        Coordinate s1 = new SphericCoordinate(0, 0, 0);
        Coordinate s2 = new SphericCoordinate(0, 0, 1);


        assertEquals(0, c1.getCartesianDistance(c1), 0.0);
        assertEquals(0, s2.getCartesianDistance(s2), 0.0);

        assertEquals(1, c1.getCartesianDistance(c2), 0.0);
        assertEquals(1, s1.getCartesianDistance(s2), 0.0);

        assertEquals(1, c1.getCartesianDistance(s2), 0.0);
        assertEquals(1, s1.getCartesianDistance(c2), 0.0);
    }

    @Test
    public void testCentralAngles() {
        // ARRANGE
        Coordinate c1 = new CartesianCoordinate(0, 0, 0);
        Coordinate c2 = new CartesianCoordinate(0, 0, 1);
        Coordinate s1 = new SphericCoordinate(0, 0, 0);
        Coordinate s2 = new SphericCoordinate(0, 0, 1);

        assertEquals(0, c1.getCentralAngle(c1), 0.0);
        assertEquals(0, s2.getCentralAngle(s2), 0.0);

        assertEquals(c1.getCentralAngle(c2), Math.acos(1), 0.0);
        assertEquals(s1.getCentralAngle(s2), Math.acos(1), 0.0);

        assertEquals(c2.getCentralAngle(s1), Math.acos(1), 0.0);
        assertEquals(s2.getCentralAngle(c1), Math.acos(1), 0.0);
    }

    @Test
    public void testPostconditions() {
        // ARRANGE
        Random rand = new Random();
        Coordinate s1 = new SphericCoordinate(rand.nextDouble() * Math.PI, rand.nextDouble() * Math.PI, 2);
        Coordinate s2 = new SphericCoordinate(rand.nextDouble() * Math.PI, rand.nextDouble() * Math.PI, 4);

        Coordinate c1 = new SphericCoordinate(rand.nextDouble() * 100, rand.nextDouble() * 100, rand.nextDouble() * 100);
        Coordinate c2 = new SphericCoordinate(rand.nextDouble() * 100, rand.nextDouble() * 100, rand.nextDouble() * 100);

        s1.getCentralAngle(s2);
        s1.getCartesianDistance(s2);

        c1.getCentralAngle(c2);
        c1.getCartesianDistance(c2);
    }

    private void testEqualitySelf(Coordinate coordinate) {
        assertEquals(coordinate, coordinate);
        assertTrue(coordinate.isEqual(coordinate));
    }

    private void testNoEquality(Coordinate coordinate1, Coordinate coordinate2) {
        assertNotEquals(coordinate1, coordinate2);
        assertFalse(coordinate1.isEqual(coordinate2));
    }

    public void testEqualityFalseClass(Coordinate coordinate1, Coordinate coordinate2) {
        assertNotEquals(coordinate1, coordinate2);
    }

    @Test
    public void testSerialization() throws SQLException {
        // ARRANGE
        CartesianCoordinate coordinate = new CartesianCoordinate(2, 3, 5);
        ResultSet rset = Mockito.mock(ResultSet.class);

        // ACT
        coordinate.writeOn(rset);

        // ASSERT
        verify(rset, Mockito.times(1)).updateDouble("coordinate_x", coordinate.getX());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_y", coordinate.getY());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_z", coordinate.getZ());
    }

}
