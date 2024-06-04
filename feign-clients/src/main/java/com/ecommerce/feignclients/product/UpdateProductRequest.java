package com.ecommerce.feignclients.product;

import org.springframework.web.multipart.MultipartFile;

public record UpdateProductRequest (
		Long id,
        CategoryDTO category,
        String name,
        String description,
        MultipartFile image,
        Float price,
		Integer stockQuantity,
        Integer status) {
}
