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

    @Column(name = "wins")
    private int wins;

    @Column(name = "loses")
    private int loses;

    @Column(name = "draws")
    private int draws;

    @Column(name = "goalsFor")
    private int goalsFor;

    @Column(name = "goalsAgainst")
    private int goalsAgainst;

    public TeamTablePosition() { }

    public void addPoints(int points) {
        this.points += points;
    }

    public void addGoalsFor(int goals) { this.goalsFor += goals; }

    public void addGoalsAgainst(int goals) { this.goalsAgainst += goals; }

    public void addWin() { this.wins += 1; }

    public void addLoss() { this.loses += 1; }

    public void addDraw() { this.draws += 1; }

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

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }
}
