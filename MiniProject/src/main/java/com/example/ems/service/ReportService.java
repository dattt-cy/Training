package com.example.ems.service;

import com.example.ems.entity.Department;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public ReportService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Cacheable("employeeReport")
    public Map<String, Object> getEmployeeReport() {
        log.info("Đang tính báo cáo nhân viên từ database");

        Map<String, Long> byDepartment = new LinkedHashMap<>();
        List<Department> departments = departmentRepository.findAll();
        for (Department d : departments) {
            byDepartment.put(d.getName(), employeeRepository.countByDepartmentId(d.getId()));
        }

        Map<String, Object> report = new LinkedHashMap<>();
        report.put("totalEmployees", employeeRepository.count());
        report.put("totalDepartments", departments.size());
        report.put("byDepartment", byDepartment);
        report.put("generatedAt", LocalDateTime.now());
        return report;
    }
}
