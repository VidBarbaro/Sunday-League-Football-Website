package com.individuals3.backend_football.domain;

import javax.persistence.*;

@Entity
@Table(name = "league_table")
public class TeamTablePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team teamId;

    @Column(name = "points")
    private int points;

    public TeamTablePosition() { }

    public TeamTablePosition(Long id, Team teamId, int points) {
        this.id = id;
        this.teamId = teamId;
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
