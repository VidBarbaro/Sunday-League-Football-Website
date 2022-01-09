package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.domain.User;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TeamPlayersRepositoryTest {

    public static final Long TEAM_PLAYER_ID = 2L;
    public static final Long PLAYER_ID = 8L;
    public static final Long TEAM_ID = 10L;

    @Autowired
    private TeamPlayersRepository teamPlayersRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
        teamPlayersRepository.deleteAll();
    }

    @Test
    @Transactional
    void findTeamPlayersByTeamId() {
        Team team = teamRepository.findTeamById(TEAM_ID);
        TeamPlayers[] teamPlayers = teamPlayersRepository.findTeamPlayersByTeamId(team);
        Assert.assertEquals(1, teamPlayers.length);
    }

    @Test
    @Transactional
    void findTeamPlayersByPlayerId() {
        User user = userRepository.findUserById(PLAYER_ID);
        TeamPlayers teamPlayers = teamPlayersRepository.findTeamPlayersByPlayerId(user);
        Assert.assertEquals(TEAM_PLAYER_ID, teamPlayers.getId());
        Assert.assertEquals(TEAM_ID, teamPlayers.getTeamId().getId());
        Assert.assertEquals(PLAYER_ID, teamPlayers.getPlayerId().getId());
    }
}