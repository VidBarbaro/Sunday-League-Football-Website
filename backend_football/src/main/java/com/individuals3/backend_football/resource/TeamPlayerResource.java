package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.HttpResponse;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.team.TeamNotFoundException;
import com.individuals3.backend_football.service.TeamPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/teamPlayers")
public class TeamPlayerResource {
    public static final String PLAYER_REMOVED_FROM_TEAM_SUCCESSFULLY = "Player removed from team successfully";

    private TeamPlayersService teamPlayersService;

    @Autowired
    public TeamPlayerResource(TeamPlayersService teamPlayersService) {
        this.teamPlayersService = teamPlayersService;
    }

    @GetMapping({"{id}"})
    public ArrayList<User> getPlayersFromTeam(@PathVariable("id") Long teamId) {
        return teamPlayersService.getPlayersForTeam(teamId);
    }

    @PostMapping({"addPlayerToTeam"})
    @PreAuthorize("hasAnyAuthority('team:modify')")
    public TeamPlayers addPlayerToTeam(@RequestBody TeamPlayers teamPlayers) {
        return teamPlayersService.addPlayerToTeam(teamPlayers);
    }

    @DeleteMapping({"removePlayerFromTeam/{playerId}"})
    @PreAuthorize("hasAnyAuthority('team:modify')")
    public ResponseEntity<HttpResponse> removePlayerFromTeam(@PathVariable("playerId") Long playerId) throws IOException {
        teamPlayersService.removePlayerFromTeam(playerId);
        return response(OK, PLAYER_REMOVED_FROM_TEAM_SUCCESSFULLY);
    }

    @GetMapping("/playerId/{playerId}")
    public ResponseEntity<Team> getTeamByPlayerId(@PathVariable("playerId") Long playerId) throws TeamNotFoundException {
        Team team = teamPlayersService.getTeamForPlayer(playerId);
        return new ResponseEntity<>(team, OK);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
