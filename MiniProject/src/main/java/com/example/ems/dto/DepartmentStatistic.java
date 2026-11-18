package com.example.ems.dto;

public class DepartmentStatistic {

    private String departmentName;
    private long employeeCount;
    private double averageSalary;

    public DepartmentStatistic(String departmentName, Long employeeCount, Double averageSalary) {
        this.departmentName = departmentName;
        this.employeeCount = employeeCount == null ? 0 : employeeCount;
        this.averageSalary = averageSalary == null ? 0 : averageSalary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public double getAverageSalary() {
        return averageSalary;
    }
}
