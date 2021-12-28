package com.individuals3.backend_football.service;

import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.domain.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistsException, EmailExistsException, MessagingException;

    List<User> getUsers();

    List<User> getAvailablePlayers();

    List<User> getAvailableReferees();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, NotAnImageFileException;

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, NotAnImageFileException;

    void deleteUser(String username) throws IOException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException, NotAnImageFileException;
}
