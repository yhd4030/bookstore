package com.bookstore.haid.service;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.mapper.CheckBookMapper;
import com.bookstore.haid.model.BookMsg;
import com.bookstore.haid.model.BookPublish;
import com.bookstore.haid.model.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckBookService {
    @Autowired
    private CheckBookMapper checkBookMapper;

    public BookMsgDTO checkAllMsg(Integer bookId) {
        BookMsgDTO bookMsgDTO = checkBookMapper.checkAllMsg(bookId);
        return bookMsgDTO;
    }

    public List<BookMsg> similarBook(String bookName) {
        List<BookMsg> similarBook = checkBookMapper.similarBook(bookName);
        return similarBook;
    }


}
