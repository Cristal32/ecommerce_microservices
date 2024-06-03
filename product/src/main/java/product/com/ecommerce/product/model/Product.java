package product.com.ecommerce.product.model;


import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2813298681406457792L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryId")
    private Category category;
    private String name;
    private String description;
    private byte[] image;
    private float price;
    private Integer stockQuantity;
    private int status; // 0 = available, 1 = sold

    public Product() {
    }

    public Product(Long id, Category category, String name, String description, byte[] image, float price, Integer stockQuantity, int status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = status;
    }
    
    // Getters & setters ---------------------------------------------------------------------
    
    // id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    // name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // image
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    // price
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    //stockQuantity
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


    // category
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    // status
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", status=" + status +
                '}';
    }
    
    // ProductBuilder ---------------------------------------------------------------------
    
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
    
    public static class ProductBuilder {
        private Long id;
        private Category category;
        private String name;
        private String description;
        private byte[] image;
        private Integer stockQuantity;
        private float price;
        private int status;

        private ProductBuilder() {}

        // Methods
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

        public ProductBuilder stockQuantity(Integer stockQuantity){
            this.stockQuantity = stockQuantity;
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
        
        public ProductBuilder status(int status) {
            this.status = status;
            return this;
        }

        public Product build() {
            return new Product(id, category, name, description, image, price, stockQuantity, status);
        }
    }
}
