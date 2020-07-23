package com.bookstore.haid.mapper;

import com.bookstore.haid.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    User checkUser(String username, String password);
}
