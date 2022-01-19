package org.wahlzeit.model;

import java.sql.*;

import org.wahlzeit.services.*;

@Patterns(
    patterns = {
        @PatternInstance(
            patternName = "Singleton",
            participants = {
                "Singleton"
            }
        ),
        @PatternInstance(
            patternName = "AbstractFactory",
            participants = {
                "ConcreteFactory"
            }
        )
    }
)
public class BadmintonPhotoFactory extends PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static BadmintonPhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized BadmintonPhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("Setting BadmintonPhotoFactory");
            setInstance(new BadmintonPhotoFactory());
        }
        return instance;
    }

    /**
     * Method to set the singleton instance of BadmintonPhotoFactory.
     */
    protected static synchronized void setInstance(BadmintonPhotoFactory badmintonPhotoFactory) throws IllegalStateException, NullPointerException  {
        if(badmintonPhotoFactory == null) {
            throw new NullPointerException("The given badmintonPhotoFactory parameter was null!");
        }
        if (instance != null) {
            throw new IllegalStateException("Attempt to initialize BadmintonPhotoFactory twice");
        }
        instance = badmintonPhotoFactory;
    }


    protected BadmintonPhotoFactory() {
        // do nothing
    }

    /**
     * @methodtype factory
     */
    public BadmintonPhoto createPhoto() {
        return new BadmintonPhoto();
    }


    public BadmintonPhoto createPhoto(PhotoId id) {
        return new BadmintonPhoto(id);
    }

    // Call from BadmintonPhotoManager
    // Call to BadmintonPhoto
    public BadmintonPhoto createPhoto(ResultSet rs) throws SQLException {
        return new BadmintonPhoto(rs);
    }

}
