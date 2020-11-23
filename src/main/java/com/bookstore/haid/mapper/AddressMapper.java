package com.bookstore.haid.mapper;

import com.bookstore.haid.model.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AddressMapper {

    List<Address> findAddress(String username);

    void savaAddress(Address address);

    Address findById(Integer id);

    void updateAddress(Address address);

    Boolean delById(Integer addressId);

    Address findIsDefault();

    void updateIsDefault(Address address);
}
