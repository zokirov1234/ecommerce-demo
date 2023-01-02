package com.company.repository;

import com.company.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCartId(int cartId);

    boolean deleteById(int id);
}
