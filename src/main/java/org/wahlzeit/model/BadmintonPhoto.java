package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;


@Patterns(
    patterns = {
        @PatternInstance(
            patternName = "AbstractFactory",
            participants = {
                "ConcreteProduct"
            }
        )
    }
)
public class BadmintonPhoto extends Photo {

    public BadmintonGame game;

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
    // Call from BadmintonPhotoFactory
    // Call to Photo
    public BadmintonPhoto(ResultSet rset) throws SQLException {
        super(rset);
    }

    public void setPlayers(int players) throws IllegalArgumentException {
        assertClassInvariants();
        if(players < 0) {
            throw new IllegalArgumentException("Number of players must be >= 0.");
        }
        this.players = players;
    }

    public int getPlayers() throws IllegalStateException {
        assertClassInvariants();
        return this.players;
    }

    public void writeOn(ResultSet rset) throws SQLException, IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(rset);
        try {
            super.writeOn(rset);
            rset.updateInt("players", players);
        } catch (SQLException e) {
            throw new SQLException("Could not create Photo from resultSet: " + rset.toString() + ". " + e.getMessage());
        }
    }

    protected void assertClassInvariants() throws IllegalStateException {
        if(this.players < 0) {
            throw new IllegalStateException("Number of players in this Photo is < 0.");
        }
    }

    public void readFrom(ResultSet rset) throws SQLException, IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(rset);
        try {
            super.readFrom(rset);
            players = rset.getInt("players");
        } catch (SQLException e) {
            throw new SQLException("Could not create Photo from resultSet: " + rset.toString() + ". " + e.getMessage());
        }
        assertClassInvariants();
    }


}

