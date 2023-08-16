package com.videorental.queries.movie;

import com.videorental.dtos.movie.MovieDto;
import com.videorental.entities.movie.Movie;
import com.videorental.queries.AbstractQueries;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class MovieQueriesImpl extends AbstractQueries<Movie> implements MovieQueries  {


   MovieQueriesImpl(SessionFactory sessionFactory) {

        super(sessionFactory, Movie.class);
    }


    public List<MovieDto> getAllMoviesDto() {

       return getAll().stream()
               .map(movie -> new MovieDto(
                       movie.getId(),
                       movie.getName(),
                       movie.getPrice()
               ))
               .toList();
    }
}
