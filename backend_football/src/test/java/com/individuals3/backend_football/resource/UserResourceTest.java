package com.individuals3.backend_football.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.individuals3.backend_football.constant.Authority;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.repository.UserRepository;
import com.individuals3.backend_football.service.UserService;
import com.individuals3.backend_football.utility.JWTTokenProvider;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {UserResource.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(UserResource.class)
class UserResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    JWTTokenProvider jwtTokenProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    @WithMockUser
    void getUserByUsername() throws Exception {
        //arrange
        User user = new User();
        user.setUsername("test");
        when(userService.findUserByUsername(user.getUsername())).thenReturn(user);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/find/{username}", user.getUsername())
                .contentType(MediaType.APPLICATION_JSON))
        //assert
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsers() throws Exception {
        //arrange
        User user1 = new User(100L, "123", "FN", "LN", "USERNAME", "PWD", "T@MAIL.COM", "IMAGE", new Date(), new Date(), new Date(), "ROLE_USER", Authority.USER_AUTHORITIES,true, true);
        User user2 = new User(101L, "122", "FN1", "LN1", "USERNAME1", "PWD1", "T1@MAIL.COM", "IMAGE1", new Date(), new Date(), new Date(), "ROLE_USER", Authority.USER_AUTHORITIES,true, true);
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        when(userService.getUsers()).thenReturn(users);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                .get("/list")
                .contentType(MediaType.APPLICATION_JSON))
        //assert
                .andExpect(status().isUnauthorized());
    }
}