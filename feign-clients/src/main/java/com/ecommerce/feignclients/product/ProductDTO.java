package com.ecommerce.feignclients.product;

public class ProductDTO {
	private Long id;
    private String name;
    private String image;
    private Integer price;
    private CategoryDTO category;
    private Integer stockQuantity;

    // Default constructor
    public ProductDTO() {
    }

    // Constructor with all fields
    public ProductDTO(Long id, String name, String image, Integer price, CategoryDTO category, Integer stockQuantity) {
        this.id = id;
    	this.name = name;
        this.image = image;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    // Getters and setters ------------------------------------------------------
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

    // image
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // price
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    // category
    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    public Integer getStockQuantity(){ return stockQuantity;}
    public void setStockQuantity(Integer stockQuantity){ this.stockQuantity = stockQuantity;}
    // toString method
    @Override
    public String toString() {
        return "ProductResponse{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", category=" + category.getName() +
                ", stockQuantity=" + stockQuantity +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDTO that = (ProductDTO) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }
}
