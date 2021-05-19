package com.bookstore.haid.model;

import lombok.Data;

@Data
public class Role {
    /** 主键*/
    private Integer id;

    /** 角色名称*/
    private String name;

    /** 角色编码*/
    private String sn;

}