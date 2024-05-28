package product.com.ecommerce.product.service;

import product.com.ecommerce.product.RegisterProductRequest;
import product.com.ecommerce.product.model.Category;
import product.com.ecommerce.product.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
	List<Product> getAllProducts();
    Product findProductById(Long id);
    public byte[] getProductImageById(Long id);
    List<Product> findProductByCategory(Category category);
    public Product registerProduct(RegisterProductRequest request) throws IOException;
    public Product updateProduct(Long id, RegisterProductRequest request);
    void deleteProduct(Long id);
}
