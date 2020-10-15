package com.bookstore.haid.mapper;

import com.bookstore.haid.model.ShopCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShopCartMapper {

    @Insert("insert into book_shopcart values(#{bookId},#{userName},#{bookName},#{imgUrl},#{bookPrice})")
    Boolean addInCart(ShopCart shopCart);

    @Update("update book_shopcart set userName=#{userName},bookName=#{bookName},imgUrl=#{imgUrl},bookPrice=#{bookPrice} where bookId=#{bookId}")
    Boolean savaInCart(ShopCart shopCart);

    @Select("select bookId from book_shopcart where bookId=#{bookId}")
    ShopCart selectInCart(Integer bookId);

    @Select("select bookId,userName,bookName,imgUrl,bookPrice from book_shopcart where userName=#{username}")
    List<ShopCart> selectAll(String username);
}
