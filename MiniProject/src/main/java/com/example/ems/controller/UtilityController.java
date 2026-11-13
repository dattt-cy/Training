package com.example.ems.controller;

import com.example.ems.service.UtilityService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/utility")
public class UtilityController {

    private final UtilityService utilityService;
    private final PasswordEncoder passwordEncoder;

    public UtilityController(UtilityService utilityService, PasswordEncoder passwordEncoder) {
        this.utilityService = utilityService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/format-name")
    public Map<String, String> formatName(@RequestParam String name) {
        Map<String, String> result = new HashMap<>();
        result.put("input", name);
        result.put("formatted", utilityService.formatName(name));
        return result;
    }

    @GetMapping("/employee-code")
    public Map<String, String> generateEmployeeCode() {
        Map<String, String> result = new HashMap<>();
        result.put("employeeCode", utilityService.generateEmployeeCode());
        return result;
    }

    @GetMapping("/encode-password")
    public Map<String, Object> encodePassword(@RequestParam String password) {
        String encoded = passwordEncoder.encode(password);
        Map<String, Object> result = new HashMap<>();
        result.put("rawPassword", password);
        result.put("encodedPassword", encoded);
        result.put("matches", passwordEncoder.matches(password, encoded));
        return result;
    }
}
