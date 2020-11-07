package com.bookstore.haid.model;

import lombok.Data;

@Data
public class OrderItem {
    private Integer id;
    private String order_id;
    private Integer book_id;
    private Integer count;
    private BookMsg bookMsg;
}
