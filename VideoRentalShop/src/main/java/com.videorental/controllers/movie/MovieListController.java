package com.videorental.controllers.movie;

import com.videorental.dtos.movie.MovieDto;
import com.videorental.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/movie-list")
@Controller
class MovieListController {

    private static final String M_MOVIE_LIST = "movies";
    private static final String V_MOVIE_LIST = "movie-list-view";
    private final MovieService movieService;


    @Autowired
    private MovieListController(MovieService movieService) {

        Assert.notNull(movieService, "movieService must not be null");

        this.movieService = movieService;
    }


    @ModelAttribute(M_MOVIE_LIST)
    private List<MovieDto> getMovies() {

        return movieService.getAllActiveMoviesDto();
    }

    @GetMapping
    private String showMovieList() {

        return V_MOVIE_LIST;
    }
}
