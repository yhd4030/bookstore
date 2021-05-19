package com.bookstore.haid.service;


import com.bookstore.haid.mapper.ReportMapper;
import com.bookstore.haid.mapper.VisitCountMapper;
import com.bookstore.haid.model.VisitCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private VisitCountMapper visitCountMapper;

    public List<Map<String, Object>> queryForReport() {
        return reportMapper.selectForReport();
    }

    public List<VisitCount> queryVisitReport() {

        return visitCountMapper.selectAll();
    }
}
