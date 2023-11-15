package com.videorental.entities.movie;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Movie implements com.videorental.entities.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private LocalDate lastUpdateDate;

    @Column
    private boolean isOnSale;

    public Movie(String name, BigDecimal price, LocalDate lastUpdateDate, boolean isOnSale) {

        Assert.notNull(name, "name must not be null");
        Assert.notNull(price, "price must not be null");
        Assert.notNull(lastUpdateDate, "lastUpdateDate must not be null");
        Assert.notNull(isOnSale, "isOnSale must not be null");

        this.name = name;
        this.price = price;
        this.lastUpdateDate = lastUpdateDate;
        this.isOnSale = isOnSale;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id =id;
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

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return isOnSale == movie.isOnSale && Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(price, movie.price) && Objects.equals(lastUpdateDate, movie.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, lastUpdateDate, isOnSale);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", lastUpdateDate=" + lastUpdateDate +
                ", isOnSale=" + isOnSale +
                '}';
    }
}
