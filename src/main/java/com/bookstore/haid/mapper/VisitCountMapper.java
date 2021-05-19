package com.bookstore.haid.mapper;


import com.bookstore.haid.model.VisitCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VisitCountMapper {

    void insert(VisitCount visitCount);

    void updateById(VisitCount visitCount);

    void deleteById(Long id);

    VisitCount selectById(Long id);

    List<VisitCount> selectAll();

    VisitCount checkVisitDate(String visitDate);

    void updateVisitCount(VisitCount visitCount);
}
