package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.repository.TeamPlayersRepository;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.repository.UserRepository;
import com.individuals3.backend_football.service.TeamPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Service
public class TeamPlayersServiceImplementation implements TeamPlayersService {

    private TeamPlayersRepository teamPlayersRepository;
    private UserRepository userRepository;
    private TeamRepository teamRepository;

    @Autowired
    public TeamPlayersServiceImplementation(TeamPlayersRepository teamPlayersRepository, UserRepository userRepository, TeamRepository teamRepository) {
        this.teamPlayersRepository = teamPlayersRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamPlayers addPlayerToTeam(TeamPlayers teamPlayers) {
        teamPlayersRepository.save(teamPlayers);
        User user = this.userRepository.findUserByUsername(teamPlayers.getPlayerId().getUsername());
        user.setActive(true);
        this.userRepository.save(user);
        return teamPlayers;
    }

    @Override
    public boolean removePlayerFromTeam(Long teamPlayersId) {
//        if (teamPlayersRepository.findById(teamPlayersId).isPresent()) {
//            teamPlayersRepository.deleteById(teamPlayersId);
//            return true;
//        }
        return false;
    }

    @Override
    public ArrayList<User> getPlayersForTeam(Long teamId) {
        ArrayList<User> players = new ArrayList<>();
        TeamPlayers[] teamPlayers = teamPlayersRepository.findTeamPlayersByTeamId(teamRepository.findTeamById(teamId));
        for (int i=0; i<teamPlayers.length; i++) {
            players.add(userRepository.findUserById(teamPlayers[i].getPlayerId().getId()));
        }
        return players;
//        return null;
    }
}
