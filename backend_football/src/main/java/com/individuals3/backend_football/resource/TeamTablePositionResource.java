package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.*;
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

    @Autowired
    public TeamTablePositionResource(TeamTablePositionService teamTablePositionService) {
        this.teamTablePositionService = teamTablePositionService;
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
    public String addPointsFromMatchResult(@RequestBody Match match) {
        return teamTablePositionService.addPointsFromMatchResult(match);
    }
}
