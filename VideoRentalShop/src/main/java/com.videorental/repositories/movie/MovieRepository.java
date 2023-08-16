package com.videorental.repositories.movie;

import com.videorental.entities.movie.Movie;

public interface MovieRepository {

    void saveOrUpdate(Movie movie);

    void deleteById(Long id);
}
