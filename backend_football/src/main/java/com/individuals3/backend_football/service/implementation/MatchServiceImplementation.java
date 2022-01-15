package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.domain.EmailExistsException;
import com.individuals3.backend_football.exception.domain.NotAnImageFileException;
import com.individuals3.backend_football.exception.domain.UserNotFoundException;
import com.individuals3.backend_football.exception.domain.UsernameExistsException;
import com.individuals3.backend_football.exception.match.DateForNewMatchHasAlreadyPassedException;
import com.individuals3.backend_football.exception.match.TeamAlreadyHasMatchThatDateException;
import com.individuals3.backend_football.repository.MatchRepository;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.repository.UserRepository;
import com.individuals3.backend_football.service.MatchService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.individuals3.backend_football.constant.FileConstant.USER_FOLDER;
import static com.individuals3.backend_football.constant.MatchImplementationConstant.*;

@Service
public class MatchServiceImplementation implements MatchService {

    private MatchRepository matchRepository;
    private UserRepository userRepository;
    private TeamRepository teamRepository;

    @Autowired
    public MatchServiceImplementation(MatchRepository matchRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Match createNewMatch(Match match) throws TeamAlreadyHasMatchThatDateException, DateForNewMatchHasAlreadyPassedException {
        validateNewMatch(match);
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match updateMatch(Long matchId, Team homeTeam, Team awayTeam, User referee, LocalDateTime matchDateTime, String location, int homeTeamGoals, int awayTeamGoals, Boolean isFinished) {
        Match match = this.matchRepository.findMatchById(matchId);
        match.setHomeTeamId(homeTeam);
        match.setAwayTeamId(awayTeam);
        match.setRefereeId(referee);
        match.setMatchDateTime(matchDateTime);
        match.setLocation(location);
        match.setHomeTeamGoals(homeTeamGoals);
        match.setAwayTeamGoals(awayTeamGoals);
        match.setIsFinished(isFinished);
        matchRepository.save(match);
        return match;
    }

    @Override
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> getMatchesForTeam(Long teamId) {
        Team team = teamRepository.findTeamById(teamId);
        List<Match> matches = matchRepository.findMatchByHomeTeamId(team);
        matches.addAll(matchRepository.findMatchByAwayTeamId(team));
        return matches;
    }

    @Override
    public List<Match> getMatchesForReferee(Long refereeId) {
        User referee = userRepository.findUserById(refereeId);
        List<Match> matches = matchRepository.findMatchByRefereeId(referee);
        return matches;
    }

    @Override
    public void deleteMatch(Long matchId) throws IOException {
        Match match = matchRepository.findMatchById(matchId);
        matchRepository.deleteById(match.getId());
    }

    private Match validateNewMatch(Match match) throws TeamAlreadyHasMatchThatDateException, DateForNewMatchHasAlreadyPassedException {
        ArrayList<Match> allMatches = new ArrayList<>();
        for(int i=0; i<allMatches.size(); i++) {
            if(allMatches.get(i).getHomeTeamId() == match.getHomeTeamId() || allMatches.get(i).getAwayTeamId() == match.getAwayTeamId() && allMatches.get(i).getMatchDateTime() == match.getMatchDateTime()) {
                throw new TeamAlreadyHasMatchThatDateException(TEAM_ALREADY_HAS_MATCH_THAT_DATE);
            }
        }
        LocalDateTime now = LocalDateTime.now();
        if(match.getMatchDateTime().isBefore(now)) {
            throw new DateForNewMatchHasAlreadyPassedException(DATE_FOR_NEW_MATCH_ALREADY_PASSED);
        }
        return match;
    }
}
