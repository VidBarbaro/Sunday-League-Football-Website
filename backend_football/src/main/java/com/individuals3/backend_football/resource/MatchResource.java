package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.*;
import com.individuals3.backend_football.exception.domain.EmailExistsException;
import com.individuals3.backend_football.exception.domain.NotAnImageFileException;
import com.individuals3.backend_football.exception.domain.UserNotFoundException;
import com.individuals3.backend_football.exception.domain.UsernameExistsException;
import com.individuals3.backend_football.exception.match.DateForNewMatchHasAlreadyPassedException;
import com.individuals3.backend_football.exception.match.TeamAlreadyHasMatchThatDateException;
import com.individuals3.backend_football.exception.team.TeamNotFoundException;
import com.individuals3.backend_football.service.MatchService;
import com.individuals3.backend_football.service.TeamService;
import com.individuals3.backend_football.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Convert;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/match")
public class MatchResource {

    private static final String MATCH_DELETED_SUCCESSFULLY = "Match deleted succesfully";
    private MatchService matchService;
    private TeamService teamService;
    private UserService userService;

    @Autowired
    public MatchResource(MatchService matchService, TeamService teamService, UserService userService) {
        this.matchService = matchService;
        this.teamService = teamService;
        this.userService = userService;
    }

    @PostMapping({"createMatch"})
    @PreAuthorize("hasAnyAuthority('user:create')")
    public Match createNewMatch(@RequestParam("homeTeamId") String homeTeamName,
                                @RequestParam("awayTeamId") String awayTeamName,
                                @RequestParam("refereeId") String refereeName,
                                @RequestParam("matchDateTime") String dateTime,
                                @RequestParam("location") String location) throws TeamAlreadyHasMatchThatDateException, DateForNewMatchHasAlreadyPassedException, TeamNotFoundException {
        Team homeTeam = this.teamService.findTeamByName(homeTeamName);
        Team awayTeam = this.teamService.findTeamByName(awayTeamName);
        User referee = this.userService.findUserByUsername(refereeName);
        String newDateTime = dateTime.replace("Z", "");
        LocalDateTime matchDateTime = LocalDateTime.parse(newDateTime);
        String matchLocation = location;
        Match match = new Match(homeTeam, awayTeam, referee, matchDateTime, matchLocation);
        return matchService.createNewMatch(match);
    }

    @PostMapping("/update")
    public ResponseEntity<Match> update(@RequestParam("matchId") String matchId,
                                        @RequestParam("homeTeamId") String homeTeamName,
                                        @RequestParam("awayTeamId") String awayTeamName,
                                        @RequestParam("refereeId") String refereeName,
                                        @RequestParam("matchDateTime") String dateTime,
                                        @RequestParam("location") String location,
                                        @RequestParam("homeTeamGoals") String homeTeamGoals,
                                        @RequestParam("awayTeamGoals") String awayTeamGoals,
                                        @RequestParam("isFinished") String isFinished) throws TeamNotFoundException {
        Long id = Long.parseLong(matchId);
        Team homeTeam = this.teamService.findTeamByName(homeTeamName);
        Team awayTeam = this.teamService.findTeamByName(awayTeamName);
        User referee = this.userService.findUserByUsername(refereeName);
        String newDateTime = dateTime.replace("Z", "");
        LocalDateTime matchDateTime = LocalDateTime.parse(newDateTime);
        int homeGoals = Integer.parseInt(homeTeamGoals);
        int awayGoals = Integer.parseInt(awayTeamGoals);
        Match updatedMatch = matchService.updateMatch(id, homeTeam, awayTeam, referee, matchDateTime, location, homeGoals, awayGoals, Boolean.parseBoolean(isFinished));
        return new ResponseEntity<>(updatedMatch, OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getMatches();
        return new ResponseEntity<>(matches, OK);
    }

    @DeleteMapping("/delete/{matchId}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteMatch(@PathVariable("matchId") Long matchId) throws IOException {
        matchService.deleteMatch(matchId);
        return response(OK, MATCH_DELETED_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
