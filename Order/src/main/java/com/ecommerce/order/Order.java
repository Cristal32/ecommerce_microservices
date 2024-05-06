package com.ecommerce.order;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_id_sequence", sequenceName = "order_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    private Integer id;
    private Date date;
    private String adresse;
    private String statut;

    public Order(){

    }

    public Order(Integer id, Date date, String adresse, String statut){
        this.id=id;
        this.date=date;
        this.adresse=adresse;
        this.statut=statut;
    }

    //Getters

    public Integer getId(){return this.id;}

    public Date getDate(){return this.date;}

    public String getAdresse(){return this.adresse;}

    public String getStatut(){return this.statut;}

    //Setters

    public void setId(Integer id){this.id = id;}

    public void setDate(Date date){this.date = date;}

    public void setAdresse(String adresse){this.adresse = adresse;}

    public void setStatut(String statut){this.statut = statut;}

    //toStringMethod
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", adresse='" + adresse + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }

    // Builder pattern static method
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private Integer id;
        private Date date;
        private String adresse;
        private String statut;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder adresse(String adresse) {
            this.adresse = adresse;
            return this;
        }

        public Builder statut(String statut) {
            this.statut = statut;
            return this;
        }

        public Order build() {
            return new Order(id, date, adresse, statut);
        }
    }

}