package com.bookstore.haid.mapper;

import com.bookstore.haid.model.Role;
import com.bookstore.haid.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    User findUserByUsername(String username);

    Boolean updateUser(User user);

    Boolean updatepwd(@Param("username") String username, @Param("password") String password);

    User checkPwd(String username);

    List<User> findAllUser(@Param("keywords") String keywords);

    boolean deleteById(Integer id);

    Boolean addUser(User user);

    User findUserById(Integer id);

    User findUser(String username);

    List<Role> selectAllRole();

}
