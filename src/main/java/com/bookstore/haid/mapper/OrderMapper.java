package com.bookstore.haid.mapper;

import com.bookstore.haid.dto.OrderInfoDTO;
import com.bookstore.haid.model.BookMsg;
import com.bookstore.haid.model.Order;
import com.bookstore.haid.model.ShopCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insert(Order order);

    List<OrderInfoDTO> selectOrderInfo(@Param(value = "username") String username);

    OrderInfoDTO findOrderById(@Param(value = "id") Integer id, @Param(value = "username") String username);

    BookMsg selectBookMsgByBookId(Integer bookId);

    Boolean OrderStatusUpdate(@Param(value = "status") String status, @Param(value = "id") Integer id);

    Boolean deleteOrderById(@Param("id") Integer id, @Param("status") String status);

    Order findIdByOrderNum(String order_num);

    void updateOrderStatus(@Param(value = "order_num") String order_num, @Param(value = "status") String status);

    List<OrderInfoDTO> selectForList(@Param("keywords") String keywords);

    Boolean cancelOrderById(@Param("id") Integer id, @Param("status") String status);
}
