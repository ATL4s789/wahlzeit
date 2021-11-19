package org.wahlzeit.model;

import org.junit.Test;

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
        assertTrue(photo.getPlayers() == players);
    }



}
