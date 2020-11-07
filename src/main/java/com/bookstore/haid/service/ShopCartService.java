package com.bookstore.haid.service;

import com.bookstore.haid.mapper.ShopCartMapper;
import com.bookstore.haid.model.Cart;
import com.bookstore.haid.model.ShopCart;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ShopCartService {
    @Autowired
    private ShopCartMapper shopCartMapper;

    public Boolean addInCart(ShopCart shopCart) {

        ShopCart shopCart1 = shopCartMapper.selectInCart(shopCart.getBookId());
        if (shopCart1 == null) {
            Boolean isSuccess = shopCartMapper.addInCart(shopCart);
            Cart cart = shopCartMapper.selectTotalCart(shopCart);
            if (cart.getUserName()==null){
                shopCartMapper.createCart(shopCart);
            }else {
                shopCartMapper.updateCart(shopCart);
            }
            return isSuccess;
        } else {
            Boolean aBoolean = shopCartMapper.savaInCart(shopCart);
            return aBoolean;
        }
    }

    public PageInfo<ShopCart> selectAll(String username, int pageNum, int pageSize, HttpServletRequest request) {
//        调用PageHelper的startPage方法，传入pageNum和pageSize
        PageHelper.startPage(pageNum, pageSize);
        double count = 0.0;
//        按用户名查找购物车里的数据
        List<ShopCart> shopCarts = shopCartMapper.selectAll(username);
        for (ShopCart shopCart : shopCarts) {
            count = count + (shopCart.getBookPrice() * shopCart.getBuyNum());
        }
        request.getSession().setAttribute("total", count);
        System.out.println(count);
//        返回PageInfo对象并传入shopCarts，让PageInfo对象帮我们分页
        return new PageInfo(shopCarts);
    }

    public Double updateBuyNum(ShopCart shopCart) {
        double count = 0.0;
        shopCartMapper.updateBuyNum(shopCart);
        List<ShopCart> shopCarts = shopCartMapper.selectAll(shopCart.getUserName());
        for (ShopCart cart : shopCarts) {
            count = count + (cart.getBookPrice() * cart.getBuyNum());
        }
        shopCartMapper.updateTotal(shopCart.getUserName(),count);
        return count;
    }

    public void deleteByUserName(ShopCart shopCart) {
        shopCartMapper.deleteByUserName(shopCart);
    }
}
