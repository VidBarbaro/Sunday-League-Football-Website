package com.individuals3.backend_football.service;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.domain.EmailExistsException;
import com.individuals3.backend_football.exception.domain.NotAnImageFileException;
import com.individuals3.backend_football.exception.domain.UserNotFoundException;
import com.individuals3.backend_football.exception.domain.UsernameExistsException;
import com.individuals3.backend_football.exception.match.DateForNewMatchHasAlreadyPassedException;
import com.individuals3.backend_football.exception.match.TeamAlreadyHasMatchThatDateException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface MatchService {

    Match createNewMatch(Match match) throws TeamAlreadyHasMatchThatDateException, DateForNewMatchHasAlreadyPassedException;

    Match updateMatch(Long matchId, Team homeTeam, Team awayTeam, User referee, LocalDateTime matchDateTime, String location, int homeTeamGoals, int awayTeamGoals, Boolean isFinished);

    List<Match> getMatches();

    List<Match> getMatchesForTeam(Long teamId);

    void deleteMatch(Long matchId) throws IOException;

}
