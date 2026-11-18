package com.example.ems.dto;

public class AuthResponse {

    private String token;
    private String tokenType;
    private long expiresIn;
    private String username;
    private String role;

    public AuthResponse(String token, long expiresIn, String username, String role) {
        this.token = token;
        this.tokenType = "Bearer";
        this.expiresIn = expiresIn;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
