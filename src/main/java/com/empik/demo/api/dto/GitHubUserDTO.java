package com.empik.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUserDTO {
    private String id;
    private String login;
    private String name;
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private int followers;
    @JsonProperty("public_repos")
    private int publicRepos;


    private GitHubUserDTO() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GitHubUserDTO gitHubUserDTO;

        private Builder() {
            gitHubUserDTO = new GitHubUserDTO();
        }

        public Builder withId(String id) {
            gitHubUserDTO.setId(id);
            return this;
        }

        public Builder withLogin(String login) {
            gitHubUserDTO.setLogin(login);
            return this;
        }

        public Builder withName(String name) {
            gitHubUserDTO.setName(name);
            return this;
        }

        public Builder withType(String type) {
            gitHubUserDTO.setType(type);
            return this;
        }

        public Builder withAvatarUrl(String avatarUrl) {
            gitHubUserDTO.setAvatarUrl(avatarUrl);
            return this;
        }

        public Builder withFollowers(int followers) {
            gitHubUserDTO.setFollowers(followers);
            return this;
        }

        public Builder withPublicRepos(int publicRepos) {
            gitHubUserDTO.setPublicRepos(publicRepos);
            return this;
        }

        public GitHubUserDTO build() {
            return gitHubUserDTO;
        }
    }
}