package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.repository.TeamPlayersRepository;
import com.individuals3.backend_football.service.TeamPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamPlayersServiceImplementation implements TeamPlayersService {

    private TeamPlayersRepository teamPlayersRepository;

    @Autowired
    public TeamPlayersServiceImplementation(TeamPlayersRepository teamPlayersRepository) {
        this.teamPlayersRepository = teamPlayersRepository;
    }

    @Override
    public TeamPlayers addPlayerToTeam(TeamPlayers teamPlayers) {
        teamPlayersRepository.save(teamPlayers);
        return teamPlayers;
    }

    @Override
    public boolean removePlayerFromTeam(Long teamPlayersId) {
        if (teamPlayersRepository.findById(teamPlayersId).isPresent()) {
            teamPlayersRepository.deleteById(teamPlayersId);
            return true;
        }
        return false;
    }
}
