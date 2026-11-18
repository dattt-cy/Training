package com.example.ems.security;

import com.example.ems.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        if (!request.getRequestURI().startsWith("/api/")) {
            response.sendError(403);
            return;
        }

        ErrorResponse body = new ErrorResponse(403, "Forbidden",
                "Bạn không có quyền thực hiện thao tác này", request.getRequestURI());
        response.setStatus(403);
        response.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
