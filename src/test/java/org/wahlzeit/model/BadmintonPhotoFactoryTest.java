package org.wahlzeit.model;

import org.junit.*;
import org.junit.rules.*;

import static org.junit.Assert.*;

public class BadmintonPhotoFactoryTest {

    @Test
    public void testSingleton() {
        BadmintonPhotoFactory factory = new BadmintonPhotoFactory();

        assertTrue(factory.getInstance() instanceof BadmintonPhotoFactory);
        assertTrue(BadmintonPhotoFactory.getInstance() instanceof BadmintonPhotoFactory);
        try {
            BadmintonPhotoFactory.setInstance(null);
        } catch (IllegalStateException exception) {
            assertTrue(true);
        }
        try {
            factory.setInstance(null);
        } catch (IllegalStateException exception) {
            assertTrue(true);
        }
    }
}
