package com.bookstore.haid.model;

import lombok.Data;

import java.util.Date;

@Data
public class BookPublish {
    private Integer bookId;
    private String author;
    private String press;
    private Date releaseDate;
}
