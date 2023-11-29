package com.videorental.dtos.history;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalHistoryDto {

    private Long id;
    private String clientFirstName;
    private String clientLastName;
    private String movieName;
    private LocalDate startRentDate;
    private LocalDate endRentDate;
    private BigDecimal numberOfRentedDays;
    private BigDecimal totalPrice;


    public RentalHistoryDto(Long id,
        String clientFirstName,
        String clientLastName,
        String movieName,
        LocalDate startRentDate,
        LocalDate endRentDate,
        BigDecimal numberOfRentedDays,
        BigDecimal totalPrice) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(clientFirstName,"clientFirstName should not be null");
        Assert.notNull(clientLastName,"clientLastName should not be null");
        Assert.notNull(movieName, "movieName should not be null");
        Assert.notNull(startRentDate, "startRentDate should not be null");
        Assert.notNull(endRentDate, "endRentDate must not be null");
        Assert.notNull(numberOfRentedDays, "numberOfRentedDays must not be null");
        Assert.notNull(totalPrice, "totalPrice must not be null");

        this.id = id;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.movieName = movieName;
        this.startRentDate = startRentDate;
        this.endRentDate = endRentDate;
        this.numberOfRentedDays = numberOfRentedDays;
        this.totalPrice = totalPrice;
    }


    public RentalHistoryDto() {
    }


    public Long getId() {
        return id;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getMovieName() {
        return movieName;
    }

    public LocalDate getStartRentDate() {
        return startRentDate;
    }

    public LocalDate getEndRentDate() {
        return endRentDate;
    }

    public BigDecimal getNumberOfRentedDays() {
        return numberOfRentedDays;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
