package com.ecommerce.order.model;


public class ProductDTO {
	private Long id;
    private CategoryDTO category;
    private String name;
    private String description;
    private byte[] image;
    private float price;
    private int status;

    public ProductDTO() {}

    public ProductDTO(Long id, CategoryDTO category, String name, String description, byte[] image, float price, int status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.status = status;
    }

    // Getters & setters
    
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

    // category
    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    
    // status
    public int getStatus() {
    	return status;
    }
    
    public void setStatus(int status) {
    	this.status = status;
    }
}
