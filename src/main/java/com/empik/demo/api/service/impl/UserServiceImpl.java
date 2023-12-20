package com.empik.demo.api.service.impl;

import com.empik.demo.api.dto.GitHubUserDTO;
import com.empik.demo.api.dto.UserDTO;
import com.empik.demo.api.entity.UserEntity;
import com.empik.demo.api.repository.UserRepository;
import com.empik.demo.api.service.GitHubUserService;
import com.empik.demo.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GitHubUserService gitHubUserService;

    public UserServiceImpl(UserRepository userRepository, GitHubUserService gitHubUserService) {
        this.userRepository = userRepository;
        this.gitHubUserService = gitHubUserService;
    }

    @Override
    public ResponseEntity<UserDTO> getUser(String login) {
        GitHubUserDTO gitHubUserDTO = gitHubUserService.getGitHubUser(login);
        addOrUpdateUserEntity(login);
        return getResponse(gitHubUserDTO);
    }

    /**
     * Check if exists login in database and increment request count,
     * otherwise create new record
     *
     * @param login
     */
    private void addOrUpdateUserEntity(String login) {
        UserEntity userEntity = userRepository.findByLogin(login);
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setLogin(login);
            userEntity.setRequestCount(1);
        } else {
            userEntity.setRequestCount(userEntity.getRequestCount() + 1);
        }
        userRepository.save(userEntity);
    }

    /**
     * Get response 200 OK with UserDTO body
     *
     * @param gitHubUserDTO
     * @return
     */
    private ResponseEntity<UserDTO> getResponse(GitHubUserDTO gitHubUserDTO) {
        return ResponseEntity.ok(UserDTO.builder()
                .withId(gitHubUserDTO.getId())
                .withLogin(gitHubUserDTO.getLogin())
                .withName(gitHubUserDTO.getName())
                .withType(gitHubUserDTO.getType())
                .withAvatarUrl(gitHubUserDTO.getAvatarUrl())
                .withCreatedAt(LocalDateTime.now())
                .withCalculations(makeCalculation(gitHubUserDTO))
                .build());
    }

    /**
     * Make calculation for GitHubUser
     *
     * @param gitHubUserDTO
     * @return
     */
    private double makeCalculation(GitHubUserDTO gitHubUserDTO) {
        return 6.0 / (gitHubUserDTO.getFollowers() * (2 + gitHubUserDTO.getPublicRepos()));
    }
}