package com.jorgelazo.product.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private String name;

    private String description;

    private String brand;

    private String category;

    private Double price;

    private Integer stock;

    private ProductStatus status;

    public Product(){}

    public Product(String sku, String name, String description, String brand, String category, Double price, Integer stock, ProductStatus status) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public void updateStock(Integer quantity) {

        this.stock = quantity;

        if (this.stock == 0) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }

    }

    public void activate() {

        if (this.stock == null || this.stock <=0) {
            throw new IllegalStateException("Cannot activate product with zero or negative stock");
        }

        this.status = ProductStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = ProductStatus.INACTIVE;
    }

    public void reserveStock(Integer quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to reserve must be greater than zero");
        }

        if (this.stock < quantity) {
            throw new IllegalStateException("Insufficient stock");
        }
            

        this.stock -= quantity;

        if (this.stock == 0) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
    }

    public void releaseStock(Integer quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to release must be greater than zero");
        }

        this.stock += quantity;

        if (this.status == ProductStatus.OUT_OF_STOCK) {
            this.status = ProductStatus.ACTIVE;
        }
    }

    public void changePrice(Double newPrice) {

        if (newPrice <= 0) {
            throw new IllegalArgumentException("New price must be greater than zero");
        }

        this.price = newPrice;
    }

}