package com.example.ems.service;

import com.example.ems.entity.Department;
import com.example.ems.exception.BadRequestException;
import com.example.ems.exception.ResourceNotFoundException;
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
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new ResourceNotFoundException("Không tìm thấy phòng ban id = " + id);
        }
        return department;
    }

    public Department create(String name) {
        String trimmedName = name.trim();
        if (departmentRepository.existsByNameIgnoreCase(trimmedName)) {
            throw new BadRequestException("Phòng ban '" + trimmedName + "' đã tồn tại");
        }
        return departmentRepository.save(new Department(trimmedName));
    }
}
