package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPlayersRepository extends JpaRepository<TeamPlayers, Long> {

    TeamPlayers[] findTeamPlayersByTeamId(Team teamId);

    TeamPlayers findTeamPlayersByPlayerId(User playerId);

}
