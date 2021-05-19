package com.bookstore.haid.mapper;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.model.BookMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CheckBookMapper {

    BookMsgDTO checkAllMsg(Integer bookId);

    List<BookMsg> similarBook(String bookName);

}
