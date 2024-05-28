package product.com.ecommerce.product.model;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2813298681406457792L;
	
	@Id
    @SequenceGenerator( name = "product_id_sequence", sequenceName = "product_id_sequence" )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_id_sequence" )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryId")
    private Category category;
    private String name;
    private String description;
    private byte[] image;
    private float price;

    public Product() {
    }

    public Product(Long id, Category category, String name, String description, byte[] image, float price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategoryId(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
    
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
    
    public static class ProductBuilder {
        private Long id;
        private Category category;
        private String name;
        private String description;
        private byte[] image;
        private float price;

        private ProductBuilder() {
        }

        public ProductBuilder id(Long id) {
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

        public ProductBuilder image(byte[] image) {
            this.image = image;
            return this;
        }

        public ProductBuilder price(float price) {
            this.price = price;
            return this;
        }

        public ProductBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public Product build() {
            return new Product(id,category, name, description, image, price);
        }
    }
}
