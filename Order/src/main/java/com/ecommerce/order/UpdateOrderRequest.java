package com.ecommerce.order;

import java.util.Date;

public record UpdateOrderRequest(
        Date date,
        String adresse,
        String statut
) {
}
