package org.wahlzeit.model;

import java.sql.*;

import org.wahlzeit.services.*;

public class BadmintonPhotoFactory extends PhotoFactory{

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static BadmintonPhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized BadmintonPhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting BadmintonPhotoFactory");
            setInstance(new BadmintonPhotoFactory());
        }
        return instance;
    }

    /**
     * Method to set the singleton instance of BadmintonPhotoFactory.
     */
    protected static synchronized void setInstance(BadmintonPhotoFactory badmintonPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize BadmintonPhotoFactory twice");
        }
        instance = badmintonPhotoFactory;
    }

    /**
     *
     */
    protected BadmintonPhotoFactory() {
        // do nothing
    }

    /**
     * @methodtype factory
     */
    public BadmintonPhoto createPhoto() {
        return new BadmintonPhoto();
    }

    /**
     *
     */
    public BadmintonPhoto createPhoto(PhotoId id) {
        return new BadmintonPhoto(id);
    }

    /**
     *
     */
    public BadmintonPhoto createPhoto(ResultSet rs) throws SQLException {
        return new BadmintonPhoto(rs);
    }

}
