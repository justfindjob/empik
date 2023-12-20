package com.empik.demo.api.service;

import com.empik.demo.api.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDTO> getUser(String login);
}