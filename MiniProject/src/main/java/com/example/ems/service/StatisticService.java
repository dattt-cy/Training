package com.example.ems.service;

import com.example.ems.dto.DepartmentStatistic;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public StatisticService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public long getTotalEmployees() {
        return employeeRepository.countAllEmployees();
    }

    public long getTotalDepartments() {
        return departmentRepository.count();
    }

    public double getAverageSalary() {
        Double average = employeeRepository.averageSalary();
        if (average == null) {
            return 0;
        }
        return average;
    }

    public List<DepartmentStatistic> getStatisticByDepartment() {
        return employeeRepository.statisticByDepartment();
    }

    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("totalEmployees", getTotalEmployees());
        summary.put("totalDepartments", getTotalDepartments());
        summary.put("averageSalary", getAverageSalary());
        summary.put("byDepartment", getStatisticByDepartment());
        return summary;
    }
}
