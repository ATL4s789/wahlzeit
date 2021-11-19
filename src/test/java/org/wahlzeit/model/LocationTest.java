package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * Test cases for the Location class.
 */
public class LocationTest {

    /**
     *
     */
    @Test
    public void testGetCoordinate() {
        Coordinate c = new CartesianCoordinate(0,0, 0);
        Location loc = new Location(c);
        assertTrue(loc.getCoordinate() == c);
    }

    @Test
    public void testSerialization() throws SQLException {
        // ARRANGE
        Coordinate coordinate = Mockito.mock(CartesianCoordinate.class);
        ResultSet rset = Mockito.mock(ResultSet.class);
        Location location = new Location(coordinate);

        // ACT
        location.writeOn(rset);

        // ASSERT
        verify(coordinate, Mockito.times(1)).writeOn(rset);

    }




}
