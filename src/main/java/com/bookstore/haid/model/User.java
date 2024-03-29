package com.bookstore.haid.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer sex;
    private String email;
    private String phone;
    private String zip_code;
    private String location;
    private String detail_address;
    private String identity;
    private Integer roleId;
    private List<Address> address;
}
