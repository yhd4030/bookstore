package com.bookstore.haid.dto;

import com.bookstore.haid.model.BookImgs;
import com.bookstore.haid.model.BookPublish;
import com.bookstore.haid.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BookMsgDTO {
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
    private Integer publish_Id;
    private BookPublish bookPublish;
    private Category category;

    public BookMsgDTO() {
    }

    public BookMsgDTO(BookPublish bookPublish, Category category) {
        this.bookPublish = bookPublish;
        this.category = category;
    }
}
