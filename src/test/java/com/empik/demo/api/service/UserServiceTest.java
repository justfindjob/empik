package com.empik.demo.api.service;

import com.empik.demo.api.dto.GitHubUserDTO;
import com.empik.demo.api.dto.UserDTO;
import com.empik.demo.api.repository.UserRepository;
import com.empik.demo.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private GitHubUserService gitHubUserService;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void getUserSuccess() {
        GitHubUserDTO gitHubUserDTO = GitHubUserDTO.builder()
                .withId("1")
                .withLogin("testuser")
                .withName("Test User")
                .withType("user")
                .withAvatarUrl("https://example.com/avatar.png")
                .withFollowers(10)
                .withPublicRepos(5)
                .build();

        Mockito.when(userRepository.findByLogin("testuser")).thenReturn(null);
        Mockito.when(gitHubUserService.getGitHubUser("testuser")).thenReturn(gitHubUserDTO);

        ResponseEntity<UserDTO> response = userService.getUser("testuser");
        UserDTO userDTO = response.getBody();

        double expectedCalculations = 6.0 / (gitHubUserDTO.getFollowers() * (2 + gitHubUserDTO.getPublicRepos()));
        assert userDTO != null;
        Assertions.assertEquals(expectedCalculations, userDTO.getCalculations());

        Assertions.assertEquals("1", userDTO.getId());
        Assertions.assertEquals("testuser", userDTO.getLogin());
        Assertions.assertEquals("Test User", userDTO.getName());
        Assertions.assertEquals("user", userDTO.getType());
        Assertions.assertEquals("https://example.com/avatar.png", userDTO.getAvatarUrl());
    }

    @Test
    public void getUserNotFound() {
        Mockito.when(gitHubUserService.getGitHubUser("nonexistentuser")).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));;
        Assertions.assertThrows(HttpClientErrorException.class, () -> userService.getUser("nonexistentuser"));
    }

}