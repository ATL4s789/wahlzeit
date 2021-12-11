package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the Photo class.
 */
public class BadmintonPhotoTest {

    /**
     *
     */
    @Test
    public void testGetPlayers() {
        int players = 2;
        BadmintonPhoto photo = new BadmintonPhoto();
        photo.setPlayers(players);
        assertEquals(photo.getPlayers(), players);
    }

    /*  This does not work
    @Test
    public void testSetPlayers() {
        Badminton photo = new BadmintonPhoto();
        try {
            photo.setPlayers(-1);
        } catch(IllegalArgumentException e) {
            assert true;
        }
        assert false;
    }
    */
}
