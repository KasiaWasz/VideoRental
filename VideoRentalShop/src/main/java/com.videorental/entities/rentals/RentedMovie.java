package com.videorental.entities.rentals;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class RentedMovie implements com.videorental.entities.Entity{

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long clientId;
    @Column
    private Long movieId;
    @Column
    private LocalDate rentDate;


    public RentedMovie(Long clientId, Long movieId, LocalDate rentDate) {

        Assert.notNull(clientId,"clientId should not be null");
        Assert.notNull(movieId, "movieId should not be null");
        Assert.notNull(rentDate, "rentDate should not be null");

        this.clientId = clientId;
        this.movieId = movieId;
        this.rentDate = rentDate;
    }


    public RentedMovie() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentedMovie that = (RentedMovie) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(movieId, that.movieId) && Objects.equals(rentDate, that.rentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, movieId, rentDate);
    }

    @Override
    public String toString() {
        return "RentedMovie{" +
                "clientId=" + clientId +
                ", movieId=" + movieId +
                ", rentDate=" + rentDate +
                '}';
    }
}
