package com.bookstore.haid.model;

import lombok.Data;

@Data
public class BookMsg {
    private Integer bookId;
    private String imgTitle;
    private Double bookPrice;
    private String imgUrl;
    private Integer typeId;
}
