package com.videorental.controllers.movie;

import com.videorental.entities.rentals.RentedMovie;
import com.videorental.services.rentals.RentedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class MovieIdValidator {

    private final RentedMovieService rentedMovieService;


    @Autowired
    public MovieIdValidator(RentedMovieService rentedMovieService) {

        Assert.notNull(rentedMovieService, "rentedMovieService must not be null");

        this.rentedMovieService = rentedMovieService;
    }


    public boolean isMovieRented(Long id) {

        Assert.notNull(id, "id must not be null");

        List<RentedMovie> rentals = rentedMovieService.getAll();

        return rentals.stream()
                .anyMatch(rental ->
                        rental.getMovieId().equals(id));
    }
}
