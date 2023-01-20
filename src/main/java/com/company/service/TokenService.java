package com.company.service;

import com.company.exp.BadRequestException;
import com.company.model.entity.TokenEntity;
import com.company.model.entity.UserEntity;
import com.company.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveToken(UserEntity user, String token) {
        tokenRepository.save(TokenEntity.builder()
                .token(token)
                .user(user)
                .build());
    }

    public String getToken(UserEntity user) {

        return tokenRepository.findByUser(user).getToken();
    }

    public UserEntity getUser(String token) {
        TokenEntity find = tokenRepository.findByToken(token);

        if (find == null) {
            throw new BadRequestException("Token does not exists");
        }

        if (find.getUser() == null) {
            throw new BadRequestException("Token not valid");
        }

        return find.getUser();
    }
}
