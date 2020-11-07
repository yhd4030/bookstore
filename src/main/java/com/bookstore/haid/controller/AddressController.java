package com.bookstore.haid.controller;

import com.bookstore.haid.model.Address;
import com.bookstore.haid.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping("/add")
    @ResponseBody
    public String savaAddress(Address address, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        address.setUser_id(username);
        addressService.savaAddress(address);
        return "success";
    }
}
