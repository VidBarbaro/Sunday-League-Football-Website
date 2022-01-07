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
        return this.teamTablePositionRepository.findAllByOrderByPointsDesc();
    }

    @Override
    public TeamTablePosition addTeamToLeagueTable(TeamTablePosition teamTablePosition) {
        teamTablePosition.setPoints(0);
        teamTablePosition.setWins(0);
        teamTablePosition.setLoses(0);
        teamTablePosition.setDraws(0);
        teamTablePosition.setGoalsFor(0);
        teamTablePosition.setGoalsAgainst(0);
        teamTablePositionRepository.save(teamTablePosition);
        return teamTablePosition;
    }

    @Override
    public String addInfoFromMatchResult(Match match) {
        int homeTeamGoals = match.getHomeTeamGoals();
        int awayTeamGoals = match.getAwayTeamGoals();

        if(homeTeamGoals > awayTeamGoals) {
            TeamTablePosition homeTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getHomeTeamId());
            homeTeamTablePosition.addPoints(WIN_POINTS);
            homeTeamTablePosition.addWin();
            homeTeamTablePosition.addGoalsFor(match.getHomeTeamGoals());
            homeTeamTablePosition.addGoalsAgainst(match.getAwayTeamGoals());
            teamTablePositionRepository.save(homeTeamTablePosition);
            TeamTablePosition awayTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getAwayTeamId());
            awayTeamTablePosition.addLoss();
            awayTeamTablePosition.addGoalsFor(match.getAwayTeamGoals());
            awayTeamTablePosition.addGoalsAgainst(match.getHomeTeamGoals());
            teamTablePositionRepository.save(awayTeamTablePosition);
            return HOME_TEAM_WIN;
        }
        else if(homeTeamGoals < awayTeamGoals) {
            TeamTablePosition awayTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getAwayTeamId());
            awayTeamTablePosition.addPoints(WIN_POINTS);
            awayTeamTablePosition.addWin();
            awayTeamTablePosition.addGoalsFor(match.getAwayTeamGoals());
            awayTeamTablePosition.addGoalsAgainst(match.getHomeTeamGoals());
            teamTablePositionRepository.save(awayTeamTablePosition);
            TeamTablePosition homeTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getHomeTeamId());
            homeTeamTablePosition.addLoss();
            homeTeamTablePosition.addGoalsFor(match.getHomeTeamGoals());
            homeTeamTablePosition.addGoalsAgainst(match.getAwayTeamGoals());
            teamTablePositionRepository.save(homeTeamTablePosition);
            return AWAY_TEAM_WIN;
        }
        else {
            TeamTablePosition homeTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getHomeTeamId());
            homeTeamTablePosition.addPoints(DRAW_POINTS);
            homeTeamTablePosition.addDraw();
            homeTeamTablePosition.addGoalsFor(match.getHomeTeamGoals());
            homeTeamTablePosition.addGoalsAgainst(match.getAwayTeamGoals());
            teamTablePositionRepository.save(homeTeamTablePosition);
            TeamTablePosition awayTeamTablePosition =  teamTablePositionRepository.findTeamTablePositionByTeamId(match.getAwayTeamId());
            awayTeamTablePosition.addPoints(DRAW_POINTS);
            awayTeamTablePosition.addDraw();
            awayTeamTablePosition.addGoalsFor(match.getAwayTeamGoals());
            awayTeamTablePosition.addGoalsAgainst(match.getHomeTeamGoals());
            teamTablePositionRepository.save(awayTeamTablePosition);
            return MATCH_DRAW;
        }
    }


}
