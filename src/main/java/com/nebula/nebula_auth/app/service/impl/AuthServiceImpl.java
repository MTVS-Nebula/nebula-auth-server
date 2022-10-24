package com.nebula.nebula_auth.app.service.impl;

import com.nebula.nebula_auth.app.dao.entity.LoginLog;
import com.nebula.nebula_auth.app.dao.entity.Role;
import com.nebula.nebula_auth.app.dao.entity.User;
import com.nebula.nebula_auth.app.dao.entity.UserRole;
import com.nebula.nebula_auth.app.dao.entity.embeddable.UserRoleId;
import com.nebula.nebula_auth.app.dao.repository.LoginLogRepository;
import com.nebula.nebula_auth.app.dao.repository.RoleRepository;
import com.nebula.nebula_auth.app.dao.repository.UserRepository;
import com.nebula.nebula_auth.app.dao.repository.UserRoleRepository;
import com.nebula.nebula_auth.app.dto.auth.LoginDTO;
import com.nebula.nebula_auth.app.dto.auth.SignUpDTO;
import com.nebula.nebula_auth.app.service.AuthService;
import com.nebula.nebula_auth.helper.jwt.JwtUtil;
import com.nebula.nebula_auth.helper.jwt.dto.JwtDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final LoginLogRepository loginLogRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, LoginLogRepository loginLogRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.loginLogRepository = loginLogRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    @Override
    public String login(LoginDTO loginDTO) {
        String token = null;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
            User user = userRepository.findByUsername(loginDTO.getUsername());
            loginLogRepository.save(new LoginLog(0,user,new Date((new java.util.Date()).getTime())));
            return generateToken(loginDTO);
        } catch (Exception e){
            throw new RuntimeException("login 에러");
        }
    }

    private User createUser(SignUpDTO signUpDTO){
        User user = new User(
                0, signUpDTO.getUsername(), passwordEncoder.encode(signUpDTO.getPassword()),
                signUpDTO.getEmail(), new Date((new java.util.Date().getTime())),
                "N", null
        );
        userRepository.save(user);
        return user;
    }

    private void createUserRole(User user, String roleName){
        Role role = roleRepository.findByRoleName(roleName);
        UserRole userRole = new UserRole(new UserRoleId(user, role));
        userRoleRepository.save(userRole);
    }

    private String generateToken(LoginDTO loginDTO){
        try {
            User user = userRepository.findByUsername(loginDTO.getUsername());
            List<UserRole> userRoleList = userRoleRepository.findByIdUserUsername(loginDTO.getUsername());
            List<String> roleList = userRoleList.stream()
                    .map(uR ->uR.getId().getRole().getRoleName())
                    .collect(Collectors.toList());
            JwtDTO jwtDTO = new JwtDTO(user.getId(), user.getUsername(), roleList);
            return jwtUtil.generateToken(jwtDTO);
        } catch (RuntimeException e){
            throw new RuntimeException("token 발행 에러");
        }
    }
}
