package com.bookstore.haid.controller;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.model.ShopCart;
import com.bookstore.haid.service.CheckBookService;
import com.bookstore.haid.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private CheckBookService checkBookService;


    @GetMapping("/books/shopCart")
    public String shopCart(@RequestParam(name = "username")String username, Model model){
        List<ShopCart> shopCarts = shopCartService.selectAll(username);
        model.addAttribute("shopCarts",shopCarts);
        System.out.println(shopCarts.get(1));

        return "shopCart";
    }

    @PostMapping("/books/shopCart/add")
    @ResponseBody
    public Boolean addShopCart(String username,Integer bookId){
        BookMsgDTO bookMsgDTO = checkBookService.checkAllMsg(bookId);
        ShopCart shopCart = new ShopCart();
        shopCart.setBookId(bookId);
        shopCart.setBookName(bookMsgDTO.getBookName());
        shopCart.setBookPrice(bookMsgDTO.getBookPrice());
        shopCart.setUserName(username);
        String imgUrl = bookMsgDTO.getImgUrl();
        String[] imgUrlSplit = imgUrl.split("~");
        System.out.println(imgUrlSplit[0]);
        shopCart.setImgUrl(imgUrlSplit[0]);
        Boolean isSuccess = shopCartService.addInCart(shopCart);
        return isSuccess;
    }
}
