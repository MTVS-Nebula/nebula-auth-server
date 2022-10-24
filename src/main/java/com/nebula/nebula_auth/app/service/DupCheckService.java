package com.nebula.nebula_auth.app.service;

import org.springframework.stereotype.Service;

public interface DupCheckService {
    boolean checkUsername(String username);
    boolean checkEmail(String email);
}
