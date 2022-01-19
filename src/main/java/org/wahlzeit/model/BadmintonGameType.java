package org.wahlzeit.model;

public class BadmintonGameType {

    private final String name;

    public BadmintonGameType(String name) {
        this.name = name;
    }

    // Call from BadmintonGameManager
    // Call to BadmintonGame
    public BadmintonGame createInstance() {
        return new BadmintonGame(this);
    }

    public String getName() {
        return name;
    }

    // there are no hierarchies in Badminton disciplines
    public boolean isSubtype() {
        return false;
    }

}
