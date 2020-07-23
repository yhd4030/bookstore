package com.bookstore.haid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserInformationController {

    @RequestMapping("/user/information")
    public String imformation(){

        return "userInformation";
    }
}
