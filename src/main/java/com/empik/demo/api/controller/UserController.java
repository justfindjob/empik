package com.empik.demo.api.controller;

import com.empik.demo.api.dto.UserDTO;
import com.empik.demo.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
        return userService.getUser(login);
    }
}