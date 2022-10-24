package com.nebula.nebula_auth.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpDTO {
    @NotBlank(message = "username 이 공백입니다.")
    private String username;
    @NotBlank(message = "password 가 공백입니다")
    private String password;
    @Email(message = "email 형식이 맞지 않습니다.")
    private String email;

    public SignUpDTO() {
    }

    public SignUpDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
