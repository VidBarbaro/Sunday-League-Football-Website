package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.TeamPlayers;
import com.individuals3.backend_football.domain.TeamTablePosition;
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
class TeamTablePositionRepositoryTest {

    public static final Long ID = 9L;
    public static final int POINTS = 3;
    public static final Long TEAM_ID = 10L;
    public static final int WINS = 1;
    public static final int LOSES = 2;
    public static final int DRAWS = 0;
    public static final int GOALS_FOR = 5;
    public static final int GOALS_AGAINST = 9;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamTablePositionRepository teamTablePositionRepository;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
        teamTablePositionRepository.deleteAll();
    }

    @Test
    @Transactional
    void findAllByOrderByPointsDesc() {
        List<TeamTablePosition> teamTablePositions = teamTablePositionRepository.findAllByOrderByPointsDesc();
        Assert.assertEquals(8, teamTablePositions.size());
    }

    @Test
    @Transactional
    void findTeamTablePositionByTeamId() {
        Team team = teamRepository.findTeamById(TEAM_ID);
        TeamTablePosition teamTablePosition = teamTablePositionRepository.findTeamTablePositionByTeamId(team);
        Assert.assertEquals(ID, teamTablePosition.getId());
        Assert.assertEquals(POINTS, teamTablePosition.getPoints());
        Assert.assertEquals(WINS, teamTablePosition.getWins());
        Assert.assertEquals(LOSES, teamTablePosition.getLoses());
        Assert.assertEquals(DRAWS, teamTablePosition.getDraws());
        Assert.assertEquals(GOALS_FOR, teamTablePosition.getGoalsFor());
        Assert.assertEquals(GOALS_AGAINST, teamTablePosition.getGoalsAgainst());
    }
}