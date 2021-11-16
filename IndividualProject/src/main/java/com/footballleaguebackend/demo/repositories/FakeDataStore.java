package com.footballleaguebackend.demo.repositories;

import com.footballleaguebackend.demo.models.Club;
import com.footballleaguebackend.demo.models.Player;
import com.footballleaguebackend.demo.models.TeamManager;

import java.util.ArrayList;
import java.util.List;

public class FakeDataStore {
    private final List<Player> playerList = new ArrayList<>();
    private final List<TeamManager> teamManagerList = new ArrayList<>();
    private final List<Club> clubList = new ArrayList<>();

    public FakeDataStore() {
        // work this out better, add few more countries and students
        Player vid = new Player(1, "Vid", "Barbaro", "Croatia", "Pusphaira P10");
        Player robin = new Player(2, "Robin", "van der Laar", "Netherlands", "Pusphaira P2");
        Player diogo = new Player(3, "Diogo", "Araujo Teixeira", "Portugal", "Pusphaira P5");
        playerList.add(vid);
        playerList.add(robin);
        playerList.add(diogo);

        TeamManager nadan = new TeamManager(1, "Nadan", "Zaharija", "Croatia", "NK Rovinj");
        teamManagerList.add(nadan);

        Club pusphaira = new Club(1, "Pusphaira", nadan);
        clubList.add(pusphaira);
    }

    //Player
    public List<Player> getPlayers() {
        return playerList;
    }

    public List<Player> getPlayers(Club club) {
        List<Player> filtered = new ArrayList<>();
        for (Player player : playerList) {
            if (player.getClub().equals(club)) {
                filtered.add(player);
            }
        }
        return filtered;
    }

    public Player getPlayer(int id) {
        for (Player player : playerList) {
            if (player.getId() == id)
                return player;
        }
        return null;
    }

    public boolean deletePlayer(int id) {
        Player player = getPlayer(id);
        if (player == null){
            return false;
        }

        return playerList.remove(player);
    }


    public boolean addPlayer(Player player) {
        if (this.getPlayer(player.getId()) != null){
            return false;
        }
        playerList.add(player);
        return true;
    }

    public boolean updatePlayer(Player player) {
        Player oldPlayer = this.getPlayer(player.getId());
        if (oldPlayer == null) {
            return false;
        }
        oldPlayer.setFirstName(player.getFirstName());
        oldPlayer.setLastName(player.getLastName());
        oldPlayer.setCountry(player.getCountry());
        oldPlayer.setClub(player.getClub());
        return true;
    }

    //    TeamManager
    public List<TeamManager> getTeamManagers() {
        return teamManagerList;
    }

    public List<TeamManager> getTeamManagers(Club club) {
        List<TeamManager> filtered = new ArrayList<>();
        for (TeamManager teamManager : teamManagerList) {
            if (teamManager.getClub().equals(club)) {
                filtered.add(teamManager);
            }
        }
        return filtered;
    }

    public TeamManager getTeamManager(int id) {
        for (TeamManager teamManager : teamManagerList) {
            if (teamManager.getId() == id)
                return teamManager;
        }
        return null;
    }

    public boolean deleteTeamManager(int id) {
        TeamManager teamManager = getTeamManager(id);
        if (teamManager == null){
            return false;
        }
        return teamManagerList.remove(teamManager);
    }


    public boolean addTeamManager(TeamManager teamManager) {
        if (this.getTeamManager(teamManager.getId()) != null){
            return false;
        }
        teamManagerList.add(teamManager);
        return true;
    }

    public boolean updateTeamManager(TeamManager teamManager) {
        TeamManager oldTeamManager = this.getTeamManager(teamManager.getId());
        if (teamManager == null) {
            return false;
        }
        oldTeamManager.setFirstName(teamManager.getFirstName());
        oldTeamManager.setLastName(teamManager.getLastName());
        oldTeamManager.setClub(teamManager.getClub());
        oldTeamManager.setCountry(teamManager.getCountry());

        return true;
    }
}
