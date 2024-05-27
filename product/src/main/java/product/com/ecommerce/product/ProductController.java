package product.com.ecommerce.product;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product.com.ecommerce.product.model.Category;


@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private final ProductService productService;
	
	// Constructor
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	// ================================= GET Mapping =================================
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/getId/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

	@GetMapping("/getCategory/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") Category category){
		List<Product> products = productService.findProductByCategory(category);
		return ResponseEntity.ok(products);
	}
	
	// ================================= POST Mapping =================================
	
	@PostMapping("new")
	public Product registerProduct(@RequestBody RegisterProductRequest registerProductRequest) throws IOException {
        return productService.registerProduct(registerProductRequest);
    }
	
	// ================================= PUT Mapping =================================
	
	@PutMapping("/update")
	public Product updateProduct(@RequestBody RegisterProductRequest registerProductRequest) throws IOException {
		return productService.registerProduct(registerProductRequest);
	}
	
	// ================================= DELETE Mapping =================================
	@Transactional
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
