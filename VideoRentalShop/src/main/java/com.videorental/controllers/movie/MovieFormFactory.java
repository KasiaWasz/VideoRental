package com.videorental.controllers.movie;

import com.videorental.entities.movie.Movie;
import com.videorental.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class MovieFormFactory {

    private final MovieService movieService;


    @Autowired
    MovieFormFactory(MovieService movieService) {

        Assert.notNull(movieService, "movieService must not be null");

        this.movieService = movieService;
    }


    MovieForm create(Long id) {

        Assert.notNull(id, "id must not be null");

        Movie movie = movieService.getById(id);

        return new MovieForm(
                movie.getId(),
                movie.getName(),
                movie.getPrice()
        );
    }
}
