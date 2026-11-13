package com.example.ems.service;

import com.example.ems.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private Long nextId = 1L;

    private final UtilityService utilityService;

    public EmployeeService(UtilityService utilityService) {
        this.utilityService = utilityService;

        create(new Employee("Nguyễn Văn An", "an@gmail.com", "IT", 15000000.0));
        create(new Employee("Trần Thị Bình", "binh@gmail.com", "Nhân sự", 12000000.0));
        create(new Employee("Lê Văn Cường", "cuong@gmail.com", "Kế toán", 13000000.0));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public List<Employee> findByDepartment(String department) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getDepartment().equalsIgnoreCase(department)) {
                result.add(e);
            }
        }
        return result;
    }

    public Employee findById(Long id) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public synchronized Employee create(Employee employee) {
        employee.setId(nextId);
        nextId++;
        employee.setCode(utilityService.generateEmployeeCode());
        employee.setFullName(utilityService.formatName(employee.getFullName()));
        employees.add(employee);
        return employee;
    }
}
