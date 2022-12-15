package com.company.controller;

import com.company.model.dto.OrderReceiveDTO;
import com.company.model.dto.OrderResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/add")
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestBody OrderReceiveDTO orderReceiveDTO
    ){

    }
}
