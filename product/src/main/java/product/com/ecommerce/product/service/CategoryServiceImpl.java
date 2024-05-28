package product.com.ecommerce.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.com.ecommerce.product.model.Category;
import product.com.ecommerce.product.repository.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryDao categoryDao;
	
	// ---------------------------- get all categorys ----------------------------
	@Override
	public List<Category> getAllCategories(){
		return categoryDao.findAll();
	}
	
	// ---------------------------- get a category by its id ----------------------------
    @Override
    public Category findCategoryById(Long id) {
        return categoryDao.findById(id).orElse(null);
    }
    
    // ---------------------------- register a category ----------------------------
    @Override
    public Category registerCategory(Category category) {
        return categoryDao.save(category);
    }
    
    // ---------------------------- delete a category ----------------------------
    @Override
    public void deleteCategory(Long id) {
        categoryDao.deleteById(id);
    }
}
