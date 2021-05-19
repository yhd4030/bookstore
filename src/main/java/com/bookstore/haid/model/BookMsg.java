package com.bookstore.haid.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookMsg {
    private Integer bookId;
    private String bookName;
    private BigDecimal bookPrice;
    private String imgUrl;
    private Integer typeId;
    private String bookDescription;
    private BigDecimal discount;
    private Integer detail_id;
    private Long amount;
    private Integer isShelf;
}
