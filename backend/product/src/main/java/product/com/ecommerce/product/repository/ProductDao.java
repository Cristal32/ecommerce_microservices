package product.com.ecommerce.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.com.ecommerce.product.model.Category;
import product.com.ecommerce.product.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
	Optional<Product> findProductById(Long id);
	Optional<List<Product>> findProductByCategory(Category category);
}
