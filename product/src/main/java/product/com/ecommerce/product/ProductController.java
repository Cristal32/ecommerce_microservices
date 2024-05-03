package product.com.ecommerce.product;

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
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }
	
	// ================================= POST Mapping =================================
	
	@PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
	
	// ================================= PUT Mapping =================================
	
	@PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }
	
	// ================================= DELETE Mapping =================================
	@Transactional
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
