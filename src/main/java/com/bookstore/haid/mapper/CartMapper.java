package com.bookstore.haid.mapper;

import com.bookstore.haid.model.Cart;
import com.bookstore.haid.model.ShopCart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CartMapper {
    //加入购物车
    boolean addInCart(ShopCart shopCart);

    //更新购物车的信息
    boolean savaInCart(ShopCart shopCart);

    ShopCart selectInCart(Integer bookId);

    List<ShopCart> selectAll(String username);

    //    @Update("update book_shopcart set subTotal=#{subTotal},buyNum=#{buyNum} where bookId=#{bookId} and userName=#{userName}")
//    Boolean updateSubTotal(ShopCart shopCart);
//
    ShopCart selectByCheck(@Param("bookId") Integer bookId, @Param("userName") String userName);

    //插入一个购物车总金额
    void createCart(ShopCart shopCart);

    //    更新购物车中每本书的数量 同时算出小计
    void updateBuyNum(ShopCart shopCart);

    //    更新购物车所有物品的总金额
    void updateTotal(@Param("userName") String userName, @Param("total") BigDecimal total);

    //  更新从查看书籍页面添加到购物车时 总金额的改变
    void updateCart(ShopCart shopCart);

    //查询购物车总金额
    Cart selectTotalCart(ShopCart shopCart);

    //根据username删除
    void deleteByUserName(ShopCart shopCart);

    boolean deleteAll(@Param("username") String username, @Param("ids") List<Integer> ids);
}
