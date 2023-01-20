package com.company.service;

import com.company.exp.BadRequestException;
import com.company.model.dto.user.SignInDTO;
import com.company.model.dto.user.SignUpDTO;
import com.company.model.entity.UserEntity;
import com.company.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Transactional
    public String register(SignUpDTO signUpDTO) {
        UserEntity user = userRepository.findByEmail(signUpDTO.getEmail());

        if (user != null) {
            throw new BadRequestException("User already exists");
        }

        String encryptedPassword;

        try {
            encryptedPassword = hashPassword(signUpDTO.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        UserEntity user1 = userRepository.save(UserEntity.builder()
                .firstName(signUpDTO.getFirstName())
                .lastName(signUpDTO.getLastName())
                .email(signUpDTO.getEmail())
                .password(encryptedPassword)
                .build()
        );

        String token = UUID.randomUUID().toString();
        tokenService.saveToken(user1, token);

        return "Success";
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public String login(SignInDTO signInDTO) {

        UserEntity user = userRepository.findByEmail(signInDTO.getEmail());

        if (user == null) {
            throw new BadRequestException("User not registered");
        }

        try {
            if (!user.getPassword().equals(hashPassword(signInDTO.getPassword()))) {
                throw new BadRequestException("Wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return tokenService.getToken(user);
    }
}
