package com.videorental.queries.rentals;

import com.videorental.dtos.rentals.RentedMovieDto;
import com.videorental.entities.rentals.RentedMovie;

import java.util.List;
import java.util.Optional;

public interface RentedMovieQueries {

    RentedMovie getById(Long id);

    List<RentedMovie> getAll();

    Optional<RentedMovie> findById(Long id);

    List<RentedMovieDto> getAllRentedMoviesDto();

    List<RentedMovieDto> getMoviesByClientId(Long clientId);
}
