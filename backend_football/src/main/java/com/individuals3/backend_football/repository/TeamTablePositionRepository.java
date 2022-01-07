package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamTablePosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamTablePositionRepository extends JpaRepository<TeamTablePosition, Long> {

    List<TeamTablePosition> findAllByOrderByPointsDesc();

    TeamTablePosition findTeamTablePositionByTeamId(Team teamId);

}
