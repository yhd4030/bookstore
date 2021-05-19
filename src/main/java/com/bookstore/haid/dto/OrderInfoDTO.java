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

    public String getOrderStatusDisplay(){
        switch (order_status){
            case "0": return "未支付";
            case "1": return "已支付";
            case "3": return "已取消";
            case "4": return "已完成";
            default:return "未知";
        }
    }

}
