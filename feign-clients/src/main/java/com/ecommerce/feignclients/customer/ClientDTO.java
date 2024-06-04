package com.ecommerce.feignclients.customer;

public class ClientDTO {
	private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String tel;
    private Long userId;
    
    // Constructors
    public ClientDTO() {}

    public ClientDTO(Long id, String firstName, String lastName, String email, String tel, Long userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tel = tel;
        this.userId = userId;
    }

    // Getters 
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    public String getTel(){ return tel;}

    // Setter methods

    public void setId(Long id) {
        this.id=id;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public void setEmail(String email) {
        this.email=email;
    }
    public void setTel(String tel){ this.tel = tel;}
    
    // userId
    public Long getUserId() { return userId; }
    
    public void setUserId(Long userId) { this.userId = userId; }

    // toString() method
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", userId=" + userId +
                '}';
    }
}
