package com.videorental.entities.history.archivemovie;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ArchiveMovie implements com.videorental.entities.Entity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long movieId;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private LocalDate lastUpdateDate;

    @Column
    private boolean isMovieActive;


    public ArchiveMovie(Long movieId, String name, BigDecimal price, LocalDate lastUpdateDate, boolean isMovieActive) {

        Assert.notNull(movieId, "movieId must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(price, "price must not be null");
        Assert.notNull(lastUpdateDate, "lastUpdateDate must not be null");

        this.movieId = movieId;
        this.name = name;
        this.price = price;
        this.lastUpdateDate = lastUpdateDate;
        this.isMovieActive = isMovieActive;
    }


    public ArchiveMovie() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
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

    public boolean isMovieActive() {
        return isMovieActive;
    }

    public void setMovieActive(boolean movieActive) {
        isMovieActive = movieActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiveMovie that = (ArchiveMovie) o;
        return isMovieActive == that.isMovieActive && Objects.equals(id, that.id) && Objects.equals(movieId, that.movieId) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(lastUpdateDate, that.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, name, price, lastUpdateDate, isMovieActive);
    }

    @Override
    public String toString() {
        return "ArchiveMovie{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", lastUpdateDate=" + lastUpdateDate +
                ", isMovieActive=" + isMovieActive +
                '}';
    }
}
