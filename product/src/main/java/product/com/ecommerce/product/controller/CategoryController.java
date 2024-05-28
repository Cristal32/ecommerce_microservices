package product.com.ecommerce.product.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import product.com.ecommerce.product.model.Category;
import product.com.ecommerce.product.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("api/category")
public class CategoryController {
	
	@Autowired
	private final CategoryService categoryService;
	
	// Constructor
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// ================================= GET Mapping =================================
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/getId/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        Category category = categoryService.findCategoryById(id);
        return ResponseEntity.ok(category);
    }
	
	// ================================= POST Mapping =================================
	
	@PostMapping("add")
	public ResponseEntity<Category> addCategory(@RequestBody Category category)  {
		Category newCategory = categoryService.registerCategory(category);
		return ResponseEntity.ok(newCategory);
    }
	
	// ================================= PUT Mapping =================================
	
	@PutMapping("/update")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category)  {
		Category updatedCategory = categoryService.registerCategory(category);
		return ResponseEntity.ok(updatedCategory);
    }
	
	// ================================= DELETE Mapping =================================
	@Transactional
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
