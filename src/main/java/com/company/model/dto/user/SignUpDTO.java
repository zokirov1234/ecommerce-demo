package com.company.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
