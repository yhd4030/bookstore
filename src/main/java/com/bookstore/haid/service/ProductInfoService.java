package com.bookstore.haid.service;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.mapper.ProductInfoMapper;
import com.bookstore.haid.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoService {
    @Autowired
    private ProductInfoMapper productInfoMapper;
//
//    public ProductDTO findNewProduct(Integer id) {
//        return productInfoMapper.findNewProductById(id);
//    }
//
//    public ProductDTO findRecommended(Integer id) {
//        return productInfoMapper.findRecommendedById(id);
//    }
//
//    public List<ShopProduct> findProductByRandom() {
//        int productNum = productInfoMapper.countProduct();
//        int num;
//        List<Integer> numList = new ArrayList<>();
//        for (int i = 1; i <= 3; i++) {
//            num = (int) (Math.random() * productNum) + 1;
//            numList.add(num);
//        }
//        List<ShopProduct> productByRandom = productInfoMapper.findProductByRandom(numList);
//        return productByRandom;
//
//    }
//
    public BookMsgDTO findProduct(Integer id) {
        return productInfoMapper.findProductById(id);
    }
//
    public List<Category> findCategory() {
        List<Category> categories = productInfoMapper.findCategory();

        return categories;
    }
}
