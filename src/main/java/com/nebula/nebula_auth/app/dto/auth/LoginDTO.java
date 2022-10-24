package com.nebula.nebula_auth.app.dto.auth;

import javax.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank(message = "username 이 공백입니다.")
    private String username;
    @NotBlank(message = "password 가 공백입니다.")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
