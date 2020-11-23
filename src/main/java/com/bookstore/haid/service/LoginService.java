package com.bookstore.haid.service;

import com.bookstore.haid.mapper.LoginMapper;
import com.bookstore.haid.model.User;
import com.bookstore.haid.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//字符串s 为表单传过来的username
        User user = loginMapper.checkUser(s);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//        passwordEncoderUtil.passwordEncoder(user.getPassword())
    }

}
