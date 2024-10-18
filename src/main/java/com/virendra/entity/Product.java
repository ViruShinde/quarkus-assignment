package com.virendra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    public Long id;
    @NotNull
    @Size(min = 2, max = 30, message = "Name must be between 2 and 10 characters")
    private String name;
    @NotNull
    @Size(min = 2, max = 255, message = "Description must be between 2 and 255 characters")
    private String description;
    @NotNull
    @Range(min = (long) 100.0, max = (long) 200000.0, message = "Price must be between 100 and 2,00,000")
    private double price;
    @NotNull
    @Range(min = 1, max = 1000, message = "Quantity must be between 1 and 100")
    private int quantity;

    public Product(Long id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {}

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
