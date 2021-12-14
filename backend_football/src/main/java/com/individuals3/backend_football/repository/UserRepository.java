package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserById(Long id);
}
