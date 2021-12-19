package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * Test cases for the Location class.
 */
public class LocationTest {


    @Test
    public void testGetCoordinate() {
        Coordinate c = new CartesianCoordinate(0,0, 0);
        Location loc = new Location(c);
        assertTrue(loc.getCoordinate() == c);
    }


    @Test
    public void testSerialization() throws SQLException {
        ResultSet rset = Mockito.mock(ResultSet.class);
        Location location = Mockito.mock(Location.class);

        location.writeOn(rset);

        verify(location, Mockito.times(1)).writeOn(rset);
    }


}
