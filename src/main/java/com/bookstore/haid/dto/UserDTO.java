package com.bookstore.haid.dto;

import com.bookstore.haid.model.Address;
import com.bookstore.haid.model.Role;
import lombok.Data;


import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private int sex;
    private String email;
    private String phone;
    private String zip_code;
    private String location;
    private String detail_address;
    private String identity;
    private Role role;
    private List<Address> address;
}
