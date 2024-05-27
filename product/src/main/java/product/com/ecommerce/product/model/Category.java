package product.com.ecommerce.product.model;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @SequenceGenerator( name = "product_id_sequence", sequenceName = "product_id_sequence" )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_id_sequence" )
    private Long id;

    private String name;
    private String description;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
