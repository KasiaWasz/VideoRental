package com.videorental.services.history.archivemovie;

import com.videorental.dtos.history.archivemovie.ArchiveMovieDto;
import com.videorental.entities.history.archivemovie.ArchiveMovie;
import com.videorental.entities.movie.Movie;
import com.videorental.queries.history.archivemovie.ArchiveMovieQueries;
import com.videorental.repositories.history.archivemovie.ArchiveMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

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


    public List<ArchiveMovieDto> getAllArchiveMoviesDto() {

        return archiveMovieQueries.getAllArchiveMoviesDto();
    }

    public void deleteById(Long id) {

        Assert.notNull(id, "id must not be null");

        archiveMovieRepository.deleteById(id);
    }

    public void deleteByMovieId(Long movieId) {

        Assert.notNull(movieId, "movieId must not be null");

        ArchiveMovie archiveMovie = archiveMovieQueries.getByMovieId(movieId);
        Long archiveMovieId = archiveMovie.getId();

        deleteById(archiveMovieId);
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
