package com.example.ems.service;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

    private int employeeCounter = 0;

    public String formatName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "";
        }
        String[] words = name.trim().toLowerCase().split("\\s+");
        String result = "";
        for (String word : words) {
            result += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
        }
        return result.trim();
    }

    public synchronized String generateEmployeeCode() {
        employeeCounter++;
        return String.format("NV%04d", employeeCounter);
    }
}
