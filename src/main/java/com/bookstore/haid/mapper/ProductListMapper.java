package com.bookstore.haid.mapper;

import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.model.BookMsg;
import com.bookstore.haid.model.BookPublish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductListMapper {

//    List<BookMsg> findProductByTypeId(Integer typeId);

    List<BookMsg> findAllProduct(@Param("keywords") String keywords);

    boolean updateIsShelf(BookMsg bookMsg);

    boolean deleteBookById(Integer id);

    boolean updateProduct(BookMsgDTO bookMsgDTO);


    boolean updatePublishById(BookPublish bookPublish);
//
    Integer addPublish(BookPublish bookPublish);

    boolean addProduct(BookMsgDTO productDTO);
}
