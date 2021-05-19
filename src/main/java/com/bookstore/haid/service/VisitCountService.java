package com.bookstore.haid.service;

import com.bookstore.haid.mapper.VisitCountMapper;
import com.bookstore.haid.model.VisitCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitCountService {

    @Autowired
    private VisitCountMapper visitCountMapper;

    public void addVisitCount(String visitDate) {
        //判断今天的访问是否为空
        VisitCount visitCount = visitCountMapper.checkVisitDate(visitDate);
        if (visitCount == null) {
            VisitCount newVisitCount = new VisitCount();
            newVisitCount.setQuantity(1L);
            newVisitCount.setVisitDate(visitDate);
            visitCountMapper.insert(newVisitCount);
        } else {
            Long quantity = visitCount.getQuantity();
            quantity++;
            visitCount.setQuantity(quantity);
            visitCountMapper.updateVisitCount(visitCount);
        }
    }
}
