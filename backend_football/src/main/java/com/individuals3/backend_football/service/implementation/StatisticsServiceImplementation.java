package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.domain.TeamTablePosition;
import com.individuals3.backend_football.repository.MatchRepository;
import com.individuals3.backend_football.repository.TeamPlayersRepository;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.repository.TeamTablePositionRepository;
import com.individuals3.backend_football.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Qualifier("StatisticsService")
public class StatisticsServiceImplementation implements StatisticsService {

    private TeamRepository teamRepository;
    private TeamTablePositionRepository teamTableRepository;
    private MatchRepository matchRepository;

    @Autowired
    public StatisticsServiceImplementation(TeamRepository teamRepository, TeamTablePositionRepository teamTableRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.teamTableRepository = teamTableRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> getTopMatchesWithMostScoreGoalsInSingleMatch() {
        List<Match> mostHomeTeamGoals = matchRepository.findAllByOrderByHomeTeamGoalsDesc();
        List<Match> mostAwayTeamGoals = matchRepository.findAllByOrderByAwayTeamGoalsDesc();
        List<Match> topMatchesWithMostGoalsInSingleMatch = new ArrayList<>();
        for (int i = 0; topMatchesWithMostGoalsInSingleMatch.size() < 3; i++) {
            if(mostHomeTeamGoals.get(i).getHomeTeamGoals() > mostAwayTeamGoals.get(i).getAwayTeamGoals()) {
                topMatchesWithMostGoalsInSingleMatch.add(mostHomeTeamGoals.get(i));
            }
            else if (mostHomeTeamGoals.get(i).getHomeTeamGoals() < mostAwayTeamGoals.get(i).getAwayTeamGoals()) {
                topMatchesWithMostGoalsInSingleMatch.add(mostAwayTeamGoals.get(i));
            }
        }
        return topMatchesWithMostGoalsInSingleMatch;
    }

    @Override
    public List<Team> getTopTeamsWithMostPlayersSigned() {
//        List<Team> teamsWithMostPlayers = new ArrayList<>();
//        for (int i = 0; teamsWithMostPlayers.size() < 2; i++) {
//            teamPlayersRepository.
//        }
        return null;
    }

    @Override
    public List<Match> getTopTeamsWithMostHomeWins() {
        List<Match> mostHomeTeamGoals = matchRepository.findAllByOrderByHomeTeamGoalsDesc();
        List<Match> top3Teams = new ArrayList<>();
        for (int i = 0; top3Teams.size() < 3; i++) {
            top3Teams.add(mostHomeTeamGoals.get(i));
        }
        return top3Teams;
    }

    @Override
    public List<Match> getTopTeamsWithMostAwayWins() {
        List<Match> mostAwayTeamGoals = matchRepository.findAllByOrderByAwayTeamGoalsDesc();
        List<Match> top3Teams = new ArrayList<>();
        for (int i = 0; top3Teams.size() < 3; i++) {
            top3Teams.add(mostAwayTeamGoals.get(i));
        }
        return top3Teams;
    }
}
