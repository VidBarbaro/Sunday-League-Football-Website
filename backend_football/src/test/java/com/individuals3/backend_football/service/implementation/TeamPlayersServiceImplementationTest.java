package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.repository.TeamPlayersRepository;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.repository.UserRepository;
import com.individuals3.backend_football.service.TeamPlayersService;
import com.individuals3.backend_football.service.TeamService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TeamPlayersServiceImplementationTest {

    @Autowired
    private TeamPlayersRepository teamPlayersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamPlayersService teamPlayersService;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void itShouldRemovePlayerFromTeam() {
        teamPlayersService.removePlayerFromTeam(12L);
        User player = userRepository.findUserById(12L);
        TeamPlayers teamPlayers = teamPlayersRepository.findTeamPlayersByPlayerId(player);
        Assert.assertEquals(null, teamPlayers);
    }

    @Test
    @Transactional
    void itShouldGetPlayersForTeam() {
        List<User> players = teamPlayersService.getPlayersForTeam(26L);
        Assert.assertEquals(2, players.size());
    }

    @Test
    @Transactional
    void itShouldGetTeamForPlayer() {
        Team team = teamPlayersService.getTeamForPlayer(12L);
        Assert.assertEquals("Arsenal", team.getName());
    }
}