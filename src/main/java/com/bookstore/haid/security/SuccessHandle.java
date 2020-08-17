package com.bookstore.haid.security;

import com.bookstore.haid.mapper.LoginMapper;
import com.bookstore.haid.model.User;
import com.bookstore.haid.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class SuccessHandle implements AuthenticationSuccessHandler {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private LoginService loginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String username=authentication.getName();
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("username",username);
        httpServletResponse.sendRedirect("/");
    }

}
