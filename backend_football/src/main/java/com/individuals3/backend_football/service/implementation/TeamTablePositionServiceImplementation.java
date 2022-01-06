package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamTablePosition;
import com.individuals3.backend_football.repository.MatchRepository;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.repository.TeamTablePositionRepository;
import com.individuals3.backend_football.service.TeamTablePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamTablePositionServiceImplementation implements TeamTablePositionService {
    static int WIN_POINTS = 3;
    static int DRAW_POINTS = 1;

    static String HOME_TEAM_WIN = "Home team won!";
    static String AWAY_TEAM_WIN = "Away team won!";
    static String MATCH_DRAW = "The match is a draw!";

    private TeamTablePositionRepository teamTablePositionRepository;
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    @Autowired
    public TeamTablePositionServiceImplementation(TeamRepository teamRepository, MatchRepository matchRepository, TeamTablePositionRepository teamTablePositionRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.teamTablePositionRepository = teamTablePositionRepository;
    }

    @Override
    public List<TeamTablePosition> getAllTeamTablePositions() {
        return this.teamTablePositionRepository.findAll();
    }

    @Override
    public TeamTablePosition addTeamToLeagueTable(TeamTablePosition teamTablePosition) {
        teamTablePosition.setPoints(0);
        teamTablePositionRepository.save(teamTablePosition);
        return teamTablePosition;
    }

    @Override
    public String addPointsFromMatchResult(Match match) {
        int homeTeamGoals = match.getHomeTeamGoals();
        int awayTeamGoals = match.getAwayTeamGoals();
        TeamTablePosition homeTeamTablePosition;
        TeamTablePosition awayTeamTablePosition;
        if(homeTeamGoals > awayTeamGoals) {
             homeTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getHomeTeamId());
             homeTeamTablePosition.addPoints(WIN_POINTS);
             teamTablePositionRepository.save(homeTeamTablePosition);
             return HOME_TEAM_WIN;
        }
        else if(homeTeamGoals < awayTeamGoals) {
            awayTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getAwayTeamId());
            awayTeamTablePosition.addPoints(WIN_POINTS);
            teamTablePositionRepository.save(awayTeamTablePosition);
            return AWAY_TEAM_WIN;
        }
        else {
            homeTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getHomeTeamId());
            homeTeamTablePosition.addPoints(DRAW_POINTS);
            teamTablePositionRepository.save(homeTeamTablePosition);
            awayTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getAwayTeamId());
            awayTeamTablePosition.addPoints(DRAW_POINTS);
            teamTablePositionRepository.save(awayTeamTablePosition);
            return MATCH_DRAW;
        }
    }


}
