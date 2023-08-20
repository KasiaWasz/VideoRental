package com.videorental.controllers.rentals;

import org.springframework.util.Assert;

public class RentedMovieForm {

    static final String F_CLIENT_ID = "clientId";
    static final String F_MOVIE_ID = "movieId";
    static final String F_RENT_DATE = "rentDate";

    private Long id;
    private Long clientId;
    private Long movieId;
    private String rentDate;


    public RentedMovieForm(Long id, Long clientId, Long movieId, String rentDate) {

        Assert.notNull(clientId,"clientId should not be null");
        Assert.notNull(movieId, "movieId should not be null");
        Assert.notNull(rentDate, "rentDate should not be null");

        this.id = id;
        this.clientId = clientId;
        this.movieId = movieId;
        this.rentDate = rentDate;
    }


    public RentedMovieForm() {
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

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }
}
