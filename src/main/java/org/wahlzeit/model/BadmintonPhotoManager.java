package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BadmintonPhotoManager extends PhotoManager {

    /**
     *
     */
    protected static final BadmintonPhotoManager instance = new BadmintonPhotoManager();

    /**
     *
     */
    public BadmintonPhotoManager() {
        photoTagCollector = BadmintonPhotoFactory.getInstance().createPhotoTagCollector();
    }

    public static BadmintonPhotoManager getInstance() {
        return instance;
    }

    /**
     *
     */
    protected Photo createObject(ResultSet rset) throws SQLException {
        return BadmintonPhotoFactory.getInstance().createPhoto(rset);
    }

}
