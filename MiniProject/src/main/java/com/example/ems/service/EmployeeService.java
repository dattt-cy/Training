package com.example.ems.service;

import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.exception.BadRequestException;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UtilityService utilityService;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           UtilityService utilityService) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
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
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new ResourceNotFoundException("Không tìm thấy nhân viên id = " + id);
        }
        return employee;
    }

    public Employee create(Employee employee) {
        employee.setId(null);
        employee.setFullName(utilityService.formatName(employee.getFullName()));
        employee.setDepartment(findDepartment(employee.getDepartment()));
        Employee saved = employeeRepository.save(employee);

        saved.setCode(utilityService.generateEmployeeCode(saved.getId()));
        return employeeRepository.save(saved);
    }

    public Employee update(Long id, Employee newData) {
        Employee existing = findById(id);
        existing.setFullName(utilityService.formatName(newData.getFullName()));
        existing.setEmail(newData.getEmail());
        existing.setSalary(newData.getSalary());
        existing.setDepartment(findDepartment(newData.getDepartment()));
        return employeeRepository.save(existing);
    }

    public void delete(Long id) {
        Employee existing = findById(id);
        employeeRepository.delete(existing);
    }

    private Department findDepartment(Department department) {
        if (department == null || department.getId() == null) {
            throw new BadRequestException("Phải chọn phòng ban (department.id)");
        }
        Department found = departmentRepository.findById(department.getId()).orElse(null);
        if (found == null) {
            throw new BadRequestException("Không tìm thấy phòng ban id = " + department.getId());
        }
        return found;
    }
}
