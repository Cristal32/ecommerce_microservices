package product.com.ecommerce.product.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import product.com.ecommerce.product.RegisterProductRequest;
import product.com.ecommerce.product.UpdateProductRequest;
import product.com.ecommerce.product.model.Category;
import product.com.ecommerce.product.model.Product;
import product.com.ecommerce.product.service.CategoryService;
import product.com.ecommerce.product.service.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private final ProductService productService;
	
	@Autowired
	private final CategoryService categoryService;
	
	// Constructor
	public ProductController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}
	
	// ================================= GET Mapping =================================
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

	@GetMapping("/getByCategory/{categoryId}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("categoryId") Long categoryId){
		Category category = categoryService.findCategoryById(categoryId);
		List<Product> products = productService.findProductByCategory(category);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/getImage/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        byte[] imageBytes = productService.getProductImageById(id);

        if (imageBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);  // Or the appropriate image type
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	// ================================= POST Mapping =================================
	
	@PostMapping("add")
	public Product registerProduct(@ModelAttribute RegisterProductRequest registerProductRequest) throws IOException {
        return productService.registerProduct(registerProductRequest);
    }
	
	// ================================= PUT Mapping =================================
	
	@PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @ModelAttribute UpdateProductRequest updateProductRequest) throws IOException {
        return productService.updateProduct(id, updateProductRequest);
    }
	
	@PutMapping("/updateStockQuantity/{id}")
	public Product updateProductStockQuantity(
	        @PathVariable Long id,
	        @RequestParam(value = "stockQuantity", required = false) Integer stockQuantity
	) {
	    UpdateProductRequest updateProductRequest = new UpdateProductRequest(null, null, null, null, null, null, stockQuantity, null);
	    return productService.updateProduct(id, updateProductRequest);
	}
	
	// ================================= DELETE Mapping =================================
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }
}
