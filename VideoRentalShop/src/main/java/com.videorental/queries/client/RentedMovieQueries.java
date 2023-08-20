package com.videorental.queries.client;

import com.videorental.dtos.client.RentedMovieDto;
import com.videorental.entities.client.RentedMovie;

import java.util.List;
import java.util.Optional;

public interface RentedMovieQueries {

    RentedMovie getById(Long id);

    List<RentedMovie> getAll();

    Optional<RentedMovie> findById(Long id);

    List<RentedMovieDto> getAllRentedMoviesDto();

    List<RentedMovieDto> getMoviesByClientId(Long clientId);
}
