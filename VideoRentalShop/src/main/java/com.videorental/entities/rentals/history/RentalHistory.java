package com.videorental.entities.rentals.history;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class RentalHistory implements com.videorental.entities.Entity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long clientId;
    @Column
    private Long movieId;
    @Column
    private LocalDate startRentDate;
    @Column
    private BigDecimal numberOfRentedDays;


    public RentalHistory(Long clientId, Long movieId, LocalDate startRentDate, BigDecimal numberOfRentedDays) {

        Assert.notNull(clientId,"clientId should not be null");
        Assert.notNull(movieId, "movieId should not be null");
        Assert.notNull(startRentDate, "startRentDate should not be null");
        Assert.notNull(numberOfRentedDays, "numberOfRentedDays must not be null");

        this.clientId = clientId;
        this.movieId = movieId;
        this.startRentDate = startRentDate;
        this.numberOfRentedDays = numberOfRentedDays;
    }


    public RentalHistory() {
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public LocalDate getStartRentDate() {
        return startRentDate;
    }

    public void setStartRentDate(LocalDate startRentDate) {
        this.startRentDate = startRentDate;
    }

    public BigDecimal getNumberOfRentedDays() {
        return numberOfRentedDays;
    }

    public void setNumberOfRentedDays(BigDecimal numberOfRentedDays) {
        this.numberOfRentedDays = numberOfRentedDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalHistory that = (RentalHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(clientId, that.clientId) && Objects.equals(movieId, that.movieId) && Objects.equals(startRentDate, that.startRentDate) && Objects.equals(numberOfRentedDays, that.numberOfRentedDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, movieId, startRentDate, numberOfRentedDays);
    }

    @Override
    public String toString() {
        return "RentalHistory{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", movieId=" + movieId +
                ", startRentDate=" + startRentDate +
                ", numberOfRentedDays=" + numberOfRentedDays +
                '}';
    }
}
