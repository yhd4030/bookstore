package com.bookstore.haid.service;

import com.bookstore.haid.mapper.SelectBookImgMapper;
import com.bookstore.haid.model.BookMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectBookImgService {
    @Autowired
    private SelectBookImgMapper bookImgMapper;

    public List<BookMsg> findAllImg(Integer typeId) {
        List<BookMsg> allImg = bookImgMapper.findAllImg(typeId);
        return allImg;
    }
}
