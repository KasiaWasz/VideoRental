package com.videorental.dtos.rentals;

import org.springframework.util.Assert;

import java.time.LocalDate;

public class RentedMovieDto {

    private Long id;
    private String clientFirstName;
    private String clientLastName;
    private String movieName;
    private LocalDate rentDate;


    public RentedMovieDto(Long id,
        String clientFirstName,
        String clientLastName,
        String movieName,
        LocalDate rentDate) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(clientFirstName,"clientFirstName should not be null");
        Assert.notNull(clientLastName,"clientLastName should not be null");
        Assert.notNull(movieName, "movieName should not be null");
        Assert.notNull(rentDate, "rentDate should not be null");

        this.id = id;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.movieName = movieName;
        this.rentDate = rentDate;
    }


    public RentedMovieDto() {
    }

    public String getMovieName() {
        return movieName;
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

    public String getMovieId() {
        return movieName;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }
}
