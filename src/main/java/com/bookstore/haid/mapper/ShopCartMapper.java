package com.bookstore.haid.mapper;

import com.bookstore.haid.model.Cart;
import com.bookstore.haid.model.ShopCart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShopCartMapper {
    //加入购物车
    @Insert("insert into book_shopcart values( #{id},#{bookId},#{userName},#{bookName},#{imgUrl},#{bookPrice},#{buyNum},#{subTotal})")
    Boolean addInCart(ShopCart shopCart);

    //更新购物车的信息
    @Update("update book_shopcart set userName=#{userName},bookName=#{bookName},imgUrl=#{imgUrl},bookPrice=#{bookPrice} where bookId=#{bookId}")
    Boolean savaInCart(ShopCart shopCart);

    @Select("select bookId from book_shopcart where bookId=#{bookId}")
    ShopCart selectInCart(Integer bookId);

    @Select("select bookId,userName,bookName,imgUrl,bookPrice,buyNum,subTotal from book_shopcart where userName=#{username}")
    List<ShopCart> selectAll(String username);

    //    @Update("update book_shopcart set subTotal=#{subTotal},buyNum=#{buyNum} where bookId=#{bookId} and userName=#{userName}")
//    Boolean updateSubTotal(ShopCart shopCart);
//
    @Select("select * from book_shopcart where bookId=#{bookId} and userName=#{userName}")
    ShopCart selectByCheck(Integer bookId,String userName);
//插入一个购物车总金额
    @Insert("insert into book_cart values (#{userName},#{bookPrice})")
    void createCart(ShopCart shopCart);

    //    更新购物车中每本书的数量 同时算出小计
    @Update("update book_shopcart set buyNum=#{buyNum}, subTotal=#{buyNum}*bookPrice where bookId=#{bookId} and userName=#{userName} ")
    void updateBuyNum(ShopCart shopCart);

    //    更新购物车所有物品的总金额
    @Update("update book_cart set total=#{total} where userName=#{userName}")
    void updateTotal(String userName, Double total);

    //  更新从查看书籍页面添加到购物车时 总金额的改变
    @Update("update book_cart set total=#{bookPrice}+total where userName=#{userName}")
    void updateCart(ShopCart shopCart);

    //查询购物车总金额
    @Select("select userName,total from book_cart where userName=#{userName}")
    Cart selectTotalCart(ShopCart shopCart);

    //根据username删除
    @Delete("delete from book_shopcart where bookId=#{bookId} and userName=#{userName}")
    void deleteByUserName(ShopCart shopCart);
}
