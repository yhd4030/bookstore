package com.bookstore.haid.service;

import com.bookstore.haid.mapper.CartMapper;
import com.bookstore.haid.model.Cart;
import com.bookstore.haid.model.ExecuteResult;
import com.bookstore.haid.model.ShopCart;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCartService {
    @Autowired
    private CartMapper cartMapper;

    public Boolean addInCart(ShopCart shopCart) {

        ShopCart shopCart1 = cartMapper.selectInCart(shopCart.getBookId());
        if (shopCart1 == null) {
            Boolean isSuccess = cartMapper.addInCart(shopCart);
            Cart cart = cartMapper.selectTotalCart(shopCart);
            if (cart.getUserName() == null) {
                cartMapper.createCart(shopCart);
            } else {
                cartMapper.updateCart(shopCart);
            }
            return isSuccess;
        } else {
            Boolean aBoolean = cartMapper.savaInCart(shopCart);
            return aBoolean;
        }
    }

    public PageInfo<ShopCart> selectAll(String username, int pageNum, int pageSize, HttpServletRequest request) {
//        调用PageHelper的startPage方法，传入pageNum和pageSize
        PageHelper.startPage(pageNum, pageSize);
        double count = 0.0;
//        按用户名查找购物车里的数据
        List<ShopCart> shopCarts = cartMapper.selectAll(username);
        for (ShopCart shopCart : shopCarts) {
            count = count + (shopCart.getBookPrice().multiply(new BigDecimal(shopCart.getBuyNum())).intValue());
        }
        request.getSession().setAttribute("total", count);
        System.out.println(count);
//        返回PageInfo对象并传入shopCarts，让PageInfo对象帮我们分页
        return new PageInfo(shopCarts);
    }

    public BigDecimal updateBuyNum(ShopCart shopCart) {
        BigDecimal count = new BigDecimal("0");
        cartMapper.updateBuyNum(shopCart);
        List<ShopCart> shopCarts = cartMapper.selectAll(shopCart.getUserName());
        for (ShopCart cart : shopCarts) {
            count = count.add(cart.getBookPrice().multiply(new BigDecimal(cart.getBuyNum())));
        }
        cartMapper.updateTotal(shopCart.getUserName(), count);
        return count;
    }

    public void deleteByUserName(ShopCart shopCart) {
        cartMapper.deleteByUserName(shopCart);
    }

    public ExecuteResult deleteAll(String username, String[] bookIdStrs) {
        if (bookIdStrs == null || bookIdStrs.length == 0) {
            return new ExecuteResult(false, "请选择要删除的商品");
        }
        List<Integer> ids = new ArrayList<>();
        for (String bookIdStr : bookIdStrs) {
            ids.add(Integer.valueOf(bookIdStr));
        }
        boolean isSuccess = cartMapper.deleteAll(username, ids);
        if (isSuccess) {
            return new ExecuteResult(true, "删除成功");
        }
        return new ExecuteResult(false, "删除失败");
    }
}
