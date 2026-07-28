package com.jorgelazo.product.dto.request;

import com.jorgelazo.product.entity.ProductStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductRequest {

    @NotBlank
    private String sku;

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Price must be greater than zero")
    private Double price;

    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;

    private ProductStatus status;

    public ProductRequest() {
    }

    public ProductRequest(String sku, String name, String description, String brand, String category, Double price, Integer stock, ProductStatus status) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = status;
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
