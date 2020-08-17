package com.bookstore.haid.controller;

import com.bookstore.haid.model.User;
import com.bookstore.haid.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 登录
 * */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/user/login")
    public  String login(){
        return "login";
    }

//    @ResponseBody
//    @PostMapping(value = "/user/login/checkUser")
//    public User checkUser(User user, HttpServletRequest request, HttpServletResponse response){
//        HttpSession session = request.getSession(true);
//        User checkUser = loginService.checkUser(user);
//        if (checkUser!=null){
//            session.setAttribute("user",checkUser);
//        }
//        return checkUser;
//    }
}
