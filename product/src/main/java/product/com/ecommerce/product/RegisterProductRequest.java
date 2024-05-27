package product.com.ecommerce.product;

import org.springframework.web.multipart.MultipartFile;
import product.com.ecommerce.product.model.Category;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public record RegisterProductRequest (Long id,
        Category category,
        String name,
        String description,
        MultipartFile image,
        float price) {
}


