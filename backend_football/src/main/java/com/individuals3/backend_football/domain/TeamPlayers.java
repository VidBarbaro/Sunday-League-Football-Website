package com.individuals3.backend_football.domain;

import javax.persistence.*;

@Entity
@Table(name = "team_players_join")
public class TeamPlayers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team teamId;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private User playerId;

    public TeamPlayers(Long id, Team team, User player) {
        this.id = id;
        this.teamId = team;
        this.playerId = player;
    }

    public TeamPlayers() {
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

    public void setTeamId(Team team) {
        this.teamId = team;
    }

    public User getPlayerId() {
        return playerId;
    }

    public void setPlayerId(User player) {
        this.playerId = player;
    }
}
