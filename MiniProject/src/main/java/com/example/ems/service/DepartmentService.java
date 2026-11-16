package com.example.ems.service;

import com.example.ems.entity.Department;
import com.example.ems.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public boolean existsByName(String name) {
        return departmentRepository.existsByNameIgnoreCase(name);
    }

    public Department create(String name) {
        return departmentRepository.save(new Department(name.trim()));
    }
}
