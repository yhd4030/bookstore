package com.bookstore.haid.mapper;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductInfoMapper {


    int countProduct();

    BookMsgDTO findProductById(Integer id);
//
    List<Category> findCategory();
}
