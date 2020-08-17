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

//    public void checkUser(HttpServletRequest request) {
//        HttpSession session = request.getSession(true);
//        String username = (String) session.getAttribute("username");
//        User checkUser = loginMapper.checkUser(username);
//        session.setAttribute("user",checkUser);
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = loginMapper.checkUser(s);
        List<SimpleGrantedAuthority> authorities =new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), passwordEncoderUtil.passwordEncoder(user.getPassword()), authorities);

    }

}
