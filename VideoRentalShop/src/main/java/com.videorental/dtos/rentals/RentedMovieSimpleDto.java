package com.videorental.dtos.rentals;

import org.springframework.util.Assert;

import java.time.LocalDate;

public class RentedMovieSimpleDto {

    private Long id;
    private Long clientId;
    private Long movieId;
    private LocalDate rentDate;

    public RentedMovieSimpleDto(Long id, Long clientId, Long movieId, LocalDate rentDate) {

        Assert.notNull(id, "id must not be null");
        Assert.notNull(clientId,"clientId should not be null");
        Assert.notNull(movieId, "movieId should not be null");
        Assert.notNull(rentDate, "rentDate should not be null");

        this.id = id;
        this.clientId = clientId;
        this.movieId = movieId;
        this.rentDate = rentDate;
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }
}
