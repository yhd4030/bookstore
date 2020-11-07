package com.bookstore.haid.service;

import com.bookstore.haid.mapper.OrderItemMapper;
import com.bookstore.haid.model.OrderItem;
import com.bookstore.haid.model.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    public void insertItem(List<OrderItem> orderItems) {
        orderItemMapper.insertItem(orderItems);
    }

    public void deleteBooksInCart(List<Integer> cartIds) {

        orderItemMapper.deleteBooksInCart(cartIds);

    }
}
