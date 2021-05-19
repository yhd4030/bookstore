package com.bookstore.haid.controller.admin;

import com.bookstore.haid.model.User;
import com.bookstore.haid.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    @Autowired
    private UserInfoService userInfoService;

    //管理员主页
    @GetMapping("/home")
    public String goToHome() {
        return "admin/adminHome";
    }

//    @GetMapping("/left")
//    public String left() {
//        return "admin/adminLeft";
//    }
//
//    @GetMapping("/center")
//    public String center() {
//        return "admin/adminCenter";
//    }
//
//    @GetMapping("/right")
//    public String right() {
//        return "admin/adminRight";
//    }
//
//    @GetMapping("/nav")
//    public String nav() {
//        return "navigation";
//    }

    @GetMapping("/success")
    public String success() {
        return "admin/success";
    }

    @GetMapping("/fail")
    public String fail() {
        return "admin/fail";
    }

    @GetMapping("/index2")
    public String index2() {
        return "admin/index2";
    }

//    @GetMapping("/index1")
//    public String index1() {
//
//        return "admin/index1";
//    }
}
