package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.TeamPlayers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPlayersRepository extends JpaRepository<TeamPlayers, Long> {
    TeamPlayers[] findTeamPlayersByTeamId(Long teamId);
}
