package com.videorental.queries.history.archivemovie;

import com.videorental.dtos.history.archivemovie.ArchiveMovieDto;
import com.videorental.entities.history.archivemovie.ArchiveMovie;

import java.util.List;
import java.util.Optional;

public interface ArchiveMovieQueries {

    ArchiveMovie getById(Long id);

    ArchiveMovie getByMovieId(Long movieId);

    List<ArchiveMovie> getAll();

    Optional<ArchiveMovie> findById(Long id);

    List<ArchiveMovieDto> getAllArchiveMoviesDto();
}
