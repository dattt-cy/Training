package com.example.ems.controller;

import com.example.ems.dto.RegisterRequest;
import com.example.ems.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthWebController {

    private final UserService userService;

    public AuthWebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/employees/list";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerRequest") RegisterRequest request,
                           BindingResult bindingResult) {
        if (request.getPassword() != null && !request.getPassword().equals(request.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "mismatch", "Mật khẩu nhập lại không khớp");
        }
        if (request.getUsername() != null && userService.existsByUsername(request.getUsername())) {
            bindingResult.rejectValue("username", "duplicate", "Tên đăng nhập đã tồn tại");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.register(request.getUsername(), request.getPassword());
        return "redirect:/login?registered";
    }
}
