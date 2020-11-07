package com.bookstore.haid.model;

import lombok.Data;

@Data
public class Pagination {
    private int currentPage =1;  //当前页
    private int pageSize = 6; // 每页显示几条数据
}
