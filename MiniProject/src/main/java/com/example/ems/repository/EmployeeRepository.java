package com.example.ems.repository;

import com.example.ems.dto.DepartmentStatistic;
import com.example.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFullNameContainingIgnoreCase(String fullName);

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByFullNameContainingIgnoreCaseAndDepartmentId(String fullName, Long departmentId);

    long countByDepartmentId(Long departmentId);

    @Query("SELECT COUNT(e) FROM Employee e")
    long countAllEmployees();

    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double averageSalary();

    @Query("SELECT new com.example.ems.dto.DepartmentStatistic(d.name, COUNT(e), AVG(e.salary)) "
            + "FROM Department d LEFT JOIN Employee e ON e.department = d "
            + "GROUP BY d.id, d.name "
            + "ORDER BY COUNT(e) DESC, d.name")
    List<DepartmentStatistic> statisticByDepartment();
}
