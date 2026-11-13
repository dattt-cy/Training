package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll(@RequestParam(required = false) String department) {
        if (department == null || department.trim().isEmpty()) {
            return employeeService.findAll();
        }
        return employeeService.findByDepartment(department.trim());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        if (employee.getFullName() == null || employee.getFullName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Họ tên không được để trống");
        }
        if (employee.getDepartment() == null || employee.getDepartment().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Phòng ban không được để trống");
        }
        Employee created = employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
