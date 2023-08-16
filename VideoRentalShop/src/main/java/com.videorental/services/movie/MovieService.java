package com.videorental.services.movie;

import com.videorental.controllers.movie.MovieForm;
import com.videorental.dtos.movie.MovieDto;
import com.videorental.entities.movie.Movie;
import com.videorental.queries.movie.MovieQueries;
import com.videorental.repositories.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MovieService {

    private final MovieQueries movieQueries;
    private final MovieRepository movieRepository;


    @Autowired
    public MovieService(MovieQueries movieQueries, MovieRepository movieRepository) {

        Assert.notNull(movieQueries, "movieQueries must not be null");
        Assert.notNull(movieRepository, "movieRepository");

        this.movieQueries = movieQueries;
        this.movieRepository = movieRepository;
    }


    public List<MovieDto> getAllMoviesDto() {

        return movieQueries.getAllMoviesDto();
    }

    public Movie getById(Long id) {

        Assert.notNull(id, "id must not be null");

        return movieQueries.getById(id);
    }

    public void deleteById(Long id) {

        Assert.notNull(id, "id must not be null");

        movieRepository.deleteById(id);
    }

    public void saveOrUpdateMovie(MovieForm movieForm) {

        Assert.notNull(movieForm, "movieForm must not be null");

        Movie movie = movieQueries.findById(movieForm.getId())
                .orElseGet(Movie::new);

        movie.setName(movieForm.getName());
        movie.setPrice(movieForm.getPrice());

        movieRepository.saveOrUpdate(movie);
    }
}
