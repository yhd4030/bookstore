package com.bookstore.haid.service;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.mapper.ProductListMapper;
import com.bookstore.haid.model.BookMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductListService {
    @Autowired
    private ProductListMapper productListMapper;

//    public PageInfo<BookMsg> findProductByTypeId(Integer typeId, int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<BookMsg> product = productListMapper.findProductByTypeId(typeId);
//        return new PageInfo<>(product);
//    }

    public PageInfo<BookMsg> findAllProduct(int pageNum, int pageSize, String keywords) {
        PageHelper.startPage(pageNum, pageSize);
        List<BookMsg> allProduct = productListMapper.findAllProduct(keywords);
        return new PageInfo<>(allProduct);
    }

    public boolean updateIsShelf(BookMsg bookMsg) {
        if (bookMsg.getBookId() == null) {
            return false;
        }
        if (bookMsg.getIsShelf() == null) {
            return false;
        }
        boolean updateIsShelf = productListMapper.updateIsShelf(bookMsg);

        return updateIsShelf;
    }

    public boolean deleteBookById(Integer id) {
        return productListMapper.deleteBookById(id);
    }

    public boolean updateOrAddProduct(BookMsgDTO bookMsgDTO) {
        if (bookMsgDTO.getBookName() == null || bookMsgDTO.getBookName().equals("")) {
            System.out.println("productDTO.getProductName等于空");
            throw new RuntimeException("输入的商品信息不完整，请重新输入！");
        }
        if (bookMsgDTO.getBookId() != null && bookMsgDTO.getBookPublish().getId() != null) {
            Boolean isUpdatePublish = null;
            Boolean isUpdateProduct = null;
            isUpdateProduct = productListMapper.updateProduct(bookMsgDTO);
            if (bookMsgDTO.getBookPublish() != null) {
                isUpdatePublish = productListMapper.updatePublishById(bookMsgDTO.getBookPublish());
            }
            return isUpdateProduct && isUpdatePublish;

        } else {
            Boolean isAddProduct = null;
            if (bookMsgDTO.getBookPublish() != null) {
                productListMapper.addPublish(bookMsgDTO.getBookPublish());
            }
            if (bookMsgDTO.getBookPublish().getId() != null) {
                //根据商品详情插入返回的id设置商品详情id
                bookMsgDTO.setDetail_id(bookMsgDTO.getBookPublish().getId());
            }
            bookMsgDTO.setIsShelf(0);
            bookMsgDTO.setPublish_Id(bookMsgDTO.getBookPublish().getId());
            isAddProduct = productListMapper.addProduct(bookMsgDTO);
            return isAddProduct;

        }


    }
}
