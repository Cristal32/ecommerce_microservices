package product.com.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Product findProductById(int id) {
        return productDao.findById(id).orElse(null);
    }
    
    // ---------------------------- add a product ----------------------------
    
    @Override
    public Product addProduct(Product product) {
        return productDao.save(product);
    }
    
    // ---------------------------- update a product ----------------------------
    @Override
    public Product updateProduct(Product product) {
        return productDao.save(product);
    }
    
    // ---------------------------- delete a product ----------------------------
    @Override
    public void deleteProduct(int id) {
        productDao.deleteById(id);
    }

}
