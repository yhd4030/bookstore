package com.bookstore.haid.mapper;

import com.bookstore.haid.dto.BookMsgDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CheckBookMapper {
    @Select("SELECT * FROM book_Msg bm WHERE bm.`bookId`=#{bookId}")
    BookMsgDTO checkAllMsg(Integer bookId);
}
