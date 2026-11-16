package com.example.ems.controller;

import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.service.DepartmentService;
import com.example.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Employee> search(@RequestParam(required = false) String keyword,
                                 @RequestParam(required = false) Long departmentId) {
        return employeeService.search(keyword, departmentId);
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
        String error = validate(employee);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Employee created = employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee employee) {
        Employee existing = employeeService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        String error = validate(employee);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(employeeService.update(existing, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (employeeService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private String validate(Employee employee) {
        if (employee.getFullName() == null || employee.getFullName().trim().isEmpty()) {
            return "Họ tên không được để trống";
        }
        if (employee.getDepartment() == null || employee.getDepartment().getId() == null) {
            return "Phải chọn phòng ban (department.id)";
        }
        Department department = departmentService.findById(employee.getDepartment().getId());
        if (department == null) {
            return "Không tìm thấy phòng ban id = " + employee.getDepartment().getId();
        }
        employee.setDepartment(department);
        return null;
    }
}
