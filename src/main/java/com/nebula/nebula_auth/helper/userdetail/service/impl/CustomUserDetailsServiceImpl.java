package com.nebula.nebula_auth.helper.userdetail.service.impl;

import com.nebula.nebula_auth.app.dao.entity.User;
import com.nebula.nebula_auth.app.dao.entity.UserRole;
import com.nebula.nebula_auth.app.dao.repository.UserRepository;
import com.nebula.nebula_auth.app.dao.repository.UserRoleRepository;
import com.nebula.nebula_auth.helper.userdetail.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    /** username 으로 UserDetails 객체 반환*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(username)
        );
    }

    /** username 으로 해당 유저의 권한 조회하여 GrantedAuthority 객체 배열 반환 */
    private List<GrantedAuthority> getAuthorities(String username){
        List<UserRole> userRoleList = userRoleRepository.findByIdUserUsername(username);

        if(userRoleList.size() == 0 || userRoleList == null){
            throw new RuntimeException("권한 정보가 없는 회원입니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        userRoleList.forEach(
                userRole ->
                authorities.add(new SimpleGrantedAuthority(userRole.getId().getRole().getRoleName()))
        );

        return authorities;
    }
}
