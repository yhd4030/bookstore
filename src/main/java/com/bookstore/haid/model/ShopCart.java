package com.bookstore.haid.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopCart {
    private Integer id;
    private Integer bookId;
    private String userName;
    private String bookName;
    private String imgUrl;
    private BigDecimal bookPrice;
    private Integer buyNum=1;
    private BigDecimal subTotal;
}
