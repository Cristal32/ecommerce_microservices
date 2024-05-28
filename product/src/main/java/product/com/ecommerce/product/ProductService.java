package product.com.ecommerce.product;

import product.com.ecommerce.product.model.Category;

import java.io.IOException;
import java.util.List;

public interface ProductService {
	List<Product> getAllProducts();
    Product findProductById(Long id);
    List<Product> findProductByCategory(Category category);
    public Product registerProduct(RegisterProductRequest request) throws IOException;
    Product updateProduct(Product product);
    void deleteProduct(Long id);

    void loadProductDataFromCSV();
}
