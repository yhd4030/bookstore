package com.bookstore.haid.service;

import com.bookstore.haid.mapper.ShopCartMapper;
import com.bookstore.haid.model.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class ShopCartService {
    @Autowired
    private ShopCartMapper shopCartMapper;

    public Boolean addInCart(ShopCart shopCart) {

        ShopCart shopCart1 = shopCartMapper.selectInCart(shopCart.getBookId());
        if (shopCart1!=null){
            Boolean aBoolean = shopCartMapper.savaInCart(shopCart);
            return aBoolean;
        }else {
            Boolean isSuccess = shopCartMapper.addInCart(shopCart);
            return isSuccess;
        }
    }

    public List<ShopCart> selectAll(String username) {
        List<ShopCart> shopCarts = shopCartMapper.selectAll(username);
        return shopCarts;
    }
}
