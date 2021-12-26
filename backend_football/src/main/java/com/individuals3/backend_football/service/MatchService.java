package com.individuals3.backend_football.service;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.exception.match.DateForNewMatchHasAlreadyPassedException;
import com.individuals3.backend_football.exception.match.TeamAlreadyHasMatchThatDateException;

public interface MatchService {

    Match createNewMatch(Match match) throws TeamAlreadyHasMatchThatDateException, DateForNewMatchHasAlreadyPassedException;
}
