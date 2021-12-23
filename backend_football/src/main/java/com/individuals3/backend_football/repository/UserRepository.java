package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserById(Long id);

    User findUserByUserId(String id);

    List<User> findUsersByIsActive(Boolean isActive);
}
