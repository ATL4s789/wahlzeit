package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BadmintonPhoto extends Photo {

    private int players;

    /**
     * @methodtype constructor
     */
    public BadmintonPhoto() {
        super();
    }

    /**
     * @methodtype constructor
     */
    public BadmintonPhoto(PhotoId id) {
        super(id);
    }

    /**
     *
     * @methodtype constructor
     */
    public BadmintonPhoto(ResultSet rset) throws SQLException {
        super(rset);
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getPlayers() {
        return this.players;
    }

    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateInt("players", players);
    }

    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        players = rset.getInt("players");
    }


}

