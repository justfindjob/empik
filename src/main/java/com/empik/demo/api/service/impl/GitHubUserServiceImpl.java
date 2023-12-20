package com.empik.demo.api.service.impl;

import com.empik.demo.api.dto.GitHubUserDTO;
import com.empik.demo.api.exception.UserNotFoundException;
import com.empik.demo.api.service.GitHubUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubUserServiceImpl implements GitHubUserService {
    private static final Logger logger = LoggerFactory.getLogger(GitHubUserServiceImpl.class);

    /**
     * Get user by login from github using RestTemplate
     *
     * @param login
     * @return
     */
    @Override
    public GitHubUserDTO getGitHubUser(String login) {
        String apiUrl = "https://api.github.com/users/" + login;
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(apiUrl, GitHubUserDTO.class);
        } catch (HttpClientErrorException.NotFound ex) {
            logger.error("User not found with login: " + login);
            throw new UserNotFoundException("User not found with login: " + login);
        }
    }
}
