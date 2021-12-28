package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.match.DateForNewMatchHasAlreadyPassedException;
import com.individuals3.backend_football.exception.match.TeamAlreadyHasMatchThatDateException;
import com.individuals3.backend_football.exception.team.TeamNotFoundException;
import com.individuals3.backend_football.service.MatchService;
import com.individuals3.backend_football.service.TeamService;
import com.individuals3.backend_football.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/match")
public class MatchResource {

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
        LocalDateTime matchDateTime = LocalDateTime.parse(dateTime);
        String matchLocation = location;
        Match match = new Match(homeTeam, awayTeam, referee, matchDateTime, matchLocation);
        return matchService.createNewMatch(match);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getMatches();
        return new ResponseEntity<>(matches, OK);
    }
}
