package com.individuals3.backend_football.service;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.domain.User;

public interface TeamPlayersService {

    TeamPlayers addPlayerToTeam(TeamPlayers teamPlayers);

    boolean removePlayerFromTeam(Long teamPlayersId);
}
