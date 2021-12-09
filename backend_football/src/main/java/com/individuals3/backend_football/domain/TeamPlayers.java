package com.individuals3.backend_football.domain;

import javax.persistence.*;

import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.domain.Team;

@Entity
@Table(name = "team_players_join")
public class TeamPlayers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private User player;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }
}
