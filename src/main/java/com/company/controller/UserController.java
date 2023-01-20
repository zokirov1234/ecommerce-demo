package com.company.controller;

import com.company.model.dto.user.SignInDTO;
import com.company.model.dto.user.SignUpDTO;
import com.company.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(
            @RequestBody SignUpDTO signUpDTO
    ) {
        String register = userService.register(signUpDTO);

        return ResponseEntity.ok(register);
    }


    @PostMapping("/signin")
    public ResponseEntity<String> login(
            @RequestBody SignInDTO signInDTO
    ) {

        String login = userService.login(signInDTO);

        return ResponseEntity.ok(login);
    }
}
