package com.bookstore.haid.controller.admin;


import com.bookstore.haid.dto.OrderInfoDTO;
import com.bookstore.haid.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;


    //商品列表界面
    @RequestMapping("/list")
    public String productList(Model model,
                              @RequestParam(value = "errorMsg", required = false) String errorMsg,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "6") int pageSize,
                              @RequestParam(value = "keywords", defaultValue = "", required = false) String keywords,
                              HttpServletRequest request) {
        PageInfo<OrderInfoDTO> pageInfo = orderService.queryForList(pageNum, pageSize, keywords);
        if (keywords != null && !keywords.equals("")) {
            request.setAttribute("keywords", keywords);
        } else {
            request.setAttribute("keywords", "");
        }
        model.addAttribute("pageInfo", pageInfo);
        return "admin/order/list";
    }
}
