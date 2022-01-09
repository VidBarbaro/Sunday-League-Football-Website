package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.enumeration.Role;
import com.individuals3.backend_football.exception.domain.*;
import com.individuals3.backend_football.repository.UserRepository;
import com.individuals3.backend_football.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceImplementationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    public void registerTestSuccess() throws MessagingException, UsernameExistsException, EmailExistsException {
        try
        {
            userService.register("Test FirstName", "Test LastName", "Test Username", "test@gmail.com");
            Assert.assertEquals(userRepository.findUserByUsername("Test Username").getUsername(), "Test Username");
        }
        catch (DataIntegrityViolationException | UserNotFoundException e) {
            System.out.println("User ID is taken");
        }
    }

    @Test
    @Transactional
    public void registerTestFailUsernameExists() throws MessagingException, UsernameExistsException, EmailExistsException {
        try
        {
            userService.register("Test FirstName", "Test LastName", "vidbarbaro", "test@gmail.com");
        }
        catch(UsernameExistsException | UserNotFoundException exception)
        {
            Assert.assertEquals("Username already exists", exception.getMessage());
        }
    }

    @Test
    @Transactional
    public void registerTestFailEmailExists() throws MessagingException, UsernameExistsException, EmailExistsException {
        try
        {
            userService.register("Test FirstName", "Test LastName", "Test Username", "vid.barbaro@gmail.com");
        }
        catch(EmailExistsException | UserNotFoundException exception)
        {
            Assert.assertEquals("Email already exists", exception.getMessage());
        }
    }

    @Test
    @Transactional
    void addNewUser() throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, NotAnImageFileException {
        User user = userService.addNewUser("TestFN", "TestLN", "testuser", "test@gmail.com", "ROLE_USER", true, true,null);
        User aUser = userRepository.findUserByUsername("testuser");
        Assert.assertEquals(user, aUser);
    }

    @Test
    @Transactional
    void updateUser() throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, NotAnImageFileException {
        userService.addNewUser("TestFN", "TestLN", "testuser", "test@gmail.com", "ROLE_USER", true, true,null);
        User updatedUser = userService.updateUser("testuser", "UpdatedFN", "UpdatedLN", "updateduser", "updated@gmail.com", "ROLE_USER", true, true,null);
        User aUser = userRepository.findUserByUsername("updateduser");
        Assert.assertEquals(updatedUser, aUser);
    }

    @Test
    @Transactional
    void getUsers() {
        List<User> allUsers = userService.getUsers();
        Assert.assertEquals(userRepository.findAll(), allUsers);
    }

    @Test
    @Transactional
    void getAvailablePlayers() {
        List<User> allUsers = userService.getAvailablePlayers();
        Assert.assertEquals(userRepository.findUsersByIsActive(false), allUsers);
    }

    @Test
    @Transactional
    void getAvailableReferees() {
        List<User> allUsers = userService.getAvailableReferees();
        Assert.assertEquals(userRepository.findUsersByRole(Role.ROLE_REFEREE.toString()), allUsers);
    }

    @Test
    @Transactional
    void findUserByUsername() {
        User user = userService.findUserByUsername("vidbarbaro");
        Long id = 5L;
        Assert.assertEquals(id, user.getId());
        Assert.assertEquals("vidbarbaro", user.getUsername());
        Assert.assertEquals("vid.barbaro@gmail.com", user.getEmail());
    }

    @Test
    @Transactional
    void findUserByEmail() {
        User user = userService.findUserByEmail("vid.barbaro@gmail.com");
        Long id = 5L;
        Assert.assertEquals(id, user.getId());
        Assert.assertEquals("vidbarbaro", user.getUsername());
        Assert.assertEquals("vid.barbaro@gmail.com", user.getEmail());
    }

    @Test
    @Transactional
    void deleteUser() throws IOException {
        userService.deleteUser("vidbarbaro");
        User user = userRepository.findUserByUsername("vidbarbaro");
        Assert.assertEquals(user, null);
    }
}