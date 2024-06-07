package com.ecommerce.payment;

import java.time.LocalDateTime;

public record RegisterPaymentRequest(
        Long clientId,
        Long orderId,
        LocalDateTime createdAt
) {
}



