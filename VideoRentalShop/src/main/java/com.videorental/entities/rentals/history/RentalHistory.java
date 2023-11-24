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
    private LocalDate endRentDate;
    @Column
    private BigDecimal numberOfRentedDays;
    @Column
    private BigDecimal totalPrice;


    public RentalHistory(Long clientId,
         Long movieId,
         LocalDate startRentDate,
         LocalDate endRentDate,
         BigDecimal numberOfRentedDays,
         BigDecimal totalPrice) {

        Assert.notNull(clientId,"clientId should not be null");
        Assert.notNull(movieId, "movieId should not be null");
        Assert.notNull(startRentDate, "startRentDate should not be null");
        Assert.notNull(endRentDate, "endRentDate should not be null");
        Assert.notNull(numberOfRentedDays, "numberOfRentedDays must not be null");
        Assert.notNull(totalPrice, "totalPrice must not be null");

        this.clientId = clientId;
        this.movieId = movieId;
        this.startRentDate = startRentDate;
        this.endRentDate = endRentDate;
        this.numberOfRentedDays = numberOfRentedDays;
        this.totalPrice = totalPrice;
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

    public LocalDate getEndRentDate() {
        return endRentDate;
    }

    public void setEndRentDate(LocalDate endRentDate) {
        this.endRentDate = endRentDate;
    }

    public BigDecimal getNumberOfRentedDays() {
        return numberOfRentedDays;
    }

    public void setNumberOfRentedDays(BigDecimal numberOfRentedDays) {
        this.numberOfRentedDays = numberOfRentedDays;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalHistory that = (RentalHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(clientId, that.clientId) && Objects.equals(movieId, that.movieId) && Objects.equals(startRentDate, that.startRentDate) && Objects.equals(endRentDate, that.endRentDate) && Objects.equals(numberOfRentedDays, that.numberOfRentedDays) && Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, movieId, startRentDate, endRentDate, numberOfRentedDays, totalPrice);
    }

    @Override
    public String toString() {
        return "RentalHistory{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", movieId=" + movieId +
                ", startRentDate=" + startRentDate +
                ", endRentDate=" + endRentDate +
                ", numberOfRentedDays=" + numberOfRentedDays +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
