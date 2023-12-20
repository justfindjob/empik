package com.empik.demo.api.service;

import com.empik.demo.api.dto.GitHubUserDTO;

public interface GitHubUserService {
    GitHubUserDTO getGitHubUser(String login);
}
