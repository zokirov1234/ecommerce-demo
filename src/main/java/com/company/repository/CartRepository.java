package com.company.repository;

import com.company.model.entity.Cart;
import com.company.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedAtDesc(UserEntity user);
}
