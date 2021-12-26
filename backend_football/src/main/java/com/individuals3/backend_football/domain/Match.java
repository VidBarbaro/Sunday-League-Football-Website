package com.individuals3.backend_football.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fb_match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "homeTeamId")
    private Team homeTeamId;

    @ManyToOne
    @JoinColumn(name = "awayTeamId")
    private Team awayTeamId;

    @ManyToOne
    @JoinColumn(name = "refereeId")
    private User refereeId;

    @Column(name = "matchDateTime")
    private LocalDateTime matchDateTime;

    @Column(name = "location")
    private String location;

    public Match(Team homeTeamId, Team awayTeamId, User refereeId, LocalDateTime matchDateTime, String location) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.refereeId = refereeId;
        this.matchDateTime = matchDateTime;
        this.location = location;
    }

    public Match() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Team homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Team getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Team awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(User refereeId) {
        this.refereeId = refereeId;
    }
}
