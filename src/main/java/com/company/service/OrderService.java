package com.company.service;

import com.company.model.dto.checkout.CheckoutItemDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    public Session createSession(List<CheckoutItemDTO> checkoutItemDTOList) throws StripeException {

        String stripeSecretKey = "sk_test_51MReq8I5jKqYUmZvx7Uq4XGkQKJUNhRo6h5APeMY7t2VF2UmATzYnArQSFNXfu5DK8hW7XqfTJu6JJL2PtVGTFbv00WArfAaz4";
        String baseUrl = "http://localhost:8080/";

        String successUrl = baseUrl + "payment/success";
        String failureUrl = baseUrl + "payment/failed";

        Stripe.apiKey = stripeSecretKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        for (CheckoutItemDTO checkoutItemDTO : checkoutItemDTOList) {
            sessionItemList.add(createSessionLineItem(checkoutItemDTO));
        }

        SessionCreateParams sessionCreateParams = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureUrl)
                .addAllLineItem(sessionItemList)
                .setSuccessUrl(successUrl)
                .build();

        return Session.create(sessionCreateParams);

    }

    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDTO checkoutItemDTO) {

        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkoutItemDTO))
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDTO.getQuantity())))
                .build();
    }

    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDTO checkoutItemDTO) {

        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long) (checkoutItemDTO.getPrice() * 100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDTO.getProductName())
                                .build()
                ).build();
    }
}
