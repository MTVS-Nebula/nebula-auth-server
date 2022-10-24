package com.nebula.nebula_auth.app.service.impl;

import com.nebula.nebula_auth.app.dao.entity.Role;
import com.nebula.nebula_auth.app.dao.entity.User;
import com.nebula.nebula_auth.app.dao.entity.UserRole;
import com.nebula.nebula_auth.app.dao.entity.embeddable.UserRoleId;
import com.nebula.nebula_auth.app.dao.repository.RoleRepository;
import com.nebula.nebula_auth.app.dao.repository.UserRepository;
import com.nebula.nebula_auth.app.dao.repository.UserRoleRepository;
import com.nebula.nebula_auth.app.dto.SignUpDTO;
import com.nebula.nebula_auth.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public boolean signUp(SignUpDTO signUpDTO) {
        try {
            User user = createUser(signUpDTO);
            createUserRole(user, "MEMBER");
            return true;
        } catch (RuntimeException e){
            return false;
        }
    }

    private User createUser(SignUpDTO signUpDTO){
        User user = new User(
                0, signUpDTO.getUsername(), passwordEncoder.encode(signUpDTO.getPassword()),
                signUpDTO.getEmail(), new Date((new java.util.Date().getTime())),
                "N", null, null
        );
        userRepository.save(user);
        return user;
    }

    private void createUserRole(User user, String roleName){
        Role role = roleRepository.findByRoleName(roleName);
        UserRole userRole = new UserRole(new UserRoleId(user, role));
        userRoleRepository.save(userRole);
    }
}
