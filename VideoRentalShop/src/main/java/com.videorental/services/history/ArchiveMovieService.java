package com.videorental.services.history;

import com.videorental.controllers.movie.MovieForm;
import com.videorental.entities.history.archivemovie.ArchiveMovie;
import com.videorental.entities.movie.Movie;
import com.videorental.queries.history.archivemovie.ArchiveMovieQueries;
import com.videorental.repositories.history.archivemovie.ArchiveMovieRepository;
import com.videorental.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Service
public class ArchiveMovieService {

    private final ArchiveMovieQueries archiveMovieQueries;
    private final ArchiveMovieRepository archiveMovieRepository;


    @Autowired
    private ArchiveMovieService(
        ArchiveMovieQueries archiveMovieQueries,
        ArchiveMovieRepository archiveMovieRepository) {

        Assert.notNull(archiveMovieQueries, "archiveMovieQueries must not be null");
        Assert.notNull(archiveMovieRepository, "archiveMovieRepository must not be null");


        this.archiveMovieQueries = archiveMovieQueries;
        this.archiveMovieRepository = archiveMovieRepository;
    }


    public void deleteById(Long id) {

        Assert.notNull(id, "id must not be null");

        archiveMovieRepository.deleteById(id);
    }

    public void saveArchiveMovie(Movie movie) {

        Assert.notNull(movie, "movie must not be null");

       ArchiveMovie archiveMovie = new ArchiveMovie(
                movie.getId(),
                movie.getName(),
                movie.getPrice(),
                movie.getLastUpdateDate(),
                movie.isMovieActive()
                );

        archiveMovieRepository.saveOrUpdate(archiveMovie);
    }
}
