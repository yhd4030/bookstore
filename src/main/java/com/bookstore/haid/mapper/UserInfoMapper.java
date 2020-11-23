package com.bookstore.haid.mapper;

import com.bookstore.haid.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    User findUserById(String username);

    Boolean updateUser(User user);
}
