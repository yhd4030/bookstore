package com.bookstore.haid.service;

import com.bookstore.haid.dto.UserDTO;
import com.bookstore.haid.mapper.LoginMapper;

import com.bookstore.haid.mapper.RegisterMapper;
import com.bookstore.haid.model.User;
import com.bookstore.haid.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private LoginMapper loginMapper;

    public boolean registerUser(User user) {
        String passwordEncoder = PasswordEncoderUtil.passwordEncoder(user.getPassword());
        user.setPassword(passwordEncoder);
        System.out.println(passwordEncoder);
        boolean insertUser = registerMapper.insertUser(user);
        return insertUser;
    }

    public boolean checkUser(String username) {
        UserDTO user = loginMapper.checkUser(username);
        return user == null;

    }
}
