package com.bookstore.haid.service;

import com.bookstore.haid.mapper.UserInfoMapper;
import com.bookstore.haid.model.User;
import com.bookstore.haid.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    public User findUserById(String username) {
        return userInfoMapper.findUserById(username);
    }

    public Boolean updateUser(User user) {
        return userInfoMapper.updateUser(user);
    }

    //密码验证
    public Boolean updatepwd(String oldPassword, String newPassword, String rePassword, HttpSession session) {
        String username = (String) session.getAttribute("username");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (oldPassword != null && oldPassword != "") {
            User user = userInfoMapper.checkPwd(username);
            //密码验证 用户输入的原密码与数据库中加密过的密码对比
            boolean matches = bCryptPasswordEncoder.matches(oldPassword, user.getPassword());
            if (matches) {
                System.out.println("---------------第一次对比密码成功---------------");
                //比较两次输入的密码是否一致  前端校验过 保险再校验一次
                if (newPassword.equals(rePassword)) {
                    System.out.println("---------------两次密码一致---------------");
                    //加密
                    String passwordEncoder = passwordEncoderUtil.passwordEncoder(newPassword);
                    //修改密码
                    Boolean isSuccess = userInfoMapper.updatepwd(username, passwordEncoder);
                    return isSuccess;
                } else {
                    System.out.println("---------------修改失败---------------");
                    return false;
                }
            } else {
                System.out.println("---------------第一次对比密码失败---------------");
            }

        }
        System.out.println("---------------密码错误---------------");
        return false;
    }
}
