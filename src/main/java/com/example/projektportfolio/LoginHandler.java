package com.example.projektportfolio;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        boolean hasAdmin = false;
        for (GrantedAuthority authority : authentication.getAuthorities()){
            if(authority.getAuthority().equals("ROLE_ADMIN")){
                hasAdmin = true;
            }
        }

        if (hasAdmin){
            httpServletResponse.sendRedirect("/admin");
        } else {
            httpServletResponse.sendRedirect("/");
        }
    }
}
