package com.bookstore.haid.controller;

import com.bookstore.haid.model.User;
import com.bookstore.haid.service.UserInfoService;
import com.bookstore.haid.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserInformationController {
    @Autowired
    private UserInfoService userInfoService;


    @GetMapping("/information")
    public String imformation(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = userInfoService.findUserById(username);
        model.addAttribute("user", user);
        return "userInformation";
    }

    //修改用户信息
    @PostMapping("/update")
    @ResponseBody
    public Boolean savaUser(User user, HttpSession session) {
        String username = (String) session.getAttribute("username");
        user.setUsername(username);
        Boolean aBoolean = userInfoService.updateUser(user);
        System.out.println(user);
        return aBoolean;
    }

    //修改密码
    @PostMapping("/updatePwd")
    @ResponseBody
    public Boolean updatepwd(String oldPassword, String newPassword, String rePassword, HttpSession session) {
        Boolean aBoolean = userInfoService.updatepwd(oldPassword, newPassword, rePassword, session);
        return aBoolean;
    }
}
