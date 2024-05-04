package com.ecommerce.order;

import java.util.Date;

public record RegisterOrderRequest(Date date, String adresse, String statut) {
}