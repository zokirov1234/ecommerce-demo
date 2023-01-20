package com.company.repository;

import com.company.model.entity.TokenEntity;
import com.company.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

    TokenEntity findByUser(UserEntity user);
    TokenEntity findByToken(String user);
}
