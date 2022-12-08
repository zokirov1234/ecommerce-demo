package com.company.service;

import com.company.exp.BadRequestException;
import com.company.model.dto.RegisterDto;
import com.company.model.entity.UserEntity;
import com.company.model.enums.Role;
import com.company.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean register(final RegisterDto registerDto) {
        boolean username = userRepository.existsByUsername(registerDto.getUsername());
        if (username) {
            throw new BadRequestException("Username already exists");
        }

        userRepository.save(
                UserEntity.builder()
                        .username(registerDto.getUsername())
                        .role(Role.USER)
                        .password(passwordEncoder.encode(registerDto.getPassword()))
                        .build()
        );
        return true;
    }
}
