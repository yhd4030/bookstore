package com.bookstore.haid.mapper;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.model.BookMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CheckBookMapper {
    @Select("SELECT * FROM book_msg bm LEFT JOIN book_publish bp ON bm.`bookId`=bp.`bookId` WHERE bm.`bookId`=#{bookId}")
    BookMsgDTO checkAllMsg(Integer bookId);

    @Select("select * from book_msg where bookName like CONCAT('%',#{bookName},'%')")
    List<BookMsg> similarBook(String bookName);
}
