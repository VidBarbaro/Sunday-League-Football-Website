package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.HttpResponse;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.domain.*;
import com.individuals3.backend_football.exception.team.TeamNotFoundException;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/team")
public class TeamResource extends ExceptionHandling {
    private TeamService teamService;
    private TeamRepository teamRepository;
    public static final String TEAM_DELETED_SUCCESSFULLY = "Team deleted successfully";
    public static final String PLAYER_ADDED_SUCCESSFULLY_TO_TEAM = "Player added successfully to team";

    @Autowired
    public TeamResource(TeamService teamService, TeamRepository teamRepository) {
        this.teamService = teamService;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/name/{teamName}")
    public ResponseEntity<Team> getTeamByTeamName(@PathVariable("teamName") String teamName) throws TeamNotFoundException {
        Team team = teamService.findTeamByName(teamName);
        return new ResponseEntity<>(team, OK);
    }

    @GetMapping("/managerId/{teamManagerId}")
    public ResponseEntity<Team> getTeamByTeamManagerId(@PathVariable("teamManagerId") Long teamManagerId) throws TeamNotFoundException {
        Team team = teamService.findTeamByTeamManagerId(teamManagerId);
        return new ResponseEntity<>(team, OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('team:add')")
    public ResponseEntity<Team> addNewTeam(@RequestParam("teamName") String teamName,
                                           @RequestParam("teamManagerId") Long teamManagerId,
                                           @RequestParam(value = "clubLogo", required = false) MultipartFile profileImage) throws IOException, NotAnImageFileException {
        Team newTeam = teamService.addNewTeam(teamName, profileImage, teamManagerId);
        return new ResponseEntity<>(newTeam, OK);
    }

    @DeleteMapping("/delete/{teamName}")
    @PreAuthorize("hasAnyAuthority('team:delete')")
    public ResponseEntity<HttpResponse> deleteTeam(@PathVariable("teamName") String teamName) throws IOException {
        teamService.deleteTeam(teamName);
        return response(OK, TEAM_DELETED_SUCCESSFULLY);
    }


    @PostMapping("/add/{teamName}")
    @PreAuthorize("hasAnyAuthority('team:modify')")
    public ResponseEntity<HttpResponse> addPlayerToTeam(@PathVariable("teamName") String teamName,
                                                        @RequestParam("player") User player) throws IOException {
        Team team = teamRepository.findTeamByName(teamName);
//        teamService.addPlayerToTeam(player, team);
        return response(OK, PLAYER_ADDED_SUCCESSFULLY_TO_TEAM);
    }



    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }

}
