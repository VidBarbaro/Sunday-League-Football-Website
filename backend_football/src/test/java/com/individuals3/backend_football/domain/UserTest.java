package com.individuals3.backend_football.domain;

import com.individuals3.backend_football.constant.Authority;
import com.individuals3.backend_football.repository.UserRepository;
import com.individuals3.backend_football.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
    }

    public static final Long ID = 100L;
    public static final String USER_ID = "123467890";
    public static final String USER_FIRST_NAME = "TestFN";
    public static final String USER_LAST_NAME = "TestLN";
    public static final String USER_USERNAME = "testusername";
    public static final String USER_PASSWORD = "testpassword";
    public static final String USER_EMAIL = "test@gmail.com";
    public static final String USER_PROFILE_IMG_URL = "http://localhost:8081/user/image/profile/testusername";
    public static final Date USER_LAST_LOGIN_DATE = new Date();
    public static final Date USER_JOIN_DATE = new Date();
    public static final String USER_ROLE = "ROLE_USER";
    public static final String[] USER_AUTHORITIES = Authority.USER_AUTHORITIES;
    public static final boolean USER_IS_ACTIVE = true;
    public static final boolean USER_IS_NOT_LOCKED = true;

    User user = new User(ID, USER_ID, USER_FIRST_NAME, USER_LAST_NAME, USER_USERNAME, USER_PASSWORD, USER_EMAIL, USER_PROFILE_IMG_URL, USER_LAST_LOGIN_DATE, USER_LAST_LOGIN_DATE, USER_JOIN_DATE, USER_ROLE, USER_AUTHORITIES, USER_IS_ACTIVE, USER_IS_NOT_LOCKED);

    @Test
    @Transactional
    void itShouldGetId() {
        // arrange

        // act

        // assert
        Assertions.assertEquals(100L, user.getId());
    }

    @Test
    @Transactional
    void itShouldSetId() {
        // arrange

        // act
        user.setId(101L);

        // assert
        Assertions.assertEquals(101L, user.getId());
    }

    @Test
    @Transactional
    void itShouldGetUserId() {
        // arrange

        // act

        // assert
        Assertions.assertEquals("123467890", user.getUserId());
    }

    @Test
    @Transactional
    void itShouldSetUserId() {
        // arrange

        // act
        user.setUserId("0987654321");

        // assert
        Assertions.assertEquals("0987654321", user.getUserId());
    }

    @Test
    @Transactional
    void itShouldGetFirstName() {
        // arrange

        // act

        // assert
        Assertions.assertEquals("TestFN", user.getFirstName());
    }

    @Test
    @Transactional
    void itShouldSetFirstName() {
        // arrange

        // act
        user.setFirstName("FNTest");

        // assert
        Assertions.assertEquals("FNTest", user.getFirstName());
    }

    @Test
    @Transactional
    void itShouldGetLastName() {
        // arrange

        // act

        // assert
        Assertions.assertEquals("TestLN", user.getLastName());
    }

    @Test
    @Transactional
    void itShouldSetLastName() {
        // arrange

        // act
        user.setLastName("LNTest");

        // assert
        Assertions.assertEquals("LNTest", user.getLastName());
    }

    @Test
    @Transactional
    void itShouldGetUsername() {
        // arrange

        // act

        // assert
        Assertions.assertEquals("testusername", user.getUsername());
    }

    @Test
    @Transactional
    void itShouldSetUsername() {
        // arrange

        // act
        user.setUsername("usernametest");

        // assert
        Assertions.assertEquals("usernametest", user.getUsername());
    }

    @Test
    @Transactional
    void itShouldGetPassword() {
        // arrange

        // act

        // assert
        Assertions.assertEquals("testpassword", user.getPassword());
    }

    @Test
    @Transactional
    void itShouldSetPassword() {
        // arrange

        // act
        user.setPassword("0987654321");

        // assert
        Assertions.assertEquals("0987654321", user.getPassword());
    }

    @Test
    @Transactional
    void itShouldGetEmail() {
        // arrange

        // act

        // assert
        Assertions.assertEquals("test@gmail.com", user.getEmail());
    }

    @Test
    @Transactional
    void itShouldSetEmail() {
        // arrange

        // act
        user.setEmail("set@gmail.com");

        // assert
        Assertions.assertEquals("set@gmail.com", user.getEmail());
    }

    @Test
    @Transactional
    void itShouldGetProfileImageUrl() {
        // arrange

        // act

        // assert
        Assertions.assertEquals("http://localhost:8081/user/image/profile/testusername", user.getProfileImageUrl());
    }

    @Test
    @Transactional
    void itShouldSetProfileImageUrl() {
        // arrange

        // act
        user.setProfileImageUrl("newimageurl");

        // assert
        Assertions.assertEquals("newimageurl", user.getProfileImageUrl());
    }

    @Test
    @Transactional
    void itShouldGetRole() {
        // arrange

        // act

        // assert
        Assertions.assertEquals("ROLE_USER", user.getRole());
    }

    @Test
    @Transactional
    void itShouldSetRole() {
        // arrange

        // act
        user.setRole("ROLE_ADMIN");

        // assert
        Assertions.assertEquals("ROLE_ADMIN", user.getRole());
    }

    @Test
    @Transactional
    void itShouldGetAuthorities() {
        // arrange

        // act

        // assert
        Assertions.assertEquals(Authority.USER_AUTHORITIES, user.getAuthorities());
    }

    @Test
    @Transactional
    void itShouldSetAuthorities() {
        // arrange

        // act
        user.setAuthorities(Authority.ADMIN_AUTHORITIES);

        // assert
        Assertions.assertEquals(Authority.ADMIN_AUTHORITIES, user.getAuthorities());
    }

    @Test
    @Transactional
    void itShouldGetIsActive() {
        // arrange

        // act

        // assert
        Assertions.assertEquals(true, user.isActive());
    }

    @Test
    @Transactional
    void itShouldSetActive() {
        // arrange

        // act
        user.setActive(false);

        // assert
        Assertions.assertEquals(false, user.isActive());
    }

    @Test
    @Transactional
    void itShouldGetIsNotLocked() {
        // arrange

        // act

        // assert
        Assertions.assertEquals(true, user.isNotLocked());
    }

    @Test
    @Transactional
    void itShouldSetNotLocked() {
        // arrange

        // act
        user.setNotLocked(false);

        // assert
        Assertions.assertEquals(false, user.isNotLocked());
    }
}