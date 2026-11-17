package com.example.ems.config;

import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;

    public DataInitializer(DepartmentRepository departmentRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) {
        if (departmentRepository.count() > 0) {
            log.info("Database đã có dữ liệu, bỏ qua bước tạo dữ liệu mẫu");
            return;
        }

        log.info("Database trống, bắt đầu tạo dữ liệu mẫu");
        Department it = departmentRepository.save(new Department("IT"));
        Department hr = departmentRepository.save(new Department("Nhân sự"));
        Department accounting = departmentRepository.save(new Department("Kế toán"));

        employeeService.create(new Employee("Nguyễn Văn An", "an@gmail.com", 15000000.0, it));
        employeeService.create(new Employee("Trần Thị Bình", "binh@gmail.com", 12000000.0, hr));
        employeeService.create(new Employee("Lê Văn Cường", "cuong@gmail.com", 13000000.0, accounting));
        employeeService.create(new Employee("Phạm Thị Dung", "dung@gmail.com", 11000000.0, it));
        log.info("Đã tạo 3 phòng ban và 4 nhân viên mẫu");
    }
}
