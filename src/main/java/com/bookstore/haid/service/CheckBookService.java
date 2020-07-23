package com.bookstore.haid.service;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.mapper.CheckBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckBookService {
    @Autowired
    private CheckBookMapper checkBookMapper;

    public BookMsgDTO checkAllMsg(Integer bookId) {
        BookMsgDTO bookMsgDTO = checkBookMapper.checkAllMsg(bookId);
        return bookMsgDTO;
    }
}
