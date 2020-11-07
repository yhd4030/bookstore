package com.bookstore.haid.model;

import lombok.Data;

import java.util.Date;
@Data
public class Order {
   private Integer id;
   private String order_num;
   private Date create_date;
   private String user_id;
   private Integer address_id;
   private String order_status;
   private Double order_total;
}
