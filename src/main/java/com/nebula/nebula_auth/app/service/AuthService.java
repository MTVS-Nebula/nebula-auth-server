package com.nebula.nebula_auth.app.service;

import com.nebula.nebula_auth.app.dto.LoginDTO;
import com.nebula.nebula_auth.app.dto.SignUpDTO;

public interface AuthService {
    public boolean signUp(SignUpDTO signUpDTO);
    public String login(LoginDTO loginDTO);
}
