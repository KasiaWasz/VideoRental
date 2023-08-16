package com.videorental.controllers.movie;

import org.springframework.util.Assert;

import java.math.BigDecimal;

public class MovieForm {

    static final String F_NAME = "name";
    static final String F_PRICE = "price";

    private Long id;
    private String name;
    private BigDecimal price;


    public MovieForm(Long id, String name, BigDecimal price) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(price, "price must not be null");

        this.id = id;
        this.name = name;
        this.price = price;
    }


    public MovieForm() {
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
