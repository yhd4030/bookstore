package com.bookstore.haid.mapper;

import com.bookstore.haid.model.BookMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SelectBookImgMapper {
    @Select("select * from book_msg where typeId=#{typeId}")
    List<BookMsg> findAll(Integer typeId);
}
