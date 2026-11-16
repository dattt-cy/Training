package com.example.ems.service;

import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UtilityService utilityService;

    public EmployeeService(EmployeeRepository employeeRepository, UtilityService utilityService) {
        this.employeeRepository = employeeRepository;
        this.utilityService = utilityService;
    }

    public List<Employee> search(String keyword, Long departmentId) {
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

        if (hasKeyword && departmentId != null) {
            return employeeRepository.findByFullNameContainingIgnoreCaseAndDepartmentId(keyword.trim(), departmentId);
        }
        if (hasKeyword) {
            return employeeRepository.findByFullNameContainingIgnoreCase(keyword.trim());
        }
        if (departmentId != null) {
            return employeeRepository.findByDepartmentId(departmentId);
        }
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee create(Employee employee) {
        employee.setId(null);
        employee.setFullName(utilityService.formatName(employee.getFullName()));
        Employee saved = employeeRepository.save(employee);

        saved.setCode(utilityService.generateEmployeeCode(saved.getId()));
        return employeeRepository.save(saved);
    }

    public Employee update(Employee existing, Employee newData) {
        existing.setFullName(utilityService.formatName(newData.getFullName()));
        existing.setEmail(newData.getEmail());
        existing.setSalary(newData.getSalary());
        existing.setDepartment(newData.getDepartment());
        return employeeRepository.save(existing);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
