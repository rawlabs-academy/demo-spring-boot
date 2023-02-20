package com.rawlabs.trainingspringboot.config;

import com.rawlabs.trainingspringboot.component.TokenProvider;
import com.rawlabs.trainingspringboot.domain.dao.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        final String BEARER = "Bearer ";
        if (authorization != null && authorization.startsWith(BEARER)) {
            token = authorization.replace(BEARER, "");

            try {
                username = tokenProvider.getUsername(token);
            } catch (IllegalArgumentException e) {
                log.error("An error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                log.error("Token is expired", e);
            } catch (SignatureException e) {
                log.error("Authentication Failed. Username or Password not valid.");
            }
        }

        if (username != null && authentication == null) {
            User user = (User) userDetailsService.loadUserByUsername(username);

            if (tokenProvider.isTokenValid(token, user)) {
                Authentication authenticationToken = tokenProvider.getAuthenticationToken(user);

                log.debug("Authenticated user: {}, setting security context", username);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
