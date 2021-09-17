package com.example.individualproject.model;

import java.util.Date;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class TeamManager {

    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private String club; // needs to be type Club

    public TeamManager(int id, String firstName, String lastName, String country, String club) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.club = club;
    }

    public TeamManager() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamManager teamManager = (TeamManager) o;
        return id == teamManager.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Team manager{" +
                "id=" + id +
                ", name='" + firstName + " " + lastName + '\'' +
                ", country=" + country +
                '}';
    }
}

