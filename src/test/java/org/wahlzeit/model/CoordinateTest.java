package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

        testNotEqualsNull(c1);
        testNotEqualsNull(s1);
    }

    @Test
    public void testCartesiansDistances() {
        // ARRANGE
        Coordinate c1 = new CartesianCoordinate(0, 0, 0);
        Coordinate c2 = new CartesianCoordinate(0, 0, 1);
        Coordinate s1 = new SphericCoordinate(0, 0, 0);
        Coordinate s2 = new SphericCoordinate(0, 0, 1);


        assertTrue(c1.getCartesianDistance(c1) == 0);
        assertTrue(s2.getCartesianDistance(s2) == 0);

        assertTrue(c1.getCartesianDistance(c2) == 1);
        assertTrue(s1.getCartesianDistance(s2) == 1);

        assertTrue(c1.getCartesianDistance(s2) == 1);
        assertTrue(s1.getCartesianDistance(c2) == 1);
    }

    @Test
    public void testCentralAngles() {
        // ARRANGE
        Coordinate c1 = new CartesianCoordinate(0, 0, 0);
        Coordinate c2 = new CartesianCoordinate(0, 0, 1);
        Coordinate s1 = new SphericCoordinate(0, 0, 0);
        Coordinate s2 = new SphericCoordinate(0, 0, 1);

        assertTrue(c1.getCentralAngle(c1) == 0);
        assertTrue(s2.getCentralAngle(s2) == 0);

        assertTrue(c1.getCentralAngle(c2) == Math.acos(1));
        assertTrue(s1.getCentralAngle(s2) == Math.acos(1));

        assertTrue(c2.getCentralAngle(s1) == Math.acos(1));
        assertTrue(s2.getCentralAngle(c1) == Math.acos(1));

    }

    private void testEqualitySelf(Coordinate coordinate) {
        assertTrue(coordinate.equals(coordinate));
        assertTrue(coordinate.isEqual(coordinate));
    }

    private void testNoEquality(Coordinate coordinate1, Coordinate coordinate2) {
        assertFalse(coordinate1.equals(coordinate2));
        assertFalse(coordinate1.isEqual(coordinate2));
    }

    public void testEqualityFalseClass(Coordinate coordinate1, Coordinate coordinate2) {
        assertFalse(coordinate1.equals(coordinate2));
    }

    public void testNotEqualsNull(Coordinate coordinate) {
        assertFalse(coordinate.equals(null));
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
