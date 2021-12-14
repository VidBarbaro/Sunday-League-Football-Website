package com.individuals3.backend_football.service;

import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.exception.domain.NotAnImageFileException;
import com.individuals3.backend_football.exception.team.TeamNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TeamService {

    Team findTeamByName(String teamName) throws TeamNotFoundException;

    Team findTeamByTeamManagerId(Long teamManagerId) throws TeamNotFoundException;

    Team addNewTeam(String teamName, MultipartFile profileImage, Long teamManagerId) throws IOException, NotAnImageFileException;

    void deleteTeam(String teamName) throws IOException;

}
