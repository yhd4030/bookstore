package com.bookstore.haid.dto;

import com.bookstore.haid.model.BookImgs;
import com.bookstore.haid.model.BookPublish;
import lombok.Data;

import java.util.List;

@Data
public class BookMsgDTO {
    private Integer bookId;
    private String imgTitle;
    private Double bookPrice;
    private String imgUrl;
    private Integer typeId;
    private BookPublish bookPublish;
}
