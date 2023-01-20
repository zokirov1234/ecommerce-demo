package com.company.repository;

import com.company.model.entity.UserEntity;
import com.company.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Integer> {

    List<WishList> findByUserOrderByCreatedAtDesc(UserEntity user);
}
