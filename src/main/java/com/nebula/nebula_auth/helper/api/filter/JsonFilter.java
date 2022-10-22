package com.nebula.nebula_auth.helper.api.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

@WebFilter(urlPatterns = "/*")
public class JsonFilter implements Filter {
    private HttpHeaders httpHeaders;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(
                new MediaType("application", "json", Charset.forName("UTF-8"))
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        filterChain.doFilter(httpServletRequest, httpServletResponse);

        httpServletResponse.setContentType("application/json;charset=UTF-8");
    }


}
