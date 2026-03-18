package com.restaurant.reservation.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant.reservation.user.dto.UserRequestDTO;
import com.restaurant.reservation.user.model.Role;
import com.restaurant.reservation.user.model.User;
import com.restaurant.reservation.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRequestDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        validatePassword(dto.getPassword());

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .role(Role.ROLE_CLIENT)
                .build();

        return userRepository.save(user);
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        String pattern = "^(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@$!%*?&])" +
                ".{6,24}$";

        if (!password.matches(pattern)) {
            throw new IllegalArgumentException(
                    "Password must be 6-24 chars, include upper, lower, digit and special character");
        }
    }
}