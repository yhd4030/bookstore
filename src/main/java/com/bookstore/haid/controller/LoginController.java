package com.bookstore.haid.controller;

import com.bookstore.haid.model.User;
import com.bookstore.haid.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @PostMapping("/login/error")
    public String loginError(HttpServletRequest request,Model model) {
        model.addAttribute("error", "登录失败");
        return "login";
    }



}
