package com.ecommerce.order;

import java.util.Date;

public record OrderRegistrationRequest(Date date, String adresse, String statut) {
}