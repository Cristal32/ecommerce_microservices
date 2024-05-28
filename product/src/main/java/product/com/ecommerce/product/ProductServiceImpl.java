package product.com.ecommerce.product;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import product.com.ecommerce.product.model.Category;

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

    // ---------------------------- getImage ----------------------------

    public byte[] getImage(String url) throws IOException{
        URL imageUrl = new URL(url);
        return imageUrl.openStream().readAllBytes();
    }

    // ---------------------------- load data from CSV ----------------------------

    public void loadProductDataFromCSV(){
        ClassPathResource resource = new ClassPathResource("products.csv");
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))){
            String[] lineInArray;
            reader.readNext();
            while ((lineInArray = reader.readNext()) != null){
                Product product = Product.builder()
                        .name(lineInArray[2])
                        .image(getImage(lineInArray[4]))
                        .price(Float.parseFloat(lineInArray[5]))
                        .description(lineInArray[3])
                        .build();
                productDao.save(product);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
