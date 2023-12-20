package com.empik.demo.api.controller;

import com.empik.demo.api.dto.GitHubUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetUserFromGitHub() {
        String login = "octocat";
        String url = "http://localhost:" + port + "/users/" + login;

        ResponseEntity<GitHubUserDTO> response = restTemplate.getForEntity(url, GitHubUserDTO.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(login, response.getBody().getLogin());
    }

    @Test
    public void testGetUser_UserNotFound() {
        String nonExistentLogin = "nonexistentuser";
        String url = "http://localhost:" + port + "/users/" + nonExistentLogin;

        ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}