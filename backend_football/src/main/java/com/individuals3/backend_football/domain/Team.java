package com.individuals3.backend_football.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String teamId;
    private String name;
    private String clubLogoUrl;

    public Team() {}

    public Team(Long id, String teamId, String name, String clubLogoUrl) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.clubLogoUrl = clubLogoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClubLogoUrl() {
        return clubLogoUrl;
    }

    public void setClubLogoUrl(String clubLogoUrl) {
        this.clubLogoUrl = clubLogoUrl;
    }
}
