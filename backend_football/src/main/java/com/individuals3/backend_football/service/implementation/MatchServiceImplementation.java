package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.domain.Match;
import com.individuals3.backend_football.exception.match.DateForNewMatchHasAlreadyPassedException;
import com.individuals3.backend_football.exception.match.TeamAlreadyHasMatchThatDateException;
import com.individuals3.backend_football.repository.MatchRepository;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.repository.UserRepository;
import com.individuals3.backend_football.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.individuals3.backend_football.constant.MatchImplementationConstant.*;

@Service
public class MatchServiceImplementation implements MatchService {

    private MatchRepository matchRepository;
    private UserRepository userRepository;
    private TeamRepository teamRepository;

    @Autowired
    public MatchServiceImplementation(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match createNewMatch(Match match) throws TeamAlreadyHasMatchThatDateException, DateForNewMatchHasAlreadyPassedException {
        validateNewMatch(match);
        matchRepository.save(match);
        return match;
    }

    @Override
    public List<Match> getMatches() {
        return matchRepository.findAll();
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
