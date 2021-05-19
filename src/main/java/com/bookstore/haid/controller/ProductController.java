package com.bookstore.haid.controller;

import com.bookstore.haid.model.BookMsg;
import com.bookstore.haid.service.ProductInfoService;
import com.bookstore.haid.service.ProductListService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductListService productListService;

    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping("/list")
    public String productList(Model model,
                              @RequestParam(value = "errorMsg", required = false) String errorMsg,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "6") int pageSize,
                              @RequestParam(value = "keywords", defaultValue = "", required = false) String keywords,
                              HttpServletRequest request) {
        PageInfo<BookMsg> pageInfo = productListService.findAllProduct(pageNum, pageSize, keywords);
        if (keywords != null && !keywords.equals("")) {
            request.setAttribute("keywords", keywords);
        } else {
            request.setAttribute("keywords", "");
        }
        model.addAttribute("productList", pageInfo);
        return "productList";
    }
}
