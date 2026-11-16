package com.example.ems.controller;

import com.example.ems.entity.Department;
import com.example.ems.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department) {
        String name = department.getName();
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Tên phòng ban không được để trống");
        }
        if (departmentService.existsByName(name.trim())) {
            return ResponseEntity.badRequest().body("Phòng ban đã tồn tại");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.create(name));
    }
}
