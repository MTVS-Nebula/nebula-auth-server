package com.nebula.nebula_auth.app.service;

import com.nebula.nebula_auth.app.dto.auth.LoginDTO;
import com.nebula.nebula_auth.app.dto.auth.SignUpDTO;

public interface AuthService {
    public boolean signUp(SignUpDTO signUpDTO);
    public String login(LoginDTO loginDTO);
}
