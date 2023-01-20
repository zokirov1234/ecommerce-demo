package com.company.controller;

import com.company.model.dto.checkout.CheckoutItemDTO;
import com.company.model.dto.checkout.StripeResponse;
import com.company.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Api(tags = "Order Controller")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(
            @RequestBody List<CheckoutItemDTO> checkoutItemDTOList
    ) throws StripeException {
        Session session = orderService.createSession(checkoutItemDTOList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());

        return ResponseEntity.ok().body(stripeResponse);
    }


}
