package com.example.ems.repository;

import com.example.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFullNameContainingIgnoreCase(String fullName);

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByFullNameContainingIgnoreCaseAndDepartmentId(String fullName, Long departmentId);

    long countByDepartmentId(Long departmentId);
}
