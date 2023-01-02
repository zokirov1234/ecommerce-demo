package com.company.controller;

import com.company.model.dto.AuthDto;
import com.company.service.AuthServices;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth/public")
@Api(tags = "Auth Controller")
public class AuthController {

    private final AuthServices authService;
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody AuthDto authDto
    ){
        String register = authService.register(authDto);

        return ResponseEntity.ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody  AuthDto authDto
    ){

        String login = authService.login(authDto);

        return ResponseEntity.ok(login);
    }

}
