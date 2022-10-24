package com.nebula.nebula_auth.app.service.impl;

import com.nebula.nebula_auth.app.dao.entity.User;
import com.nebula.nebula_auth.app.dao.repository.UserRepository;
import com.nebula.nebula_auth.app.service.DupCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DupCheckServiceImpl implements DupCheckService {

    private final UserRepository userRepository;

    @Autowired
    public DupCheckServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean checkUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            return true;
        }
        else {
            return false;
        }
    }
}
