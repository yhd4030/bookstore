package com.bookstore.haid.service;

import com.bookstore.haid.dto.OrderInfoDTO;
import com.bookstore.haid.mapper.OrderItemMapper;
import com.bookstore.haid.mapper.OrderMapper;
import com.bookstore.haid.mapper.ShopCartMapper;
import com.bookstore.haid.model.BookMsg;
import com.bookstore.haid.model.Order;
import com.bookstore.haid.model.OrderItem;
import com.bookstore.haid.model.ShopCart;
import com.bookstore.haid.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemService orderItemService;

    public List<ShopCart> selectByCheck(String[] strings,String username) {
        Integer bookId;
        List<ShopCart> shopCartList = new ArrayList<>();
        for (String string : strings) {
            bookId= Integer.valueOf(string);
            ShopCart shopCart = shopCartMapper.selectByCheck(bookId, username);
            shopCartList.add(shopCart);
        }
        return shopCartList;
    }

    public Order  buy(Integer addressId, HttpSession session,Double OrderTotal, List<ShopCart> checkBooks) {
        // 1.生成订单表记录

        String username = (String) session.getAttribute("username");
        Order order = new Order();
        String orderId = IDUtils.genOrderId();
        order.setUser_id(username);
        order.setOrder_total(OrderTotal);
        order.setAddress_id(addressId);
        order.setCreate_date(new Date());
        order.setOrder_num(orderId);
        order.setOrder_status("0");
        orderMapper.insert(order);
        // 2.生成订单详情记录
        List<OrderItem> orderItems = new ArrayList<>();
        List<Integer> cartIds= new ArrayList<>();
        for (ShopCart checkBook : checkBooks) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook_id(checkBook.getBookId());
            orderItem.setCount(checkBook.getBuyNum());
            orderItem.setOrder_id(order.getOrder_num());
            cartIds.add(checkBook.getId());
            orderItems.add(orderItem);
        }
        orderItemService.insertItem(orderItems);
        // 3.删除购物车记录
        orderItemService.deleteBooksInCart(cartIds);
        return order;
    }

    public List<OrderInfoDTO> selectOrderInfo(String username) {
        List<OrderInfoDTO> orderInfoDTOS = orderMapper.selectOrderInfo(username);
        return orderInfoDTOS;
    }

    public OrderInfoDTO findOrderById(Integer id,String username) {
        OrderInfoDTO orderById = orderMapper.findOrderById(id, username);
        return orderById;
    }

    public List<ShopCart> selectBookMsgByBookId(String username,Integer bookId) {
        ShopCart shopCart = new ShopCart();
        List<ShopCart> shopCartList =new ArrayList<>();
        BookMsg bookMsg = orderMapper.selectBookMsgByBookId(bookId);
        shopCart.setBookId(bookId);
        shopCart.setUserName(username);
        shopCart.setBookName(bookMsg.getBookName());
        shopCart.setImgUrl(bookMsg.getImgUrl());
        shopCart.setBookPrice(bookMsg.getBookPrice());
        shopCart.setSubTotal(bookMsg.getBookPrice()*1);
        shopCart.setBuyNum(1);
        shopCartList.add(shopCart);
        return shopCartList;
    }

    public Boolean OrderStatusUpdate(Integer id) {
        Boolean aBoolean = orderMapper.OrderStatusUpdate("4", id);
        return aBoolean;
    }

    public Boolean deleteOrderById(Integer id) {
        Boolean aBoolean = orderMapper.deleteOrderById(id);
        return aBoolean;
    }

    public Order findIdByOrderNum(String order_num) {
        return orderMapper.findIdByOrderNum(order_num);


    }

    public void updateOrderStatus(String order_num,String status) {
        orderMapper.updateOrderStatus(order_num,status);
    }
}
