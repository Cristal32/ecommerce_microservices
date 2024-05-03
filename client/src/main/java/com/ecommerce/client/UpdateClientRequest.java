package com.ecommerce.client;

import org.springframework.web.multipart.MultipartFile;

public record UpdateClientRequest(
        String firstName,
        String lastName,
        String email
) {
}
