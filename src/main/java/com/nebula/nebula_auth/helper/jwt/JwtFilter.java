package com.nebula.nebula_auth.helper.jwt;

import com.nebula.nebula_auth.helper.userdetail.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RefreshScope
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final String headerKey;
    private final String prefix;


    @Autowired
    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService, @Value("${jwt.header}") String headerKey, @Value("${jwt.prefix}") String prefix) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.headerKey = headerKey;
        this.prefix = prefix;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            System.out.println("do filter");
            String token = resolveToken(httpServletRequest);
            setSecurityAuthentication(token);
        } catch (RuntimeException e){
//            throw new RuntimeException("Security Filter 오류");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /** Request Header에서 토큰 정보 꺼내오기*/
    private String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(headerKey);
        if(bearerToken.startsWith(prefix + " ")){
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }

    /** SecurityContextHolder 의 Authentication setting */
    private void setSecurityAuthentication(String token) {
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        getUsernamePasswordAuthenticationToken(token)
                );
    }

    /** Jwt token 으로 UsernamePasswordAuthenticationToken 만들기 */
    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token){
        String username = jwtUtil.extractUsername(token);
        if(username != null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(token, userDetails)){
                System.out.println(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            } else {
                throw new RuntimeException("token is not valid");
            }
        } else {
            throw new RuntimeException("token's username is null");
        }
    }
}
