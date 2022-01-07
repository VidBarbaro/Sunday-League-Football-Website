package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.*;
import com.individuals3.backend_football.repository.MatchRepository;
import com.individuals3.backend_football.service.MatchService;
import com.individuals3.backend_football.service.TeamTablePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/teamTablePosition")
public class TeamTablePositionResource {

    private TeamTablePositionService teamTablePositionService;
    private MatchRepository matchRepository;

    @Autowired
    public TeamTablePositionResource(TeamTablePositionService teamTablePositionService, MatchRepository matchRepository) {
        this.teamTablePositionService = teamTablePositionService;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TeamTablePosition>> getAllTeamTablePositions() {
        List<TeamTablePosition> teamTablePositions = teamTablePositionService.getAllTeamTablePositions();
        return new ResponseEntity<>(teamTablePositions, OK);
    }

    @PostMapping({"addTeamToLeagueTable"})
    @PreAuthorize("hasAnyAuthority('points:update')")
    public TeamTablePosition addTeamToLeagueTable(@RequestBody TeamTablePosition teamTablePosition) {
        return teamTablePositionService.addTeamToLeagueTable(teamTablePosition);
    }

    @PostMapping({"addPointsFromMatchResult"})
    @PreAuthorize("hasAnyAuthority('points:update')")
    public String addPointsFromMatchResult(@RequestBody long matchId) {
        Match match = matchRepository.findMatchById(matchId);
        if (match != null && match.getIsFinished() == true) {
            return teamTablePositionService.addInfoFromMatchResult(match);
        }
        return null;
    }
}
