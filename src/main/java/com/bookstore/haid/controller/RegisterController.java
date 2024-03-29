package com.bookstore.haid.controller;


import com.bookstore.haid.model.User;
import com.bookstore.haid.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public boolean register(User user) {
        boolean checkUser = registerService.checkUser(user.getUsername());
        if (checkUser){
            boolean registerUser = registerService.registerUser(user);
            return registerUser;
        }
        return false;
    }

}
