package com.videorental.queries.movie;

import com.videorental.dtos.movie.MovieDto;
import com.videorental.entities.movie.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieQueries {

    List<Movie> getAll();

    Movie getById(Long id);

    Optional<Movie> findById(Long id);

    List<MovieDto> getAllMoviesDto();

    Long getRandomMovieId();

    Movie getMovieOnSale();
}