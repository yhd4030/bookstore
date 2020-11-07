package com.bookstore.haid.model;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String zip_code;
    private String location;
    private String detail_address;
    private String identity;
    private String role;
}
