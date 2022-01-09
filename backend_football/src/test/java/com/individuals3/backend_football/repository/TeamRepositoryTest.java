package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.constant.Authority;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TeamRepositoryTest {

    public static final Long TEAM_ID = 10L;
    public static final String TEAM_NAME = "NK Rovinj";
    public static final String TEAM_STRING_ID = "1821115926";
    public static final String TEAM_IMAGE_URL = "http://localhost:8081/team/image/NK%20Rovinj/NK%20Rovinj.jpg";
    public static final String TEAM_MANAGER_ID = "8694608103";

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
        teamRepository.deleteAll();
    }

    @Test
    @Transactional
    void shouldFindTeamByName() {
        Team team = teamRepository.findTeamByName(TEAM_NAME);
        Assert.assertEquals(TEAM_ID, team.getId());
        Assert.assertEquals(TEAM_STRING_ID, team.getTeamId());
        Assert.assertEquals(TEAM_IMAGE_URL, team.getClubLogoUrl());
        Assert.assertEquals(TEAM_MANAGER_ID, team.getTeamManagerId());
    }

    @Test
    @Transactional
    void shouldFindTeamByTeamManagerId() {
        Team team = teamRepository.findTeamByTeamManagerId(TEAM_MANAGER_ID);
        Assert.assertEquals(TEAM_ID, team.getId());
        Assert.assertEquals(TEAM_NAME, team.getName());
        Assert.assertEquals(TEAM_STRING_ID, team.getTeamId());
        Assert.assertEquals(TEAM_IMAGE_URL, team.getClubLogoUrl());
    }

    @Test
    @Transactional
    void shouldFindTeamById() {
        Team team = teamRepository.findTeamById(TEAM_ID);
        Assert.assertEquals(TEAM_MANAGER_ID, team.getTeamManagerId());
        Assert.assertEquals(TEAM_NAME, team.getName());
        Assert.assertEquals(TEAM_STRING_ID, team.getTeamId());
        Assert.assertEquals(TEAM_IMAGE_URL, team.getClubLogoUrl());
    }
}