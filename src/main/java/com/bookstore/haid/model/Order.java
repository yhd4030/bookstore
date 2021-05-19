package com.bookstore.haid.model;

import lombok.Data;

import java.util.Date;
@Data
public class Order {
   public final static  String  No_PAY_ORDER="0";
   public final static  String  HAVE_PAY_ORDER="1";
   public final static  String  DELETED_ORDER="2";
   public final static  String  CANCEL_ORDER="3";
   public final static  String  COMPLETE_ORDER="4";



   private Integer id;
   private String order_num;
   private Date create_date;
   private String user_id;
   private Integer address_id;
   private String order_status;
   private Double order_total;
}
