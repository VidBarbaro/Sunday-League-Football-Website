package com.individuals3.backend_football.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TeamTest {

    @AfterEach
    void tearDown() {
    }

    public static final Long ID = 100L;
    public static final String TEAM_ID = "1324354657";
    public static final String TEAM_NAME = "TestTeam";
    public static final String TEAM_LOGO_URL = "test/url/image";
    public static final String TEAM_TEAM_MANAGER_ID = "8993929421";

    Team team = new Team(ID, TEAM_ID, TEAM_NAME, TEAM_LOGO_URL, TEAM_TEAM_MANAGER_ID);

    @Test
    @Transactional
    void setId() {
        // arrange

        // act
        team.setId(101L);

        // assert
        Assertions.assertEquals(101L, team.getId());
    }

    @Test
    @Transactional
    void setTeamId() {
        // arrange

        // act
        team.setTeamId("0897867564");

        // assert
        Assertions.assertEquals("0897867564", team.getTeamId());
    }

    @Test
    @Transactional
    void setName() {
        // arrange

        // act
        team.setName("SetName");

        // assert
        Assertions.assertEquals("SetName", team.getName());
    }

    @Test
    @Transactional
    void setClubLogoUrl() {
        // arrange

        // act
        team.setClubLogoUrl("new/logo");

        // assert
        Assertions.assertEquals("new/logo", team.getClubLogoUrl());
    }

    @Test
    @Transactional
    void setTeamManagerId() {
        // arrange

        // act
        team.setTeamManagerId("1112223334");

        // assert
        Assertions.assertEquals("1112223334", team.getTeamManagerId());
    }
}