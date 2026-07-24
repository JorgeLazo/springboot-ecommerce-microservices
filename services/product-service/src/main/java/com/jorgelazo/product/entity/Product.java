package com.jorgelazo.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @Positive(message = "Price must be greater than zero")
    private Double price;

    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;

    public Product(){}

    public Product(String name, Double price, Integer stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Long getId(){ return id; }

    public String getName(){ return name; }

    public Double getPrice(){ return price; }

    public Integer getStock(){ return stock; }

    public void setName(String name){ this.name = name; }

    public void setPrice(Double price){ this.price = price; }

    public void setStock(Integer stock){ this.stock = stock; }
}