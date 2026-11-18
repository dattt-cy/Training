package com.example.ems.controller;

import com.example.ems.dto.DepartmentStatistic;
import com.example.ems.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public Map<String, Object> getSummary() {
        return statisticService.getSummary();
    }

    @GetMapping("/total")
    public Map<String, Long> getTotal() {
        Map<String, Long> body = new LinkedHashMap<>();
        body.put("totalEmployees", statisticService.getTotalEmployees());
        return body;
    }

    @GetMapping("/by-department")
    public List<DepartmentStatistic> getByDepartment() {
        return statisticService.getStatisticByDepartment();
    }
}
