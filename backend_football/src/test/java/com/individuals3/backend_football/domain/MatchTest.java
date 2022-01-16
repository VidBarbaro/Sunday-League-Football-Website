package com.individuals3.backend_football.domain;

import com.individuals3.backend_football.constant.Authority;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MatchTest {

    @AfterEach
    void tearDown() {
    }

    public static final Long ID = 100L;
    public static final String TEAM_ID = "1324354657";
    public static final String TEAM_NAME = "HomeTeam";
    public static final String TEAM_LOGO_URL = "test/url/image";
    public static final String TEAM_TEAM_MANAGER_ID = "8993929421";
    Team homeTeam = new Team(ID, TEAM_ID, TEAM_NAME, TEAM_LOGO_URL, TEAM_TEAM_MANAGER_ID);

    public static final Long ID1 = 200L;
    public static final String TEAM_ID1 = "7564534231";
    public static final String TEAM_NAME1 = "AwayTeam";
    public static final String TEAM_LOGO_URL1 = "test/url/image1";
    public static final String TEAM_TEAM_MANAGER_ID1 = "1249293998";
    Team awayTeam = new Team(ID1, TEAM_ID1, TEAM_NAME1, TEAM_LOGO_URL1, TEAM_TEAM_MANAGER_ID1);

    public static final Long ID3 = 100L;
    public static final String USER_ID = "123467890";
    public static final String USER_FIRST_NAME = "TestFN";
    public static final String USER_LAST_NAME = "TestLN";
    public static final String USER_USERNAME = "testusername";
    public static final String USER_PASSWORD = "testpassword";
    public static final String USER_EMAIL = "test@gmail.com";
    public static final String USER_PROFILE_IMG_URL = "http://localhost:8081/user/image/profile/testusername";
    public static final Date USER_LAST_LOGIN_DATE = new Date();
    public static final Date USER_JOIN_DATE = new Date();
    public static final String USER_ROLE = "ROLE_REFEREE";
    public static final String[] USER_AUTHORITIES = Authority.REFEREE_AUTHORITIES;
    public static final boolean USER_IS_ACTIVE = true;
    public static final boolean USER_IS_NOT_LOCKED = true;
    User referee = new User(ID3, USER_ID, USER_FIRST_NAME, USER_LAST_NAME, USER_USERNAME, USER_PASSWORD, USER_EMAIL, USER_PROFILE_IMG_URL, USER_LAST_LOGIN_DATE, USER_LAST_LOGIN_DATE, USER_JOIN_DATE, USER_ROLE, USER_AUTHORITIES, USER_IS_ACTIVE, USER_IS_NOT_LOCKED);

    public static final LocalDateTime MATCH_DATE_TIME = LocalDateTime.now();
    public static final String MATCH_LOCATION = "Location";
    Match match = new Match(homeTeam, awayTeam, referee, MATCH_DATE_TIME, MATCH_LOCATION);

    @Test
    @Transactional
    void setId() {
        // arrange

        // act
        match.setId(100L);

        // assert
        Assertions.assertEquals(100L, match.getId());
    }

    @Test
    @Transactional
    void setHomeTeamId() {
        // arrange

        // act
        match.setHomeTeamId(awayTeam);

        // assert
        Assertions.assertEquals(awayTeam, match.getAwayTeamId());
    }

    @Test
    @Transactional
    void setAwayTeamId() {
        // arrange

        // act
        match.setAwayTeamId(homeTeam);

        // assert
        Assertions.assertEquals(homeTeam, match.getAwayTeamId());
    }

    @Test
    @Transactional
    void setLocation() {
        // arrange

        // act
        match.setLocation("New Location");

        // assert
        Assertions.assertEquals("New Location", match.getLocation());
    }

    @Test
    @Transactional
    void setRefereeId() {
        // arrange

        // act
        match.setRefereeId(referee);

        // assert
        Assertions.assertEquals(referee, match.getRefereeId());
    }

    @Test
    @Transactional
    void setIsFinished() {
        // arrange

        // act
        match.setIsFinished(false);

        // assert
        Assertions.assertEquals(false, match.getIsFinished());
    }

    @Test
    @Transactional
    void setHomeTeamGoals() {
        // arrange

        // act
        match.setHomeTeamGoals(5);

        // assert
        Assertions.assertEquals(5, match.getHomeTeamGoals());
    }

    @Test
    @Transactional
    void setAwayTeamGoals() {
        // arrange

        // act
        match.setAwayTeamGoals(2);

        // assert
        Assertions.assertEquals(2, match.getAwayTeamGoals());
    }
}