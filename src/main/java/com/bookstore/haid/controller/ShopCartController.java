package com.bookstore.haid.controller;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.model.ShopCart;
import com.bookstore.haid.service.CheckBookService;
import com.bookstore.haid.service.ShopCartService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/books")
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private CheckBookService checkBookService;


    @RequestMapping("/shopCart")
    public String shopCart(@RequestParam(name = "username") String username,
                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "6") int pageSize,
                           HttpServletRequest request,
                           Model model) {
//        调用selectAll()方法 传入需要的当前页pageNum和分页显示的条数pageSize
        PageInfo<ShopCart> shopCarts = shopCartService.selectAll(username, pageNum, pageSize, request);
        model.addAttribute("shopCarts", shopCarts);
        return "shopCart";
    }

    @PostMapping("/shopCart/add")
    @ResponseBody
    public Boolean addShopCart(String username, Integer bookId) {
        BookMsgDTO bookMsgDTO = checkBookService.checkAllMsg(bookId);
        ShopCart shopCart = new ShopCart();
        shopCart.setBookId(bookId);
        shopCart.setBookName(bookMsgDTO.getBookName());
        shopCart.setBookPrice(bookMsgDTO.getBookPrice());
        shopCart.setUserName(username);
        String imgUrl = bookMsgDTO.getImgUrl();
        String[] imgUrlSplit = imgUrl.split("~");
        shopCart.setImgUrl(imgUrlSplit[0]);
        shopCart.setSubTotal(bookMsgDTO.getBookPrice());
        Boolean isSuccess = shopCartService.addInCart(shopCart);
        return isSuccess;
    }

    @RequestMapping("/addOrSub")
    @ResponseBody
    public Double addOrSub(ShopCart shopCart, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        shopCart.setUserName(username);
        Double total = shopCartService.updateBuyNum(shopCart);
        return total;

    }

    @RequestMapping("/shopCart/delete")
    @ResponseBody
    public void getBookNum(ShopCart shopCart,HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        shopCart.setUserName(username);
        shopCartService.deleteByUserName(shopCart);
    }
}
