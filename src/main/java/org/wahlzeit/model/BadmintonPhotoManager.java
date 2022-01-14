package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;


@Patterns(
    patterns = {
        @PatternInstance(
            patternName = "Singleton",
            participants = {
                "Singleton"
            }
        ),
        @PatternInstance(
                patternName = "Facade",
                participants = "Facade"
        ),
        @PatternInstance(
                patternName = "Proxy",
                participants = "Proxy"
        ),
        @PatternInstance(
                patternName = "Adapter",
                participants = "Adapter"
        )
    }
)
public class BadmintonPhotoManager extends PhotoManager {


    protected static final BadmintonPhotoManager instance = new BadmintonPhotoManager();


    public BadmintonPhotoManager() {
        photoTagCollector = BadmintonPhotoFactory.getInstance().createPhotoTagCollector();
    }


    public static BadmintonPhotoManager getInstance() {
        return instance;
    }


    protected Photo createObject(ResultSet rset) throws SQLException {
        Photo photo;
        try {
             photo = BadmintonPhotoFactory.getInstance().createPhoto(rset);
        } catch (SQLException e) {
            throw new SQLException("Could not create Photo from resultSet: " + rset.toString() + ". " + e.getMessage());
        }
        return photo;
    }

}
