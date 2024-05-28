package product.com.ecommerce.product.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.opencsv.CSVReader;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
    
 // ---------------------------- get a product's image ----------------------------
    public byte[] getProductImageById(Long id) {
        Product product = productDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        return product.getImage();
    }
    
    // ---------------------------- add a product ----------------------------
    
    @Override
    public Product registerProduct(RegisterProductRequest request) throws IOException {
        Product product = Product.builder()
                .name(request.name())
                .image(request.image().getBytes())
                .description(request.description())
                .price(request.price())
                .category(request.category())
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
    public Product updateProduct(Long id, RegisterProductRequest request) {
        Optional<Product> existingProductOptional = productDao.findById(id);
        
        if (existingProductOptional.isPresent()) {
        	
            Product product = existingProductOptional.get();
            if (request.name() != null) { 
            	product.setName(request.name());}
            
            if (request.description() != null) {product.setDescription(request.description());}
            
            if (request.image() != null && !request.image().isEmpty()) {
                try {
					product.setImage(request.image().getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            
            if (request.price() != null) {product.setPrice(request.price());}
            if (request.category() != null) {product.setCategory(request.category());}
            
            return productDao.saveAndFlush(product);
        } else {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
    }
    
    // ---------------------------- delete a product ----------------------------
    @Override
    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }

}
