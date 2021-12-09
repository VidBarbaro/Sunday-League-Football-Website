package com.individuals3.backend_football.resource;

import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.service.TeamPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teamPlayers")
public class TeamPlayerResource {

    private TeamPlayersService teamPlayersService;

    @Autowired
    public TeamPlayerResource(TeamPlayersService teamPlayersService) {
        this.teamPlayersService = teamPlayersService;
    }

    @PostMapping({"addPlayerToTeam"})
    public TeamPlayers addPlayerToTeam(@RequestBody TeamPlayers teamPlayers) {
        return teamPlayersService.addPlayerToTeam(teamPlayers);
    }
}
