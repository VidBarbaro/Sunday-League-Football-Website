package com.individuals3.backend_football.repository;

import com.individuals3.backend_football.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Match findMatchById(Long id);
}
