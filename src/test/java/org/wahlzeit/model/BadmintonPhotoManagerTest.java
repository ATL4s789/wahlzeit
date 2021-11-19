package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BadmintonPhotoManagerTest {

    @Test
    public void testSingleton() {
        BadmintonPhotoManager manager = new BadmintonPhotoManager();
        BadmintonPhotoManager manager2 = new BadmintonPhotoManager();

        assertTrue(manager.getInstance() instanceof BadmintonPhotoManager);
        assertEquals(BadmintonPhotoManager.getInstance(), manager.getInstance());
        assertEquals(manager.getInstance(), manager2.getInstance());
    }



}
