package com.nebula.nebula_auth.helper.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    @Value("${jwt.header}")
    private static String headerKey;
    @Value("${jwt.prefix}")
    private static String prefix;


    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = resolveToken(httpServletRequest);
            setSecurityAuthentication(token);
        } catch (RuntimeException e){

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /** Request Header에서 토큰 정보 꺼내오기*/
    private String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(headerKey);
        if(bearerToken.startsWith(prefix + " ")){
            return bearerToken.substring(7);
        } else {
            throw new RuntimeException("Bearer Token Error");
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
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            } else {
                throw new RuntimeException("token is not valid");
            }
        } else {
            throw new RuntimeException("token's username is null");
        }
    }
}
