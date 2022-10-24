package com.nebula.nebula_auth.app.dto.duplication;


import javax.validation.constraints.NotBlank;

public class UsernameDTO {
    @NotBlank
    private String username;

    public UsernameDTO() {
    }

    public UsernameDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UsernameDTO{" +
                "username='" + username + '\'' +
                '}';
    }
}
