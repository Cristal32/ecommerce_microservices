package product.com.ecommerce.product;

import java.util.List;

public interface ProductService {
	List<Product> getAllProducts();
    Product findProductById(int id);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(int id);
}
