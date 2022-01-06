package com.individuals3.backend_football.service;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.TeamTablePosition;

import java.util.List;

public interface TeamTablePositionService {

    List<TeamTablePosition> getAllTeamTablePositions();

    TeamTablePosition addTeamToLeagueTable(TeamTablePosition teamTablePosition);

    String addPointsFromMatchResult(Match match);
}
