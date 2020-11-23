package com.bookstore.haid.service;

import com.bookstore.haid.mapper.UserInfoMapper;
import com.bookstore.haid.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    public User findUserById(String username) {
        return userInfoMapper.findUserById(username);
    }

    public Boolean updateUser(User user) {
        return userInfoMapper.updateUser(user);
    }
}
