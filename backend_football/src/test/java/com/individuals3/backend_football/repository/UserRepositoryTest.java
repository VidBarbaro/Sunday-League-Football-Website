package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.constant.Authority;
import com.individuals3.backend_football.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserRepositoryTest {

    public static final Long USER_ID = 5L;
    public static final String USER_USERNAME = "vidbarbaro";
    public static final String USER_EMAIL= "vid.barbaro@gmail.com";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    void itShouldFindUserByUsername() {
        User user = userRepository.findUserByUsername(USER_USERNAME);
        Assert.assertEquals(USER_ID, user.getId());
        Assert.assertEquals("8662835203", user.getUserId());
        Assert.assertEquals("Vid", user.getFirstName());
        Assert.assertEquals("Barbaro", user.getLastName());
        Assert.assertEquals("vid.barbaro@gmail.com", user.getEmail());
        Assert.assertEquals("ROLE_USER", user.getRole());
        Assert.assertEquals(Authority.PLAYER_AUTHORITIES[0], user.getAuthorities()[0]);
        Assert.assertEquals(true, user.isActive());
        Assert.assertEquals(true, user.isNotLocked());
    }

    @Test
    @Transactional
    void itShouldFindUserByEmail() {
        User user = userRepository.findUserByEmail(USER_EMAIL);
        Assert.assertEquals(USER_ID, user.getId());
        Assert.assertEquals("8662835203", user.getUserId());
        Assert.assertEquals("Vid", user.getFirstName());
        Assert.assertEquals("Barbaro", user.getLastName());
        Assert.assertEquals("vidbarbaro", user.getUsername());
        Assert.assertEquals("ROLE_USER", user.getRole());
        Assert.assertEquals(Authority.PLAYER_AUTHORITIES[0], user.getAuthorities()[0]);
        Assert.assertEquals(true, user.isActive());
        Assert.assertEquals(true, user.isNotLocked());
    }

    @Test
    @Transactional
    void itShouldFindUserById() {
        User user = userRepository.findUserById(USER_ID);
        Assert.assertEquals("8662835203", user.getUserId());
        Assert.assertEquals("Vid", user.getFirstName());
        Assert.assertEquals("Barbaro", user.getLastName());
        Assert.assertEquals("vidbarbaro", user.getUsername());
        Assert.assertEquals("vid.barbaro@gmail.com", user.getEmail());
        Assert.assertEquals("ROLE_USER", user.getRole());
        Assert.assertEquals(Authority.PLAYER_AUTHORITIES[0], user.getAuthorities()[0]);
        Assert.assertEquals(true, user.isActive());
        Assert.assertEquals(true, user.isNotLocked());
    }

    @Test
    @Transactional
    void itShouldFindUserByUserId() {
        User user = userRepository.findUserByUserId("8662835203");
        Assert.assertEquals(USER_ID, user.getId());
        Assert.assertEquals("Vid", user.getFirstName());
        Assert.assertEquals("Barbaro", user.getLastName());
        Assert.assertEquals("vidbarbaro", user.getUsername());
        Assert.assertEquals("vid.barbaro@gmail.com", user.getEmail());
        Assert.assertEquals("ROLE_USER", user.getRole());
        Assert.assertEquals(Authority.PLAYER_AUTHORITIES[0], user.getAuthorities()[0]);
        Assert.assertEquals(true, user.isActive());
        Assert.assertEquals(true, user.isNotLocked());
    }
}