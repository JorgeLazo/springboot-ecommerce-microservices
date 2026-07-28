package com.jorgelazo.product.dto.response;

import com.jorgelazo.product.entity.ProductStatus;

public class ProductResponse {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private String brand;
    private String category;
    private Double price;
    private Integer stock;
    private ProductStatus status;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String sku, String name, String description, String brand, String category, Double price, Integer stock, ProductStatus status) {
        this.id = id;
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

    
}
