package com.videorental.dtos.movie;

import org.springframework.util.Assert;

import java.math.BigDecimal;

public class MovieDto {

    private Long id;
    private String name;
    private BigDecimal price;


    public MovieDto(Long id, String name, BigDecimal price) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(price, "price must not be null");

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public MovieDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
