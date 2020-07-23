package com.bookstore.haid.service;

import com.bookstore.haid.mapper.LoginMapper;
import com.bookstore.haid.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public User checkUser(User user) {
        User checkUser = loginMapper.checkUser(user.getUsername(), user.getPassword());
            return checkUser;
    }
}
