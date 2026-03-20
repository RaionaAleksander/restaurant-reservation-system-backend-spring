package com.restaurant.reservation.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.reservation.user.dto.AuthResponseDTO;
import com.restaurant.reservation.user.dto.LoginRequestDTO;
import com.restaurant.reservation.user.dto.UserRequestDTO;
import com.restaurant.reservation.user.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody UserRequestDTO dto) {
        String token = userService.register(dto);

        return ResponseEntity.ok(
                AuthResponseDTO.builder()
                        .token(token)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {

        String token = userService.login(dto);

        return ResponseEntity.ok(
                AuthResponseDTO.builder()
                        .token(token)
                        .build());
    }
}