package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BadmintonPhoto extends Photo {

    /**
     * @methodtype constructor
     */
    public BadmintonPhoto(Location location) {
        this.location = location;
        id = PhotoId.getNextId();
    }

    /**
     * @methodtype constructor
     */
    public BadmintonPhoto() {
        id = PhotoId.getNextId();
        incWriteCount();
    }

    /**
     *
     * @methodtype constructor
     */
    public BadmintonPhoto(PhotoId myId) {
        id = myId;
        incWriteCount();
    }

    /**
     *
     * @methodtype constructor
     */
    public BadmintonPhoto(ResultSet rset) throws SQLException {
        readFrom(rset);
    }

}

