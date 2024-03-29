package com.bookstore.haid.controller;

import com.bookstore.haid.model.Address;
import com.bookstore.haid.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
//地址管理
@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    //添加收货地址
    @RequestMapping("/add")
    @ResponseBody
    public String savaAddress(Address address, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        address.setUser_id(username);
        addressService.savaOrUpdate(address);
        return "success";
    }

    //根据id查询收获地址
    @PostMapping("/find")
    @ResponseBody
    public Address findAddress(Integer addressId) {
        Address address = addressService.findById(addressId);
        return address;
    }

    //根据id删除收获地址
    @PostMapping("/del")
    @ResponseBody
    public Boolean delAddress(Integer addressId) {
        Boolean aBoolean = addressService.delById(addressId);
        return aBoolean;
    }
}
