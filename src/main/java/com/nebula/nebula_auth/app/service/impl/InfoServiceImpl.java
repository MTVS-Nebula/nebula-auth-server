package com.nebula.nebula_auth.app.service.impl;

import com.nebula.nebula_auth.app.dao.entity.User;
import com.nebula.nebula_auth.app.dao.repository.UserRepository;
import com.nebula.nebula_auth.app.dto.info.InfoDTO;
import com.nebula.nebula_auth.app.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    private final UserRepository userRepository;

    @Autowired
    public InfoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public InfoDTO getUserInfo(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new RuntimeException();
        }
        InfoDTO infoDTO = new InfoDTO(
                user.getId(),user.getUsername(),user.getEmail(),
                user.getJoinDate(),user.getIsDeleted(), user.getDeletedDate());

        return infoDTO;
    }
}
