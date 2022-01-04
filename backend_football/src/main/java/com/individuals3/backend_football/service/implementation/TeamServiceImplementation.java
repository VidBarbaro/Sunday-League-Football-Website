package com.individuals3.backend_football.service.implementation;

import com.individuals3.backend_football.constant.FileConstant;
import com.individuals3.backend_football.domain.Team;
import com.individuals3.backend_football.domain.User;
import com.individuals3.backend_football.domain.UserPrincipal;
import com.individuals3.backend_football.enumeration.Role;
import com.individuals3.backend_football.exception.domain.EmailExistsException;
import com.individuals3.backend_football.exception.domain.NotAnImageFileException;
import com.individuals3.backend_football.exception.domain.UserNotFoundException;
import com.individuals3.backend_football.exception.domain.UsernameExistsException;
import com.individuals3.backend_football.exception.team.ManagerAlreadyHasTeamException;
import com.individuals3.backend_football.exception.team.TeamNameExistsException;
import com.individuals3.backend_football.exception.team.TeamNotFoundException;
import com.individuals3.backend_football.repository.TeamRepository;
import com.individuals3.backend_football.service.EmailService;
import com.individuals3.backend_football.service.LoginAttemptService;
import com.individuals3.backend_football.service.TeamService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.individuals3.backend_football.constant.FileConstant.*;
import static com.individuals3.backend_football.constant.FileConstant.FILE_SAVED_IN_FILE_SYSTEM;
import static com.individuals3.backend_football.constant.TeamImplementationConstant.*;
import static com.individuals3.backend_football.constant.UserImplementationConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.http.MediaType.*;

@Service
@Transactional
@Qualifier("TeamDetailsService")
public class TeamServiceImplementation implements TeamService {

    private static final String NO_TEAM_FOUND_BY_TEAM_MANAGER_ID = "No team found by team manager id ";
    private TeamRepository teamRepository;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private BCryptPasswordEncoder passwordEncoder;
    private LoginAttemptService loginAttemptService;
    private EmailService emailService;


    @Autowired
    public TeamServiceImplementation(TeamRepository teamRepository, BCryptPasswordEncoder passwordEncoder, LoginAttemptService loginAttemptService, EmailService emailService) {
        this.teamRepository = teamRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
        this.emailService = emailService;
    }

    @Override
    public Team findTeamByName(String teamName) throws TeamNotFoundException {
        return teamRepository.findTeamByName(teamName);
    }

    @Override
    public Team findTeamByTeamManagerId(String teamManagerId) throws TeamNotFoundException {
        return teamRepository.findTeamByTeamManagerId(teamManagerId);
    }

    @Override
    public Team addNewTeam(String teamName, MultipartFile profileImage, String teamManagerId) throws IOException, NotAnImageFileException, TeamNameExistsException, ManagerAlreadyHasTeamException, TeamNotFoundException {
        validateNewTeam(teamName, teamManagerId);
        Team team = new Team();
        team.setTeamId(generateTeamId());
        team.setName(teamName);
//        team.setClubLogoUrl(getTe);
        team.setClubLogoUrl(getTemporaryLogoImageUrl(teamName));
        teamRepository.save(team);
        saveProfileImage(team, profileImage);
        team.setTeamManagerId(teamManagerId);
        return team;
    }

    @Override
    public void deleteTeam(String teamName) throws IOException {
        Team team = teamRepository.findTeamByName(teamName);
        Path teamFolder = Paths.get(TEAM_FOLDER + team.getName()).toAbsolutePath().normalize();
        FileUtils.deleteDirectory(new File(teamFolder.toString()));
        teamRepository.deleteById(team.getId());
    }

    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    private String generateTeamId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private void saveProfileImage(Team team, MultipartFile profileImage) throws IOException, NotAnImageFileException {
        if (profileImage != null) {
            if(!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(profileImage.getContentType())) {
                throw new NotAnImageFileException(profileImage.getOriginalFilename() + NOT_AN_IMAGE_FILE);
            }
            Path teamFolder = Paths.get(TEAM_FOLDER + team.getName()).toAbsolutePath().normalize();
            if(!Files.exists(teamFolder)) {
                Files.createDirectories(teamFolder);
                LOGGER.info(DIRECTORY_CREATED + teamFolder);
            }
            Files.deleteIfExists(Paths.get(teamFolder + team.getName() + DOT + JPG_EXTENSION));
            Files.copy(profileImage.getInputStream(), teamFolder.resolve(team.getName() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            team.setClubLogoUrl(setProfileImageUrl(team.getName()));
            teamRepository.save(team);
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
        }
    }

    private String setProfileImageUrl(String teamName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(TEAM_IMAGE_PATH + teamName + FORWARD_SLASH
                + teamName + DOT + JPG_EXTENSION).toUriString();
    }

    private String getTemporaryLogoImageUrl(String teamName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.DEFAULT_TEAM_IMAGE_PATH + teamName).toUriString();
    }

    private Team validateNewTeam(String teamName, String managerId) throws TeamNotFoundException, TeamNameExistsException, ManagerAlreadyHasTeamException {
        Team teamByTeamName = findTeamByName(teamName);
        Team teamByManagerId = findTeamByTeamManagerId(managerId);
        if(StringUtils.isNotBlank(teamName)) {
            if(teamByTeamName != null) {
                throw new TeamNameExistsException(TEAM_NAME_ALREADY_EXISTS);
            }
            if(teamByManagerId != null) {
                throw new ManagerAlreadyHasTeamException(MANAGER_ALREADY_HAS_TEAM);
            }
            return teamByTeamName;
        }
        return null;
    }
}
