package com.individuals3.backend_football.service.implementation;

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

@Service
public class TeamPlayersServiceImplementation implements TeamPlayersService {

    private TeamPlayersRepository teamPlayersRepository;
    private UserRepository userRepository;

    @Autowired
    public TeamPlayersServiceImplementation(TeamPlayersRepository teamPlayersRepository, UserRepository userRepository) {
        this.teamPlayersRepository = teamPlayersRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TeamPlayers addPlayerToTeam(TeamPlayers teamPlayers) {
        teamPlayersRepository.save(teamPlayers);
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
    public ArrayList<User> getPlayersForTeam(String teamId) {
        ArrayList<User> players = new ArrayList<User>();
        TeamPlayers[] teamPlayers = teamPlayersRepository.findTeamPlayersByTeam_TeamId(teamId);
        for (int i=0; i<teamPlayers.length; i++) {
            players.add(userRepository.findUserById(teamPlayers[i].getPlayer().getId()));
        }
        return players;
    }
}
