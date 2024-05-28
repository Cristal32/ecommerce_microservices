package product.com.ecommerce.product.service;

import java.util.List;

import product.com.ecommerce.product.model.Category;

public interface CategoryService {
	public List<Category> getAllCategories();
	public Category findCategoryById(Long id);
	public Category registerCategory(Category category);
	public void deleteCategory(Long id);
}
