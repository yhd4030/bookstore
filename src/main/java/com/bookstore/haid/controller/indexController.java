package com.bookstore.haid.controller;

import com.bookstore.haid.model.BookMsg;
import com.bookstore.haid.model.User;
import com.bookstore.haid.service.LoginService;
import com.bookstore.haid.service.SelectBookImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * 主页
 * */
@Controller
public class indexController {

    @Autowired
    private SelectBookImgService bookImgService;

    @Autowired
    private LoginService loginService;

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/tab_selectBook")
    public List<BookMsg> selectBookImg(Integer typeId){
        List<BookMsg> allImgList = bookImgService.findAllImg(typeId);
        return allImgList;
    }



}
