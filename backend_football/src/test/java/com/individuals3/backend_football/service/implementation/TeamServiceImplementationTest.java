package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.domain.NotAnImageFileException;
import com.individuals3.backend_football.exception.team.ManagerAlreadyHasTeamException;
import com.individuals3.backend_football.exception.team.TeamNameExistsException;
import com.individuals3.backend_football.exception.team.TeamNotFoundException;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.repository.UserRepository;
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
class TeamServiceImplementationTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
    }

    public static String TEAM_NAME = "Arsenal";
    public static String TEAM_MANAGER_ID = "0001994063";

    @Test
    @Transactional
    void itShouldFindTeamByName() throws TeamNotFoundException {
        Team team = teamService.findTeamByName(TEAM_NAME);
        Assert.assertEquals(TEAM_MANAGER_ID, team.getTeamManagerId());
        Assert.assertEquals(TEAM_NAME, team.getName());
    }

    @Test
    @Transactional
    void itShouldFindTeamByTeamManagerId() throws TeamNotFoundException {
        Team team = teamService.findTeamByTeamManagerId(TEAM_MANAGER_ID);
        Assert.assertEquals(TEAM_MANAGER_ID, team.getTeamManagerId());
        Assert.assertEquals(TEAM_NAME, team.getName());
    }

    @Test
    @Transactional
    void itShouldAddNewTeam() throws TeamNameExistsException, ManagerAlreadyHasTeamException, IOException, TeamNotFoundException, NotAnImageFileException {
        Team team = teamService.addNewTeam("testName", null, "8993929421");
        Team aTeam = teamRepository.findTeamByName("testName");
        Assert.assertEquals(team, aTeam);
    }

    @Test
    @Transactional
    void itShouldDeleteTeam() throws IOException {
        teamService.deleteTeam(TEAM_NAME);
        Team team = teamRepository.findTeamByName(TEAM_NAME);
        Assert.assertEquals(team, null);
    }

    @Test
    @Transactional
    void itShouldGetTeams() {
        List<Team> allTeams = teamService.getTeams();
        Assert.assertEquals(teamRepository.findAll(), allTeams);
    }
}