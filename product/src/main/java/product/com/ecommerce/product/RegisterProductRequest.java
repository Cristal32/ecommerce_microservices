package product.com.ecommerce.product;

import org.springframework.web.multipart.MultipartFile;
import product.com.ecommerce.product.model.Category;

public record RegisterProductRequest (Long id,
        Category category,
        String name,
        String description,
        MultipartFile image,
        Float price) {
}


