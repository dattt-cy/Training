package com.example.ems.controller;

import com.example.ems.dto.DepartmentStatistic;
import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.service.DepartmentService;
import com.example.ems.service.EmployeeService;
import com.example.ems.service.StatisticService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final StatisticService statisticService;

    public EmployeeWebController(EmployeeService employeeService,
                                 DepartmentService departmentService,
                                 StatisticService statisticService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.statisticService = statisticService;
    }

    @GetMapping("/statistics")
    public String statistics(Model model) {
        List<DepartmentStatistic> byDepartment = statisticService.getStatisticByDepartment();

        List<String> chartLabels = new ArrayList<>();
        List<Long> chartData = new ArrayList<>();
        for (DepartmentStatistic s : byDepartment) {
            chartLabels.add(s.getDepartmentName());
            chartData.add(s.getEmployeeCount());
        }

        model.addAttribute("totalEmployees", statisticService.getTotalEmployees());
        model.addAttribute("totalDepartments", statisticService.getTotalDepartments());
        model.addAttribute("averageSalary", statisticService.getAverageSalary());
        model.addAttribute("byDepartment", byDepartment);
        model.addAttribute("chartLabels", chartLabels);
        model.addAttribute("chartData", chartData);
        return "employees/statistics";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("employees", employeeService.search(null, null));
        return "employees/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Employee employee = new Employee();
        employee.setDepartment(new Department());
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.findAll());
        return "employees/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("employee") Employee employee,
                      BindingResult bindingResult,
                      Model model,
                      RedirectAttributes redirectAttributes) {
        if (employee.getDepartment() == null || employee.getDepartment().getId() == null) {
            bindingResult.rejectValue("department", "required", "Phải chọn phòng ban");
        }

        if (bindingResult.hasErrors()) {
            if (employee.getDepartment() == null) {
                employee.setDepartment(new Department());
            }
            model.addAttribute("departments", departmentService.findAll());
            return "employees/add";
        }

        Employee created = employeeService.create(employee);
        redirectAttributes.addFlashAttribute("message",
                "Đã thêm nhân viên " + created.getFullName() + " (" + created.getCode() + ")");
        return "redirect:/employees/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword,
                         @RequestParam(required = false) Long departmentId,
                         Model model) {
        List<Employee> employees = employeeService.search(keyword, departmentId);
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("departmentId", departmentId);
        return "employees/search";
    }
}
