package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findTeamByName(String teamName);

    Team findTeamByTeamManagerId(String teamManagerId);
}
