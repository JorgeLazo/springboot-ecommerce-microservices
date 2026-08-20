package com.jorgelazo.cart.client.dto;


public class ProductDto {

    private Long id;
    private String name;
    private Double price;
    private Integer stock;

    public ProductDto() {
    }

    public ProductDto(Long id,
                      String name,
                      Double price,
                      Integer stock) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}