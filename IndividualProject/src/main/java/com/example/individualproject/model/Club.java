package com.example.individualproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Club {
    private int id;
    private String name;
    private List<Player> players;
    private TeamManager teamManager;

    public Club(int id, String name, TeamManager teamManager) {
        this.id = id;
        this.name = name;
        this.teamManager = teamManager;
        this.players = new ArrayList<Player>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club = (Club) o;
        return id == club.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", squad_size=" + players.size() +
                '}';
    }
}
