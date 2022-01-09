package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.constant.Authority;
import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;
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
class MatchRepositoryTest {

    public static final Long MATCH_ID = 26L;
    public static final String MATCH_LOCATION = "Rovinj Stadium";
    public static final Long AWAY_TEAM_ID = 12L;
    public static final Long HOME_TEAM_ID = 10L;
    public static final Long REFEREE_ID = 9L;
    public static final int AWAY_TEAM_GOALS = 0;
    public static final int HOME_TEAM_GOALS = 0;
    public static final boolean IS_FINISHED = false;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
        matchRepository.deleteAll();
    }

    @Test
    @Transactional
    void findMatchById() {
        Match match = matchRepository.findMatchById(MATCH_ID);
        Assert.assertEquals(MATCH_LOCATION, match.getLocation());
        Assert.assertEquals(HOME_TEAM_ID, match.getHomeTeamId().getId());
        Assert.assertEquals(AWAY_TEAM_ID, match.getAwayTeamId().getId());
        Assert.assertEquals(HOME_TEAM_GOALS, match.getHomeTeamGoals());
        Assert.assertEquals(AWAY_TEAM_GOALS, match.getAwayTeamGoals());
        Assert.assertEquals(REFEREE_ID, match.getRefereeId().getId());
        Assert.assertEquals(IS_FINISHED, match.getIsFinished());
    }

    @Test
    @Transactional
    void findMatchByHomeTeamId() {
        Team team = teamRepository.findTeamById(HOME_TEAM_ID);
        List<Match> matches = matchRepository.findMatchByHomeTeamId(team);
        Assert.assertEquals(1, matches.size());
    }

    @Test
    @Transactional
    void findMatchByAwayTeamId() {
        Team team = teamRepository.findTeamById(AWAY_TEAM_ID);
        List<Match> matches = matchRepository.findMatchByAwayTeamId(team);
        Assert.assertEquals(1, matches.size());
    }
}