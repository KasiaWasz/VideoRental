package com.videorental.queries.history.archivemovie;

import com.videorental.dtos.movie.MovieDto;
import com.videorental.entities.history.archivemovie.ArchiveMovie;

import java.util.List;
import java.util.Optional;

public interface ArchiveMovieQueries {

    ArchiveMovie getById(Long id);

    List<ArchiveMovie> getAll();

    Optional<ArchiveMovie> findById(Long id);

    List<MovieDto> getAllArchiveMoviesDto();
}
