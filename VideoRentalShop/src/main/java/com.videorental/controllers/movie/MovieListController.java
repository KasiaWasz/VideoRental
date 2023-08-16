package com.videorental.controllers.movie;

import com.videorental.dtos.employee.EmployeeDto;
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
public class MovieListController {

    private static final String M_MOVIE_LIST = "movies";
    private static final String V_MOVIE_LIST_VIEW = "movie-list-view";
    private final MovieService movieService;


    @Autowired
    public MovieListController(MovieService movieService) {

        Assert.notNull(movieService, "movieService must not be null");

        this.movieService = movieService;
    }


    @ModelAttribute(M_MOVIE_LIST)
    private List<MovieDto> getMovies() {

        return movieService.getAllMoviesDto();
    }

    @GetMapping
    private String showMovieList() {

        return V_MOVIE_LIST_VIEW;
    }
}
