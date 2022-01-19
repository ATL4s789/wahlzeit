package org.wahlzeit.model;

import java.util.*;

public class BadmintonGame {

    private BadmintonGameType discipline;
    private Location tournamentLocation;
    private String tournamentDate;

    // Call from BadmintonGame
    public BadmintonGame(BadmintonGameType discipline) {
        this.discipline = discipline;
    }

    public BadmintonGame(BadmintonGameType discipline, Location location, String date) {
        this.discipline = discipline;
        this.tournamentLocation = location;
        this.tournamentDate = date;
    }

    public BadmintonGameType getDiscipline() {
        return discipline;
    }

    public void setDiscipline(BadmintonGameType discipline) {
        this.discipline = discipline;
    }

    public Location getTournamentLocation() {
        return tournamentLocation;
    }

    public void setTournamentLocation(Location location) {
        this.tournamentLocation = location;
    }

    public String getTournamentDate() {
        return tournamentDate;
    }

    public void setTournamentDate(String date) {
        this.tournamentDate = date;
    }

    public int getID() {
        return Objects.hash(this);
    }

}
