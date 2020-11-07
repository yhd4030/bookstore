package com.bookstore.haid.mapper;

import com.bookstore.haid.model.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Select("select * from book_address where user_id=#{username} ")
    List<Address> findAddress(String username);

    @Insert("insert into book_address values (#{id},#{user_id},#{province},#{city},#{area},#{detail_address},#{receiver},#{tel},#{is_default})")
    void savaAddress(Address address);
}
