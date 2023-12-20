package com.empik.demo.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {
    private String id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private double calculations;

    private UserDTO() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final UserDTO userDTO;

        private Builder() {
            userDTO = new UserDTO();
        }

        public Builder withId(String id) {
            userDTO.setId(id);
            return this;
        }

        public Builder withLogin(String login) {
            userDTO.setLogin(login);
            return this;
        }

        public Builder withName(String name) {
            userDTO.setName(name);
            return this;
        }

        public Builder withType(String type) {
            userDTO.setType(type);
            return this;
        }

        public Builder withAvatarUrl(String avatarUrl) {
            userDTO.setAvatarUrl(avatarUrl);
            return this;
        }

        public Builder withCreatedAt(LocalDateTime createdAt) {
            userDTO.setCreatedAt(createdAt);
            return this;
        }

        public Builder withCalculations(double calculations) {
            userDTO.setCalculations(calculations);
            return this;
        }

        public UserDTO build() {
            return userDTO;
        }
    }
}