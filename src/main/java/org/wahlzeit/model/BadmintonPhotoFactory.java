package org.wahlzeit.model;

import java.sql.*;

import org.wahlzeit.services.*;

public class BadmintonPhotoFactory extends PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static PhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized PhotoFactory getInstance() {
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
    public Photo createPhoto() {
        return new BadmintonPhoto();
    }


    public Photo createPhoto(PhotoId id) {
        return new BadmintonPhoto(id);
    }


    public Photo createPhoto(ResultSet rs) throws SQLException {
        Photo photo;
        try {
            photo = BadmintonPhotoFactory.getInstance().createPhoto(rs);
        } catch (SQLException e) {
            throw new SQLException("Could not create Photo from resultSet: " + rs.toString() + ". " + e.getMessage());
        }
        return photo;
    }

}
