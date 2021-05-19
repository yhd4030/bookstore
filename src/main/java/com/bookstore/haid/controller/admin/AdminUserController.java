package com.bookstore.haid.controller.admin;

import com.bookstore.haid.model.ExecuteResult;
import com.bookstore.haid.model.Role;
import com.bookstore.haid.model.User;
import com.bookstore.haid.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserInfoService userInfoService;

    //用户列表
    @RequestMapping("/list")
    public String userList(Model model,
                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                           @RequestParam(value = "keywords", defaultValue = "", required = false) String keywords) {
        PageInfo<User> userList = userInfoService.userList(pageNum, pageSize,keywords);
        model.addAttribute("userList", userList);
        model.addAttribute("keywords", keywords);
        return "admin/user/list";
    }

    //删除用户
    @PostMapping("/deletion")
    @ResponseBody
    public ExecuteResult deleteUser(Integer id) {
        boolean delete = userInfoService.deleteById(id);
        if (delete){
            return new ExecuteResult(true,"删除成功");
        }
        return new ExecuteResult(false,"删除失败");
    }

    //添加用户页面
    @GetMapping("/add")
    public String addUser(Model model) {
        List<Role> roles= userInfoService.queryAllRole();
        model.addAttribute("roles", roles);
        return "admin/user/add";
    }

    //添加用户
    @PostMapping("/addition")
    public String addition(User user, Model model) {
        Boolean isSuccess = userInfoService.addUser(user);
        if (isSuccess) {
            model.addAttribute("addSuccess","success");
        }else {
            model.addAttribute("addFail","fail");
        }

        return "redirect:/admin/user/list";
    }

    //编辑用户
    @GetMapping("/edit")
    public String editUser(Integer id, Model model) {
        User userMsg = userInfoService.findUserById(id);
       List<Role> roles= userInfoService.queryAllRole();
        model.addAttribute("userMsg", userMsg);
        model.addAttribute("roles", roles);
        return "admin/user/edit";
    }

    @PostMapping("/checkUserExist")
    @ResponseBody
    public boolean checkUserExist(String username) {
        User user = userInfoService.checkUserExist(username);
        if (user != null) {
            return false;
        }
        return true;
    }
}
