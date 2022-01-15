package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.team.TeamNotFoundException;
import com.individuals3.backend_football.repository.MatchRepository;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.service.MatchService;
import com.individuals3.backend_football.service.TeamService;
import com.individuals3.backend_football.service.UserService;
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

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MatchServiceImplementationTest {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void itShouldGetMatches() {
        List<Match> allMatches = matchService.getMatches();
        Assert.assertEquals(matchRepository.findAll(), allMatches);
    }

    @Test
    @Transactional
    void itShouldGetMatchesForTeam() throws TeamNotFoundException {
        Team team = teamService.findTeamByName("Arsenal");
        List<Match> matches = matchService.getMatchesForTeam(team.getId());
        Assert.assertEquals(3, matches.size());
    }

    @Test
    @Transactional
    void itShouldGetMatchesForReferee() {
        User referee = userService.findUserByUsername("referee1");
        List<Match> matches = matchService.getMatchesForReferee(referee.getId());
        Assert.assertEquals(5, matches.size());
    }

    @Test
    @Transactional
    void itShouldDeleteMatch() throws IOException {
        matchService.deleteMatch(1L);
        Match match = matchRepository.findMatchById(1L);
        Assert.assertEquals(null, match);
    }
}