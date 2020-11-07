package com.bookstore.haid.mapper;

import com.bookstore.haid.model.OrderItem;
import com.bookstore.haid.model.ShopCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    void insertItem(@Param(value = "orderItems") List<OrderItem> orderItems);

    void deleteBooksInCart(@Param(value = "cartIds") List<Integer> cartIds);
}
