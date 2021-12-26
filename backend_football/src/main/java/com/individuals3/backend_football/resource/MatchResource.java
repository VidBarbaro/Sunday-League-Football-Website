package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.exception.match.DateForNewMatchHasAlreadyPassedException;
import com.individuals3.backend_football.exception.match.TeamAlreadyHasMatchThatDateException;
import com.individuals3.backend_football.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchResource {

    private MatchService matchService;

    @Autowired
    public MatchResource(MatchService matchService) { this.matchService = matchService; }

    @PostMapping({"createMatch"})
    @PreAuthorize("hasAnyAuthority('user:create')")
    public Match addPlayerToTeam(@RequestBody Match match) throws TeamAlreadyHasMatchThatDateException, DateForNewMatchHasAlreadyPassedException {

        return matchService.createNewMatch(match);
    }
}
