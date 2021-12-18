package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPlayersRepository extends JpaRepository<TeamPlayers, Long> {

    TeamPlayers[] findTeamPlayersByTeam_TeamId(String teamId);

    TeamPlayers[] findTeamPlayersByTeamId(Long teamId);
}
