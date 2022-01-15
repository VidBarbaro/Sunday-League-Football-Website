package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Match findMatchById(Long id);

    List<Match> findMatchByHomeTeamId(Team teamId);

    List<Match> findMatchByAwayTeamId(Team teamId);

    List<Match> findMatchByRefereeId(User userId);

    List<Match> findAllByOrderByHomeTeamGoalsDesc();

    List<Match> findAllByOrderByAwayTeamGoalsDesc();
}
