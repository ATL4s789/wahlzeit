package org.wahlzeit.model;

import java.util.*;

public class BadmintonGameManager {

    protected static final BadmintonGameManager instance = new BadmintonGameManager();

    private HashMap<Integer, BadmintonGame> badmintonGames = new HashMap<>();


    public static BadmintonGameManager getInstance() {
        return instance;
    }

    // Call from Client
    // Call to BadmintonGameType
    public BadmintonGame createBadmintonGame(String typeName) {
        assertIsValidBadmintonGameTypeName(typeName);
        BadmintonGameType bgt = getBadmintonGameType(typeName);
        BadmintonGame result = bgt.createInstance();
        badmintonGames.put(result.getID(), result);
        return result;
    }

    private BadmintonGameType getBadmintonGameType(String typeName) {
        return new BadmintonGameType(typeName);
    }

    private void assertIsValidBadmintonGameTypeName(String typeName) {
        if( ! (typeName.equals("Mixed") || typeName.equals("double-male") || typeName.equals("double-female")
            || typeName.equals("solo-male") || typeName.equals("solo-female")) ) {
            throw new IllegalArgumentException(typeName + " is not a valid discipline for Badminton.\n");
        }
    }


}
