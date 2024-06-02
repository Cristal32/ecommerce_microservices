package com.ecommerce.feignclients.product;

public class ProductResponse {
	private Long id;
    private String name;
    private String image;
    private Integer price;
    private CategoryDTO category; 

    // Default constructor
    public ProductResponse() {
    }

    // Constructor with all fields
    public ProductResponse(Long id, String name, String image, Integer price, CategoryDTO category) {
        this.id = id;
    	this.name = name;
        this.image = image;
        this.price = price;
        this.category = category;
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

    // toString method
    @Override
    public String toString() {
        return "ProductResponse{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", category=" + category.getName() +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductResponse that = (ProductResponse) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }
}
