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
        assertValidPlayersCount(players);
        this.players = players;
    }

    public int getPlayers() {
        assertValidPlayersCount(this.players);
        return this.players;
    }

    public void writeOn(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        super.writeOn(rset);
        rset.updateInt("players", players);
    }

    protected void assertValidPlayersCount(int count) {
        assert count >= 0;
    }


    public void readFrom(ResultSet rset) throws SQLException {
        assertNotNull(rset);
        super.readFrom(rset);
        players = rset.getInt("players");
    }


}

