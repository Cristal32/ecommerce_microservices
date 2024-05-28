package product.com.ecommerce.product.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.com.ecommerce.product.RegisterProductRequest;
import product.com.ecommerce.product.model.Category;
import product.com.ecommerce.product.model.Product;
import product.com.ecommerce.product.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
    private ProductDao productDao;
	
	// ---------------------------- get all products ----------------------------
	@Override
	public List<Product> getAllProducts(){
		return productDao.findAll();
	}
	
	// ---------------------------- get a product by its id ----------------------------
    @Override
    public Product findProductById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    // ---------------------------- get a product by its category ----------------------------
    @Override
    public List<Product> findProductByCategory(Category category) {
        return productDao.findProductByCategory(category).orElse(null);
    }
    
    // ---------------------------- add a product ----------------------------
    
    @Override
    public Product registerProduct(RegisterProductRequest request) throws IOException {
        Product product = Product.builder()
                .name(request.name())
                .image(request.image().getBytes())
                .description(request.description())
                .price(request.price())
                .build();
        return productDao.saveAndFlush(product);
    }
    
    // ---------------------------- update a product ----------------------------
    @Override
    public Product updateProduct(Product product) {
        return productDao.save(product);
    }
    
    // ---------------------------- delete a product ----------------------------
    @Override
    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }

}
