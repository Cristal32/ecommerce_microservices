package product.com.ecommerce.product.service;

import java.util.List;

import product.com.ecommerce.product.model.Category;

public interface CategoryService {
	public List<Category> getAllCategories();
	public Category findCategoryById(Long id);
	public Category registerCategory(Category category);
	public Category updateCategory(Long id, Category category);
	public void deleteCategory(Long id);
}
