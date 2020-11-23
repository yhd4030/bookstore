package com.bookstore.haid.mapper;

import com.bookstore.haid.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {
    User findUserById(String username);

    Boolean updateUser(User user);

    Boolean updatepwd(@Param("username") String username, @Param("password") String password);

    User checkPwd(String username);
}
