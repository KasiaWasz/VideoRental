package com.videorental.dtos.history.archivemovie;

import org.springframework.util.Assert;

import java.math.BigDecimal;

public class ArchiveMovieDto {

    private Long id;
    private Long movieId;
    private String name;
    private BigDecimal price;


    public ArchiveMovieDto(Long id, Long movieId, String name, BigDecimal price) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(movieId, "movieId must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(price, "price must not be null");

        this.id = id;
        this.movieId = movieId;
        this.name = name;
        this.price = price;
    }

    public ArchiveMovieDto() {
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
