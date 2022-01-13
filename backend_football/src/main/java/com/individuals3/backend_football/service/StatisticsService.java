package com.individuals3.backend_football.service;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;

import java.util.List;

public interface StatisticsService {

    List<Match> getTopMatchesWithMostScoreGoalsInSingleMatch();

    List<Team> getTopTeamsWithMostPlayersSigned();

    List<Match> getTopTeamsWithMostHomeWins();

    List<Match> getTopTeamsWithMostAwayWins();
}
