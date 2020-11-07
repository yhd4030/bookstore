package com.bookstore.haid.dto;

import com.bookstore.haid.model.Address;
import com.bookstore.haid.model.OrderItem;
import com.bookstore.haid.model.ShopCart;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderInfoDTO {
    private Integer id;
    private String order_num;
    private Date create_date;
    private String user_id;
    private Integer address_id;
    private String order_status;
    private Double order_total;
    private Address address;
    private List<OrderItem> orderItem;

}
