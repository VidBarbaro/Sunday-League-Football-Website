package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsResource {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsResource(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/topGoalsForSingleMatch")
    public ResponseEntity<List<Match>> getTopTeamsWithMostScoreGoalsInSingleMatch() {
        List<Match> matches = statisticsService.getTopMatchesWithMostScoreGoalsInSingleMatch();
        return new ResponseEntity<>(matches, OK);
    }

    @GetMapping("/top3HomeTeams")
    public ResponseEntity<List<Match>> getTopTeamsWithMostHomeWins() {
        List<Match> teams = statisticsService.getTopTeamsWithMostHomeWins();
        return new ResponseEntity<>(teams, OK);
    }

    @GetMapping("/top3AwayTeams")
    public ResponseEntity<List<Match>> getTopTeamsWithMostAwayWins() {
        List<Match> teams = statisticsService.getTopTeamsWithMostAwayWins();
        return new ResponseEntity<>(teams, OK);
    }
}
