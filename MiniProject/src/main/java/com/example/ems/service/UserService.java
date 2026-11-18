package com.example.ems.service;

import com.example.ems.entity.Role;
import com.example.ems.entity.User;
import com.example.ems.exception.BadRequestException;
import com.example.ems.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username.trim());
    }

    public User register(String username, String rawPassword) {
        return createUser(username, rawPassword, Role.USER);
    }

    public User createUser(String username, String rawPassword, Role role) {
        String trimmedUsername = username.trim();
        if (userRepository.existsByUsername(trimmedUsername)) {
            throw new BadRequestException("Tên đăng nhập '" + trimmedUsername + "' đã tồn tại");
        }

        User user = new User(trimmedUsername, passwordEncoder.encode(rawPassword), role);
        User saved = userRepository.save(user);
        log.info("Đã tạo tài khoản: username={}, role={}", saved.getUsername(), saved.getRole());
        return saved;
    }

    public User authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username.trim());
        if (user == null || !passwordEncoder.matches(rawPassword, user.getPassword())) {
            log.warn("Đăng nhập thất bại: username={}", username);
            throw new BadCredentialsException("Sai tên đăng nhập hoặc mật khẩu");
        }
        log.info("Đăng nhập API thành công: username={}", user.getUsername());
        return user;
    }
}
