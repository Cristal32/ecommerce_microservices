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

}
