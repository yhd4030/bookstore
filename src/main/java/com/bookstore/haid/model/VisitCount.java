package com.bookstore.haid.model;

import lombok.Data;

@Data
public class VisitCount {
    private Long id;
    private String visitDate;
    private Long quantity;
}
