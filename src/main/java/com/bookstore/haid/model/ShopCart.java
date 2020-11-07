package com.bookstore.haid.model;

import lombok.Data;

@Data
public class ShopCart {
    private Integer id;
    private Integer bookId;
    private String userName;
    private String bookName;
    private String imgUrl;
    private Double bookPrice;
    private Integer buyNum=1;
    private Double subTotal;
}
