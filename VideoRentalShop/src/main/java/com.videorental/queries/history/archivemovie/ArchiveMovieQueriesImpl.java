package com.videorental.queries.history.archivemovie;

import com.videorental.dtos.movie.MovieDto;
import com.videorental.entities.history.archivemovie.ArchiveMovie;
import com.videorental.queries.AbstractQueries;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ArchiveMovieQueriesImpl extends AbstractQueries<ArchiveMovie> implements ArchiveMovieQueries {


    @Autowired
    ArchiveMovieQueriesImpl(SessionFactory sessionFactory) {

        super(sessionFactory, ArchiveMovie.class);
    }


    @Override
    public List<MovieDto> getAllArchiveMoviesDto() {

        return getAll().stream()
                .map(movie -> new MovieDto(
                        movie.getId(),
                        movie.getName(),
                        movie.getPrice()))
                .toList();
    }
}
