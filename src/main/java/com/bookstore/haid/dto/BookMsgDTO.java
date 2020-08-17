package com.bookstore.haid.dto;

import com.bookstore.haid.model.BookImgs;
import com.bookstore.haid.model.BookPublish;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class BookMsgDTO {
    private Integer bookId;
    private String bookName;
    private Double bookPrice;
    private String imgUrl;
    private Integer typeId;
    private String author;
    private String press;
    private Date releaseDate;
    private String bookDescription;
}
