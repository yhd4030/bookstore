package com.bookstore.haid.controller;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.model.BookMsg;
import com.bookstore.haid.service.CheckBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 查看书籍信息
 */
@Controller
public class CheckBookMsgController {
    @Autowired
    private CheckBookService checkBookService;
//查看书的信息
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String checkBook(@RequestParam("typeId") Integer typeId,
                            @RequestParam("bookId") Integer bookId,
                            HttpServletRequest request,
                            Model model) {
//接收页面传来的bookId进行书的查找
        BookMsgDTO bookMsgDTO = checkBookService.checkAllMsg(bookId);
        HttpSession session = request.getSession(true);
        session.setAttribute("bookMsgDTO", bookMsgDTO);
        String[] url_1 = new String[5];
        String[] url_2 = new String[5];
// 将书的图片的url进行切割
        String[] split = bookMsgDTO.getImgUrl().split("~");
//        如果将图片进行分组,每组5个照片,最多两组
        for (int i = 0; i <split.length; i++) {
            if (i < 5) {
                url_1[i] = split[i];
            } else  {
                int j = i - 5;
                url_2[j] = split[i];
            }
        }
        model.addAttribute("imgurls_1", url_1);
        model.addAttribute("imgurls_2", url_2);
        model.addAttribute("imgCount", split.length);
        return "checkAndBuy";
    }
    @ResponseBody
    @PostMapping("/books/selectBook")
    public List<BookMsg> selectBook(String bookName){
        List<BookMsg> similarBook = checkBookService.similarBook(bookName);
        return similarBook;
    }

    @GetMapping("/books/buy")
    public String buyBook(){

        return "buyBook";

    }
}
