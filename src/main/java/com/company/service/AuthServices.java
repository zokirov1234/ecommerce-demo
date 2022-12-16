package com.company.service;

import com.company.exp.BadRequestException;
import com.company.model.dto.AuthDto;
import com.company.model.entity.Role;
import com.company.model.entity.UserEntity;
import com.company.model.enums.RoleEnum;
import com.company.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(final AuthDto authDto) {
        boolean username = userRepository.existsByUsername(authDto.getUsername());
        if (username) {
            throw new BadRequestException("Username already exists");
        }


        userRepository.save(
                UserEntity.builder()
                        .username(authDto.getUsername())
                        .roleEntityList(List.of(new Role(1,RoleEnum.ADMIN)))
                        .password(passwordEncoder.encode(authDto.getPassword()))
                        .build()
        );
        return "success";
    }

    public String login(AuthDto authDto) {

        Optional<UserEntity> user = userRepository.findByUsername(authDto.getUsername());

        if (user.isEmpty()) {
            throw new BadRequestException("username not found");
        }

        if (!passwordEncoder.matches(authDto.getPassword(), user.get().getPassword())) {
            throw new BadRequestException("password wrong");
        }
        return "success";
    }
}
