package product.com.ecommerce.product;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2813298681406457792L;
	
	@Id
    @SequenceGenerator( name = "product_id_sequence", sequenceName = "product_id_sequence" )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_id_sequence" )
    private int id;
    private String name;
    private String description;
    private String image;
    private float price;

    public Product() {
    }

    public Product(int id, String name, String description, String image, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
    
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
    
    public static class ProductBuilder {
        private int id;
        private String name;
        private String description;
        private String image;
        private float price;

        private ProductBuilder() {
        }

        public ProductBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder image(String image) {
            this.image = image;
            return this;
        }

        public ProductBuilder price(float price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(id, name, description, image, price);
        }
    }
}
