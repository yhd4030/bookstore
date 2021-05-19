package com.bookstore.haid.controller.admin;

import com.bookstore.haid.model.VisitCount;
import com.bookstore.haid.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/report")
public class AdminReportController {

    @Autowired
    private ReportService reportService;

    //打开销售量报表界面
    @RequestMapping("/sale")
    public String saleReport(Model model) {
        List<Map<String, Object>> saleReport = reportService.queryForReport();
        model.addAttribute("saleReport", saleReport);
        List<Object> groupType = new ArrayList<>();
        List<Long> sales = new ArrayList<>();
        List<Double> totalPrice = new ArrayList<>();
        for (Map<String, Object> map : saleReport) {
            groupType.add(map.get("groupType"));
            sales.add((Long) map.get("sales"));
            totalPrice.add((Double) map.get("totalPrice"));
        }
        Double maxTotalPrice = Collections.max(totalPrice);
        Long maxSales = Collections.max(sales);
        model.addAttribute("groupType", groupType);
        model.addAttribute("sales", sales);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("maxTotalPrice", maxTotalPrice);
        model.addAttribute("maxSales", maxSales);

        return "admin/report/saleReport";
    }
    //访问量报表界面
    @RequestMapping("/visit")
    public String visitReport(Model model) {
        List<VisitCount> visitCounts = reportService.queryVisitReport();
        List<Object> groupType = new ArrayList<>();
        List<Object> counts = new ArrayList<>();
        for (VisitCount visitCount : visitCounts) {
            groupType.add(visitCount.getVisitDate());
            counts.add(visitCount.getQuantity());
        }
        model.addAttribute("groupType", groupType);
        model.addAttribute("counts", counts);
        model.addAttribute("visitCounts", visitCounts);
        return "admin/report/visitReport";
    }

}
