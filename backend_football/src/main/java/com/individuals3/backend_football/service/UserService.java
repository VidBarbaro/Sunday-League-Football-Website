package com.individuals3.backend_football.service;

import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.domain.EmailExistsException;
import com.individuals3.backend_football.exception.domain.UsernameExistsException;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UsernameExistsException, EmailExistsException, MessagingException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
