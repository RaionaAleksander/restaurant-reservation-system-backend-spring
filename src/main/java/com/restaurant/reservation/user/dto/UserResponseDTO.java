package com.restaurant.reservation.user.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {
    private Long id;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
}