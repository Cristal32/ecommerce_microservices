package product.com.ecommerce.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import product.com.ecommerce.product.model.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
	Optional<Category> findCategoryById(Long id);
}
